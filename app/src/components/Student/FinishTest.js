import React, { Component } from 'react';
import './FinishTest.scss';
import StudentNavBar from './StudentNavBar'
import { Grid, Input, Header, Label, Icon, List, Button } from 'semantic-ui-react'

class FTest extends Component{

    end = false;

    navigate = function(){
        this.props.history.push('/student/profile');
    }

    render(){
        return(
            <div className="test-info">
                <Header as='h2' icon>
                    <Icon name='checkmark' />
                    Test Finished!
                    <Header.Subheader>Results have been successfully registered!</Header.Subheader>
                </Header>
                <Button animated type="submit" onClick = {() => this.navigate()}>
                    <Button.Content visible>Go to my Profile</Button.Content>
                    <Button.Content hidden>
                    <Icon name='next' />
                    </Button.Content>
                </Button>

            </div>
        )
    }
}

class FinishTest extends Component{
    render(){
        return(
            <Grid>
                <Grid.Row>
                    <StudentNavBar></StudentNavBar>
                    <FTest></FTest>
                </Grid.Row>
            </Grid>
        )
    }
}

export default FinishTest;
