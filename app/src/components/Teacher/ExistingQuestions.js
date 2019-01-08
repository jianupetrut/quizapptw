import { Dropdown, Grid, Label, Form, Header, Icon, Button, Table } from 'semantic-ui-react'
import React, { Component } from 'react';
import TeacherNavBar from './TeacherNavBar';
import './ExistingQuestions.scss';

class QuestionsList extends Component{
    render(){
        return(
            <div className="questions-list">
                {React.createElement(HeaderIcon, {icon: 'eye', header: 'Explore questions'})}
                {React.createElement(LabeledDropdown, {label: 'Select question category', dropdown: 'Question categories', source: questionCategories})}
                <QuestionsTable></QuestionsTable>
                {React.createElement(HeaderIcon, {icon: 'add circle', header: 'Add question to test'})}
                {React.createElement(LabeledDropdown, {label: 'Select question category', dropdown: 'Question categories', source: questionCategories})}
                {React.createElement(LabeledDropdown, {label: 'Select question', dropdown: 'Questions', source: questions})}
                {React.createElement(LabeledDropdown, {label: 'Select target test', dropdown: 'Tests', source: tests})}
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

const LabeledDropdown = (props) =>     <Form>
                                    <Form.Field inline>
                                        <Label pointing='right'>{props.label}</Label>
                                        <Dropdown placeholder={props.dropdown} fluid selection options={props.source} />
                                    </Form.Field>
                                </Form>

const questionCategories = [
    {
        text:"Category 1",
        value:"category1"
    },
    {
        text:"Category 2",
        value:"category2"
    },
    {
        text:"Category 3",
        value:"category3"
    }
]

const questions = [
    {
        text:"Question 1",
        value:"question 1",
        categories:["Category 1", "Category 2"]
    },
    {
        text:"Question 2",
        value:"question 2",
        categories:["Category 2"]
    },
    {
        text:"Question 3",
        value:"question 3",
        categories:["Category 2", "Category 3"]
    }
]

const tests = [
    {
        text:"Test 1",
        value:"test 1"
    },
    {
        text:"Test 2",
        value:"test 2"
    },
    {
        text:"Test 3",
        value:"test 3"
    }
]

const HeaderIcon = (props) => (
    <Header as='h2'>
      <Icon name={props.icon} />
      <Header.Content>{props.header}</Header.Content>
    </Header>
  )

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

const QuestionsTable = () =>(
    <Table celled>
        <Table.Header>
        <Table.Row>
            <Table.HeaderCell>#</Table.HeaderCell>
            <Table.HeaderCell>Question</Table.HeaderCell>
            <Table.HeaderCell>Categories</Table.HeaderCell>
        </Table.Row>
        </Table.Header>

        <Table.Body>
        {
            questions.map((question,i) => <Table.Row>
                                            <Table.Cell>{i}</Table.Cell>
                                            <Table.Cell>{question.text}</Table.Cell>
                                            <Table.Cell>{question.categories.map((category,i)=><div key={i}>{category}</div>)}</Table.Cell>
                                        </Table.Row> )
        }
        </Table.Body>
    </Table>
)

export default ExistingQuestions;