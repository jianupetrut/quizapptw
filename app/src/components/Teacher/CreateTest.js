import React, { Component } from 'react';
import { Dropdown, Grid, Checkbox, Input, Icon, Button, Message } from 'semantic-ui-react'
import TeacherNavBar from './TeacherNavBar';

import './CreateTest.scss';

// const options = [
//     { key: 'angular', text: 'Angular', value: 'angular' },
//     { key: 'css', text: 'CSS', value: 'css' },
//     { key: 'design', text: 'Graphic Design', value: 'design' },
//     { key: 'ember', text: 'Ember', value: 'ember' },
//     { key: 'html', text: 'HTML', value: 'html' },
//     { key: 'ia', text: 'Information Architecture', value: 'ia' },
//     { key: 'javascript', text: 'Javascript', value: 'javascript' },
//     { key: 'mech', text: 'Mechanical Engineering', value: 'mech' },
//     { key: 'meteor', text: 'Meteor', value: 'meteor' },
//     { key: 'node', text: 'NodeJS', value: 'node' },
//     { key: 'plumbing', text: 'Plumbing', value: 'plumbing' },
//     { key: 'python', text: 'Python', value: 'python' },
//     { key: 'rails', text: 'Rails', value: 'rails' },
//     { key: 'react', text: 'React', value: 'react' },
//     { key: 'repair', text: 'Kitchen Repair', value: 'repair' },
//     { key: 'ruby', text: 'Ruby', value: 'ruby' },
//     { key: 'ui', text: 'UI Design', value: 'ui' },
//     { key: 'ux', text: 'User Experience', value: 'ux' },
//   ]

// const questions = ['question1', 'question2', 'question3', 'question4'];



class SimpleCheckbox extends Component{
    constructor(props){
        super(props);
    }

    state = { checked: false }
    toggle = (questionID) => {
        this.setState({ checked: !this.state.checked },
            ()=>{
                if(this.state.checked){
                    //the user selected the checkbox
                    this.props.questionSelected(this.state.checked,questionID);
                }
                else{
                    //the user deselected the checkbox
                    this.props.questionDeselected(this.state.checked,questionID);
                }
            })
    }

    render(){
        
        return (
            <Checkbox label={this.props.questionLabel} key={this.props.i} onChange={()=>this.toggle(this.props.questionID)} checked={this.state.checked} /> 
        )
    }
} 

class CheckboxToggle extends Component{
    constructor(props){
        super(props);
    }
    state = { checked: false }
    toggle = () => {
        this.setState({ checked: !this.state.checked })
        this.props.toggled(this.props.label, this.state.checked);
    }


    render(){
        return(
            <Checkbox toggle label={this.props.label} onChange={this.toggle} checked={this.state.checked}/>
        )
    }
}  




class TestInfo extends Component{

    constructor(props){
        super(props);
        this.state={
            owner_id:'',
            allQuestions: [],
            allQuestionCategories: [],
            options: [],
            chosenCategoriesID: [],
            questionsToDisplay: [],
            selectedQuestions: [],
            shuffle:false,
            one_way:false,
            feedback:false,
            testResult:false,
            time:'',
            retrieves:'',
            name:'',
            showResult:false,
            dataSubmitted: false
        }
    }

    componentDidMount(){
        //fetch data here
        Promise.all([
            fetch('https://quiz-app-api-georgedobrin.c9users.io/api/question_categories').then(res => res.json()),
            fetch('https://quiz-app-api-georgedobrin.c9users.io/api/questions').then(res => res.json()),
            fetch('https://quiz-app-api-georgedobrin.c9users.io/api/users/2').then(res => res.json())
          ])
          .then(responses => {
            console.log('responses', responses)
            this.setState({
              allQuestions: responses[1],
              allQuestionCategories: responses[0],
              owner_id: responses[2].id
            })

            //create the options object
            var options = [];
            responses[0].map((response,i)=>{
                var newObj={};
                newObj.text=response.category
                newObj.value=response.id
                newObj.key=i;
                options.push(newObj);
            })
            this.setState({
                options: options
            },()=>console.log("options",this.state.options))
          })
    }

    questionSelected = (checked, questionID) =>{
        console.log("Question selected!!",checked);
        console.log("Question ID", questionID);

        if(checked && !this.state.selectedQuestions.includes(questionID)){
            this.setState({
                selectedQuestions: [...this.state.selectedQuestions, questionID]//push to the array
            }, ()=>console.log("Selected Questions", this.state.selectedQuestions))
        }
    }

