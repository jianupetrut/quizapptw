import { Grid, Segment, List, Button } from 'semantic-ui-react'
import React, { Component } from 'react';
import TeacherNavBar from './TeacherNavBar';

import './ActiveTests.scss';

class ActiveT extends Component{
    render(){
        return(
            <div className="active-t">
            {tests.map((test,i)=>React.createElement(ActiveTestsList,{header: test.text, date: test.creationDate, time: test.timeRemaining}))}
            </div>
        )
    }
}

const tests = [
    {
        text:"Test 1",
        value:"test 1",
        creationDate:"23.10.2018",
        timeRemaining:"23:59"
    },
    {
        text:"Test 2",
        value:"test 2",
        creationDate:"02.11.2018",
        timeRemaining:"04:05"
    }
]

const ActiveTestsList = (props) => (
    <Segment inverted>
      <List divided inverted relaxed>
        <List.Item>
            <List.Content>
            <List.Header>{props.header}</List.Header>
            <div>Created on: {props.date}</div> 
            <div>Time Remaining: {props.time}</div>
            </List.Content>
        </List.Item>        
      </List>
      <Button negative>Stop</Button>
    </Segment>
  )

class ActiveTests extends Component{
    render(){
        return(
            <Grid>
                <Grid.Row>
                    <TeacherNavBar></TeacherNavBar>
                    <ActiveT></ActiveT>
                </Grid.Row>
            </Grid>
        )
    }
}

export default ActiveTests;