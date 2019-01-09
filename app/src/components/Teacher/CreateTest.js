import React, { Component } from 'react';
import { Dropdown, Grid, Checkbox, Input, Icon, Button } from 'semantic-ui-react'
import TeacherNavBar from './TeacherNavBar';

import './CreateTest.scss';

const options = [
    { key: 'angular', text: 'Angular', value: 'angular' },
    { key: 'css', text: 'CSS', value: 'css' },
    { key: 'design', text: 'Graphic Design', value: 'design' },
    { key: 'ember', text: 'Ember', value: 'ember' },
    { key: 'html', text: 'HTML', value: 'html' },
    { key: 'ia', text: 'Information Architecture', value: 'ia' },
    { key: 'javascript', text: 'Javascript', value: 'javascript' },
    { key: 'mech', text: 'Mechanical Engineering', value: 'mech' },
    { key: 'meteor', text: 'Meteor', value: 'meteor' },
    { key: 'node', text: 'NodeJS', value: 'node' },
    { key: 'plumbing', text: 'Plumbing', value: 'plumbing' },
    { key: 'python', text: 'Python', value: 'python' },
    { key: 'rails', text: 'Rails', value: 'rails' },
    { key: 'react', text: 'React', value: 'react' },
    { key: 'repair', text: 'Kitchen Repair', value: 'repair' },
    { key: 'ruby', text: 'Ruby', value: 'ruby' },
    { key: 'ui', text: 'UI Design', value: 'ui' },
    { key: 'ux', text: 'User Experience', value: 'ux' },
  ]

const questions = ['question1', 'question2', 'question3', 'question4'];


const DropdownMultipleSelection = () => (
    <Dropdown placeholder='Question Categories' fluid multiple selection options={options} />
)


class SimpleCheckbox extends Component{
    render(){
        return (
            <div>
            {
                questions.map((question,i) => <Checkbox label={question} key={i} /> )
            }
            </div>
        )
    }
} 

const CheckboxToggle = (props) => <Checkbox toggle label={props.label}/>

const DigitInput = (props) => (
    <Input
      label={{ basic: true, content: props.content }}
      labelPosition='right'
      placeholder={props.placeholder}
    />
  )


const ButtonAnimated = ()=>    <Button animated>
                                    <Button.Content visible>Create</Button.Content>
                                    <Button.Content hidden>
                                    <Icon name='magic' />
                                    </Button.Content>
                                </Button>

class TestInfo extends Component{

    render(){
        return(
            <div className="test-informations">
                <Input placeholder='Enter test name...' className="test-name"/>
                <DropdownMultipleSelection></DropdownMultipleSelection>
                <SimpleCheckbox ></SimpleCheckbox>
                {React.createElement(CheckboxToggle, {label: 'Shuffle questions and answers'})}
                {React.createElement(CheckboxToggle, {label: 'Question feedback'})}
                {React.createElement(CheckboxToggle, {label: 'Show test result'})}
                {React.createElement(CheckboxToggle, {label: 'One way test completion'})}
                {React.createElement(DigitInput, {placeholder: 'Enter time...', content: 'mins'})}
                {React.createElement(DigitInput, {placeholder: 'Enter # retries...', content: 'retrieves'})}
                <ButtonAnimated></ButtonAnimated>
                

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