import { Form, TextArea, Grid, Input, Dropdown, Message, Button, Icon, Radio } from 'semantic-ui-react'
import React, { Component } from 'react';
import TeacherNavBar from './TeacherNavBar';

import './CreateQuestion.scss';

class RadioGroup extends Component {
    state = {value: ''}
    handleChange = (e, { value }) => {
        this.setState({ value },
            ()=>this.props.answerSelected(this.state.value))
        
    }
  
    render() {
      return (
        <Form>
          <Form.Field>
            <Input placeholder='Answer 1...' onChange={(evt)=>{this.props.elementChanged('answer1', evt.target.value)}}/>
            <Radio
              label="Check if above answer is correct"
              name='radioGroup'
              value='1'
              checked={this.state.value === '1'}
              onChange={this.handleChange}
            />
          </Form.Field>
          <Form.Field>
            <Input placeholder='Answer 2...' onChange={(evt)=>{this.props.elementChanged('answer2', evt.target.value)}}/>
            <Radio
              label="Check if above answer is correct"
              name='radioGroup'
              value='2'
              checked={this.state.value === '2'}
              onChange={this.handleChange}
            />
          </Form.Field>
          <Form.Field>
            <Input placeholder='Answer 3...' onChange={(evt)=>{this.props.elementChanged('answer3', evt.target.value)}}/>
            <Radio
              label="Check if above answer is correct"
              name='radioGroup'
              value='3'
              checked={this.state.value === '3'}
              onChange={this.handleChange}
            />
          </Form.Field>
          <Form.Field>
            <Input placeholder='Answer 4...' onChange={(evt)=>{this.props.elementChanged('answer4', evt.target.value)}}/>
            <Radio
              label="Check if above answer is correct"
              name='radioGroup'
              value='4'
              checked={this.state.value === '4'}
              onChange={this.handleChange}
            />
          </Form.Field>
        </Form>
      )
    }
  }



class QuestionInfo extends Component{
    constructor(props){
        super(props);
    }
    state={
        questionCategories: [],
        newQuestionText:'',
        newQuestionCategory:'',
        answer1Text:'',
        answer2Text:'',
        answer3Text:'',
        answer4Text:'',
        correctAnswer:'',
        dataSubmitted: false
    }

    componentDidMount(){
        //fetch data
        fetch('https://quiz-app-api-georgedobrin.c9users.io/api/question_categories')
        .then(res=>res.json()
        .then(categories=>{
            var newArr = [];

            categories.map(category=>{
                var newObj = {};
                newObj.text = category.category;
                newObj.value = category.id;
                newArr.push(newObj);
            })

            this.setState({
                questionCategories: newArr
            },()=>console.log("categories", this.state))
        }))
    }

    elementChanged = (element, value) =>{

        console.log("Element: "+ element);
        console.log("Value: " + value);

        if(element == 'textarea'){
            this.setState({
                newQuestionText: value
            },()=>console.log("State after question text", this.state))
        }
        if(element == 'answer1'){
            this.setState({
                answer1Text: value
            })
        }
        if(element == 'answer2'){
            this.setState({
                answer2Text: value
            })
        }
        if(element == 'answer3'){
            this.setState({
                answer3Text: value
            })
        }
        if(element == 'answer4'){
            this.setState({
                answer4Text: value
            })
        }

    }

    answerSelected = (element)=>{
        console.log("This is the correct answer id: " + element);
        this.setState({
            correctAnswer: element
        });
    }

    // "question_category_id": 3,
	// "question": "What does eco and no and metrics mean?",
	// "image": ""

