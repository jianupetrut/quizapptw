import { Dropdown, Grid } from 'semantic-ui-react'
import React, { Component } from 'react';
import { Switch, Route } from 'react-router-dom';
import TeacherNavBar from './TeacherNavBar';

import './ExistingTests.scss';

class Slideshow extends Component{
    render(){
        return(
            <p>ExistingTests works</p>
        )
    }
}

class ExistingTests extends Component{
    render(){
        return(
            <Grid>
                <Grid.Row>
                    <TeacherNavBar></TeacherNavBar>
                    <Slideshow></Slideshow>
                </Grid.Row>
            </Grid>
        )
    }
}

export default ExistingTests;