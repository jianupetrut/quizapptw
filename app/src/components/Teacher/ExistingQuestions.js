import { Dropdown, Grid } from 'semantic-ui-react'
import React, { Component } from 'react';
import { Switch, Route } from 'react-router-dom';
import TeacherNavBar from './TeacherNavBar';

import './ExistingQuestions.scss';

class QuestionsList extends Component{
    render(){
        return(
            <p>ExistingQuestions works</p>
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