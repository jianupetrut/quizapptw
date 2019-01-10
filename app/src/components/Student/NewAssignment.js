import React, { Component } from 'react';
import './NewAssignment.scss';
import StudentNavBar from './StudentNavBar'
import { Grid, Input, Header, Label, Icon, List, Button } from 'semantic-ui-react'
import { Redirect } from 'react-router-dom'


class NewAssignment extends Component{
    render(){
        return(
            <Grid>
                <Grid.Row>
                    <StudentNavBar></StudentNavBar>
                    <div className="test-info">
                        <Input icon='pencil square' iconPosition='left' placeholder='Enter test ID...' />
                        <Button>OK</Button>
                        <Header as='h2'>
                            Test Information
                            <Header.Subheader>Below you can see relevant details about the quiz</Header.Subheader>
                        </Header>
                        <List>
                            <List.Item>
                                <Label>
                                    <Icon name='user circle' /> Professor: John Doe
                                </Label>
                            </List.Item>
                        </List>
                        <List>
                            <List.Item>
                                <Label>
                                    <Icon name='newspaper' /> Subject: Web Technologies
                                </Label>
                            </List.Item>
                        </List>
                        <List>
                            <List.Item>
                                <Label>
                                    <Icon name='clock' /> Time: 30 mins
                                </Label>
                            </List.Item>
                        </List>
                        <List>
                            <List.Item>
                                <Label>
                                    <Icon name='question circle' /> Number of questions: 9
                                </Label>
                            </List.Item>
                        </List>
                        <Button className="start-btn" onClick = { ()=> this.props.history.push('/student/assignment/new') }>Start Quiz</Button>

                    </div>
                </Grid.Row>
            </Grid>
        )
    }
}

export default NewAssignment;
