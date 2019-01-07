import { Dropdown, Grid } from 'semantic-ui-react'
import React, { Component } from 'react';
import { Switch, Route } from 'react-router-dom';
import TeacherNavBar from './TeacherNavBar';

import './CreateQuestion.scss';

class QuestionInfo extends Component{
    render(){
        return(
            <p>Create question works</p>
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