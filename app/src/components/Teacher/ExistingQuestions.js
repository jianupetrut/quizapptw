import { Dropdown, Grid, Label, Form, Header, Icon, Button, Table } from 'semantic-ui-react'
import React, { Component } from 'react';
import TeacherNavBar from './TeacherNavBar';
import './ExistingQuestions.scss';
import {MockedData} from '../../api/mocks/mockedData'

class QuestionsList extends Component{
    constructor(props){
        super(props);
        this.state={
            questionCategories:[]
        }
    }

    componentDidMount(){
        //fetch data here
        const questionCategories = MockedData.data.question_categories;
        const allQuestions = MockedData.data.questions;

        var newQuestionCategories = [];

        //the object has to be this way as per Semantic UI documentation, that's why I created another object
        questionCategories.map(category=>{
            newQuestionCategories.push({text: category.category, value: category.id})
        })

        console.log("newQuestionCategories", newQuestionCategories)

        this.setState({
            questionCategories: newQuestionCategories,
            allQuestions: allQuestions
        })
    }

    render(){
        return(
            <div className="questions-list">
                {React.createElement(HeaderIcon, {icon: 'eye', header: 'Explore questions'})}
                {React.createElement(QuestionsCategoriesDropdown, {label: 'Select question category', dropdown: 'Question categories', source: this.state.questionCategories, questions: this.state.allQuestions})}
                {React.createElement(HeaderIcon, {icon: 'add circle', header: 'Add question to test'})}
                {/* {React.createElement(LabeledDropdown, {label: 'Select question category', dropdown: 'Question categories', source: this.state.questionCategories})}
                {React.createElement(LabeledDropdown, {label: 'Select question', dropdown: 'Questions', source: questions})}
                {React.createElement(LabeledDropdown, {label: 'Select target test', dropdown: 'Tests', source: tests})} */}
                <Button animated>
                    <Button.Content visible>Add</Button.Content>
                    <Button.Content hidden>
                        <Icon name='add square' />
                    </Button.Content>
                </Button>

            </div>
        )
    }
}

class QuestionsCategoriesDropdown extends Component{
    constructor(props){
        super(props);
        this.state={
            choice:''
        }
    }

    selectCategory = (event, data) =>{
        console.log("selected value ID", data.value);
        this.setState({
            choice: data.value
        })
    }

    render(){
        return(
            <div>
                <Form>
                    <Form.Field inline>
                        <Label pointing='right'>{this.props.label}</Label>
                        <Dropdown placeholder={this.props.dropdown} fluid selection options={this.props.source} onChange={this.selectCategory}/>
                    </Form.Field>
                </Form>
                <QuestionsTable choiceID={this.state.choice} questionsArr={this.props.questions} ></QuestionsTable>
            </div>
        )
    }
}



const HeaderIcon = (props) => (
    <Header as='h2'>
      <Icon name={props.icon} />
      <Header.Content>{props.header}</Header.Content>
    </Header>
  )



class QuestionsTable extends Component{
    constructor(props){
        super(props);
    }

    assignGoodData = () => {
        var goodData='';
        if(this.props.questionsArr){
            goodData = this.props.questionsArr.filter(question=>{
                if(question.id === this.props.choiceID){
                    return true;
                }
            })
        }
        return goodData;
    }



    render(){

        const goodData = this.assignGoodData();
        console.log("good data", goodData);


        return(
            <Table celled>
                <Table.Header>
                <Table.Row>
                    <Table.HeaderCell>Question</Table.HeaderCell>
                    <Table.HeaderCell>Category ID</Table.HeaderCell>
                </Table.Row>
                </Table.Header>
        
                <Table.Body>
                {
                    goodData.length!=0
                    ?
                    goodData.map((question,i) => <Table.Row>
                                                    <Table.Cell>{question.question}</Table.Cell>
                                                    <Table.Cell>{question.question_category_id}</Table.Cell>
                                                </Table.Row> )
                    :
                    <div></div>
                }
                </Table.Body>
            </Table>
        )
    }
}

class ExistingQuestions extends Component{
    render(){
        return(
            <Grid>
                <Grid.Row>
                    <TeacherNavBar></TeacherNavBar>
                    <QuestionsList></QuestionsList>
                </Grid.Row>
            </Grid>
        )
    }
}

export default ExistingQuestions;