    submitData = () =>{
        //first we submit the question
        var newQuestion={};
        if(this.state.newQuestionText!= '' && this.state.newQuestionCategory != ''){
            newQuestion.question_category_id = this.state.newQuestionCategory;
            newQuestion.question = this.state.newQuestionText;
            newQuestion.image='';
        }
        console.log("New question", newQuestion);

        var newAnswer1={}, newAnswer2={}, newAnswer3={}, newAnswer4={};
        newAnswer1.answer = this.state.answer1Text;
        newAnswer2.answer = this.state.answer2Text;
        newAnswer3.answer = this.state.answer3Text;
        newAnswer4.answer = this.state.answer4Text;

        if(this.state.correctAnswer == 1){
            newAnswer1.isCorrect = true;
        }
        else{
            newAnswer1.isCorrect = false;
        }

        if(this.state.correctAnswer == 2){
            newAnswer2.isCorrect = true;
        }
        else{
            newAnswer2.isCorrect = false;
        }

        if(this.state.correctAnswer == 3){
            newAnswer3.isCorrect = true;
        }
        else{
            newAnswer3.isCorrect = false;
        }

        if(this.state.correctAnswer == 4){
            newAnswer4.isCorrect = true;
        }
        else{
            newAnswer4.isCorrect = false;
        }

        var insertedID='';

        fetch('https://quiz-app-api-georgedobrin.c9users.io/api/questions', {
            method: 'post',
            body: JSON.stringify(newQuestion),
            headers:{
                'Content-Type': 'application/json'
              }
        })
        .then(res=>res.json()
        .then(question=>{

            insertedID = question.id;
            console.log("INSERTED ID IS ",insertedID);
            newAnswer1.question_id = insertedID;
            newAnswer2.question_id = insertedID;
            newAnswer3.question_id = insertedID;
            newAnswer4.question_id = insertedID;
            Promise.all([
                fetch('https://quiz-app-api-georgedobrin.c9users.io/api/answers', {
                method: 'post',
                body: JSON.stringify(newAnswer1),
                headers:{
                    'Content-Type': 'application/json'
                  }
                }),
                fetch('https://quiz-app-api-georgedobrin.c9users.io/api/answers', {
                    method: 'post',
                    body: JSON.stringify(newAnswer2),
                    headers:{
                        'Content-Type': 'application/json'
                    }
                }),
                fetch('https://quiz-app-api-georgedobrin.c9users.io/api/answers', {
                    method: 'post',
                    body: JSON.stringify(newAnswer3),
                    headers:{
                        'Content-Type': 'application/json'
                    }
                }),
                fetch('https://quiz-app-api-georgedobrin.c9users.io/api/answers', {
                    method: 'post',
                    body: JSON.stringify(newAnswer4),
                    headers:{
                        'Content-Type': 'application/json'
                    }
                })
            ])
            .then(
                this.setState({
                    dataSubmitted: true
                })
            )
            
        }
        )) 

        
        
    }

    render(){
        return(
            <div className="create-question">
                <Form>
                    <TextArea autoHeight placeholder='Enter question here...' onChange={(evt) => { this.elementChanged('textarea', evt.target.value); }}/>

                    <RadioGroup elementChanged={this.elementChanged} answerSelected={this.answerSelected}></RadioGroup>
                    
                    <Dropdown placeholder='Select Question Category' fluid selection options={this.state.questionCategories} 
                    onChange=
                    {
                        (evt, data)=>{
                            this.setState({
                                newQuestionCategory: data.value
                            },()=>console.log("New question category state", this.state))

                            console.log("Question category chosen:", data.options[data.value-1].text)
                        }
                    }
                    ></Dropdown>

                    <Button animated onClick={this.submitData}>
                        <Button.Content visible>Save</Button.Content>
                        <Button.Content hidden>
                        <Icon name='save' />
                        </Button.Content>
                    </Button>
                    {
                        this.state.dataSubmitted
                        ?                     
                        <Message
                            success
                            header='Your user new question submission was successful'
                            content='You may now add this question to a new test'
                        />
                        : <div></div>
                    }


                </Form>
            </div>
        )
    }
}

class CreateQuestion extends Component{
    render(){
        return(
            <Grid>
                <Grid.Row>
                    <TeacherNavBar></TeacherNavBar>
                    <QuestionInfo></QuestionInfo>
                </Grid.Row>
            </Grid>
        )
    }
}

export default CreateQuestion;