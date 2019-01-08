import { Form, TextArea, Grid, Input, Dropdown, Checkbox, Button, Icon } from 'semantic-ui-react'
import React, { Component } from 'react';
import TeacherNavBar from './TeacherNavBar';

import './CreateQuestion.scss';


const TextAreaQuestion = () => (
    <Form>
      <TextArea autoHeight placeholder='Enter question here...' />
    </Form>
  )

// const Checkbox = () => <Checkbox label='Make my profile visible' />

class FormInput extends Component{
    
    constructor(props){
        super(props);
        
    }

    render(){
        return(
        <div>
            <Input focus placeholder={this.props.text}/>
            <Checkbox />
        </div>)
    }

}

const ButtonAnimated = ()=>    <Button animated>
                                    <Button.Content visible>Save</Button.Content>
                                    <Button.Content hidden>
                                    <Icon name='save' />
                                    </Button.Content>
                                </Button>

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

 const DropdownSelection = () => (
    <Dropdown placeholder='Select Question Category' fluid selection options={questionCategories} />
  )


class QuestionInfo extends Component{
    render(){
        return(
            <div className="create-question">
                <TextAreaQuestion></TextAreaQuestion>
                {React.createElement(FormInput, {text: 'Answer 1'})}
                {React.createElement(FormInput, {text: 'Answer 2'})}
                {React.createElement(FormInput, {text: 'Answer 3'})}
                {React.createElement(FormInput, {text: 'Answer 4'})}
                <DropdownSelection></DropdownSelection>
                <p>OR</p>
                <Input className="question-category-input" focus placeholder='Create question category' />
                <ButtonAnimated></ButtonAnimated>
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