    questionDeselected = (checked, questionID)=>{
        console.log("Question deselected!!", checked);
        console.log("Question ID", questionID);
        //remove the element with the id = questionID
        const index = this.state.selectedQuestions.indexOf(questionID);
        var newQuestionsSelected = this.state.selectedQuestions;
        newQuestionsSelected.splice(index,1);
        
        console.log('NEW QUESTIONS SELECTED', newQuestionsSelected);
        this.setState({
            selectedQuestions: newQuestionsSelected
        })
    }

    dropdownChanged = (e, data) =>{
        console.log("Categories ID added", data.value);
        this.setState({
            chosenCategoriesID: data.value
        },
        ()=>{
            const questions = this.state.allQuestions.filter(question=>{
                if(this.state.chosenCategoriesID.includes(question.question_category_id)){
                    return true;
                }
            });
            this.setState({
                questionsToDisplay: questions
            },()=>console.log("STATE", this.state))
        })
    }

    toggled = (label,status) =>{
        console.log("LABEL: " + label + " has status " + !status)
        if(label == 'Shuffle questions and answers'){
            this.setState({
                shuffle: !status
            })
        }
        if(label == 'Question feedback'){
            this.setState({
                feedback: !status
            })
        }
        if(label == 'Show test result'){
            this.setState({
                showResult: !status
            })
        }
        if(label == 'One way test completion'){
            this.setState({
                one_way: !status
            })
        }
    }

    inputChanged = (name, value)=>{
        console.log(name+" field changed. Its value is "+value)
        if(name == 'time'){
            this.setState({
                time: value
            })
        }
        if(name == 'retrieves'){
            this.setState({
                retrieves: value
            })
        }
        if(name == 'name'){
            this.setState({
                name: value
            })
        }
    }

    submitData = ()=>{
        var newObj={};
        newObj.test = this.state.name;
        newObj.shuffle = this.state.shuffle;
        newObj.one_way = this.state.one_way;
        newObj.feedback = this.state.feedback;
        newObj.showResult = this.state.showResult;
        newObj.isActive = false;
        newObj.retrieves = this.state.retrieves;
        newObj.time = this.state.time;
        newObj.owner_id = this.state.owner_id;
        newObj.questions_id = this.state.selectedQuestions;

        console.log("Submitted data", newObj)
        fetch('https://quiz-app-api-georgedobrin.c9users.io/api/tests', {
            method: 'post',
            body: JSON.stringify(newObj),
            headers:{
                'Content-Type': 'application/json'
              }
        })
        .then(()=>{
            this.setState({
                dataSubmitted: true
            })
        })
    }

    render(){
        return(
            <div className="test-informations">
                <Input placeholder='Enter test name...' className="test-name" 
                    onChange={(e)=>{
                        this.inputChanged('name',e.target.value)
                    }}
                />
                <Dropdown placeholder='Question Categories' fluid multiple selection options={this.state.options} onChange={this.dropdownChanged}/>
                {
                    this.state.questionsToDisplay.map((question,i)=>
                        <div>
                        {
                            <SimpleCheckbox questionLabel={question.question} questionID={question.id} i={i} questionSelected={this.questionSelected} questionDeselected={this.questionDeselected}/> 
                        }
                        </div>
                    )
                }

                {React.createElement(CheckboxToggle, {label: 'Shuffle questions and answers', toggled:this.toggled})}
                {React.createElement(CheckboxToggle, {label: 'Question feedback', toggled:this.toggled})}
                {React.createElement(CheckboxToggle, {label: 'Show test result', toggled:this.toggled})}
                {React.createElement(CheckboxToggle, {label: 'One way test completion', toggled:this.toggled})}
                <Input
                    label={{ basic: true, content: 'mins' }}
                    labelPosition='right'
                    placeholder="Enter time..."
                    onChange={(e)=>{
                        this.inputChanged('time',e.target.value)
                    }
                    }
                />
                <Input
                    label={{ basic: true, content: 'retries' }}
                    labelPosition='right'
                    placeholder="Enter # retries..."
                    onChange={(e)=>{
                        this.inputChanged('retrieves',e.target.value)
                    }
                    }
                />
                <Button animated onClick={this.submitData}>
                    <Button.Content visible>Create</Button.Content>
                    <Button.Content hidden>
                    <Icon name='magic' />
                    </Button.Content>
                </Button>

                {
                    this.state.dataSubmitted
                    ?                     
                    <Message
                        success
                        header='Your user new test submission was successful'
                        content='You may now activate this test'
                    />
                    : <div></div>
                }
                

            </div>
        )
    }
}

class CreateTest extends Component{
    render(){
        return(
            <Grid>
                <Grid.Row>
                    <TeacherNavBar></TeacherNavBar>
                    <TestInfo></TestInfo>
                </Grid.Row>
            </Grid>
        )
    }
}

export default CreateTest;