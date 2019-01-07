import { Dropdown, Grid } from 'semantic-ui-react'
import React, { Component } from 'react';
import { Switch, Route } from 'react-router-dom';
import TeacherNavBar from './TeacherNavBar';

import './ExistingQuestions.scss';

class Statistics extends Component{
    render(){
        return(
            <p>AssignmentsHistory works</p>
        )
    }
}

class AssignmentsHistory extends Component{
    render(){
        return(
            <Grid>
                <Grid.Row>
                    <TeacherNavBar></TeacherNavBar>
                    <Statistics></Statistics>
                </Grid.Row>
            </Grid>
        )
    }
}

export default AssignmentsHistory;