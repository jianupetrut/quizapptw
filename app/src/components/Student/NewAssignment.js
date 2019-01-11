import React, { Component } from 'react';
import './NewAssignment.scss';
import StudentNavBar from './StudentNavBar'
import { Grid, Input, Header, Label, Icon, List, Button, Form, Message } from 'semantic-ui-react'
import {MockedData} from '../../api/mocks/mockedData'


class NewAssignment extends Component{

    constructor(props){
        super(props);
        this.state={
            testData: '',
            name: '',
            time: 0,
            questions: 0
        }
    }

    showInfo = false;
    wrongID = false;
    enteredID = '';

    // componentDidMount(){
    //     //fetch data here
    //     const testData = MockedData.data.tests[0];//get test info
    //     const testName = testData.test;
    //     const testTime = testData.time;
    //     const questions = testData.questions_id.length;

        // this.setState({ 
        //     testData: testData,
        //     name: testName,
        //     time: testTime,
        //     questions: questions
        // }, ()=>console.log("1state",this.state));
    // }

    
    getTestInfo = () => {
        //find the entered id in the DB
        const testsData = MockedData.data.tests; //got all tests
        console.log("testsData",testsData)
        const foundTest = testsData.filter(test=>{
            if(test.id == this.enteredID){
                return test;
            }
        })

        console.log("foundTest", foundTest)

        if(foundTest.length === 0 || !foundTest[0].isActive ){
            this.wrongID = true;
            this.showInfo = false;
            this.setState({
                name: '',
                time: 0,
                questions: 0
            })
        }
        else{
            this.showInfo = true;
            this.wrongID = false;
            this.setState({ 
                testData: foundTest[0],
                name: foundTest[0].test,
                time: foundTest[0].time,
                questions: foundTest[0].questions_id.length
            }, ()=>console.log("found test state",this.state));
        }


    }

    render(){
        return(
            <Grid>
                <Grid.Row>
                    <StudentNavBar></StudentNavBar>
                    <div className="test-info">
                    <Form onSubmit={this.getTestInfo}>
                        <Form.Input action="OK" icon='pencil square' iconPosition='left' placeholder='Enter test ID...' name='enteredID' onChange={evt =>  this.enteredID = evt.target.value }/>
                    </Form>
                    {
                        this.wrongID 
                        ?
                            <Message
                            icon='warning circle'
                            header='Incorrect ID'
                            content='The ID you entered could not be found or the test has not been activated. Please try again'
                            />
                        :
                            <div></div>
                    }

                        {
                            this.showInfo
                            ?
                                <div>
                                    <Header as='h2'>
                                        Test Information
                                        <Header.Subheader>Below you can see relevant details about the quiz</Header.Subheader>
                                    </Header>
                                    <List>
                                        <List.Item>
                                            <Label>
                                                <Icon name='clock' /> Test name: {this.state.name}
                                            </Label>
                                        </List.Item>
                                        <List.Item>
                                            <Label>
                                                <Icon name='clock' /> Time: {this.state.time} min
                                            </Label>
                                        </List.Item>
                                        <List.Item>
                                            <Label>
                                                <Icon name='question circle' /> Number of questions: {this.state.questions}
                                            </Label>
                                        </List.Item>
                                    </List>
                                    <Button className="start-btn" onClick = { 
                                        ()=> this.props.history.push({
                                            pathname: '/student/assignment/new', 
                                            state: {test: this.state.testData}}) 
                                        }
                                    >Start Quiz</Button>
                                </div>
                            :
                                <div></div>
                        }

                    </div>
                </Grid.Row>
            </Grid>
        )
    }
}

export default NewAssignment;
