import React, { Component } from 'react';
import './CreateQuestionCategory.scss';
import TeacherNavBar from './TeacherNavBar'
import { Button, Message, Grid, Input, Icon } from 'semantic-ui-react'

class CreateQuestionCategory extends Component{
    constructor(props){
        super(props);
        this.state={
            categoryName:'',
            dataSubmitted: false
        }
    }

    submit = () => {
        if(this.state.categoryName != ''){
            var newObj = {};
            newObj.category = this.state.categoryName;
            fetch('https://quiz-app-api-georgedobrin.c9users.io/api/question_categories', {
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
    }

    updateState = (e) => {
        this.setState({
            categoryName: e.target.value
        },
        ()=>console.log("State", this.state)
        )
    }

    render(){
        return (
            <div className="create-question-category">
                <Input icon={{ name: 'th list', circular: true, link: true }} placeholder='Enter category name...' onChange={this.updateState}/>
                <Button animated onClick={this.submit}>
                    <Button.Content visible>Add Category</Button.Content>
                    <Button.Content hidden>
                        <Icon name='add' />
                    </Button.Content>
                </Button>
                {
                        this.state.dataSubmitted
                        ?                     
                        <Message
                            success
                            header='Your user new question category submission was successful'
                            content='You may now add questions to this category'
                        />
                        : <div></div>
                    }
            </div>
        )
    }
}

class QuestionCategory extends Component{
    render(){
        return(
            <Grid>
                <Grid.Row>
                    <TeacherNavBar></TeacherNavBar>
                    <CreateQuestionCategory></CreateQuestionCategory>
                </Grid.Row>
            </Grid>
        )
    }
}

export default QuestionCategory;