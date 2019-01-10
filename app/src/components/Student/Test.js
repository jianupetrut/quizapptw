import React, { Component } from 'react';
import './Test.scss';
import StudentNavBar from './StudentNavBar'
import { Grid, Checkbox, Header, Label, Icon, List, Button, Segment } from 'semantic-ui-react'
import {MockedData} from '../../api/mocks/mockedData'


class AnswerLine extends Component{

    constructor(props){
        super(props);
    }

    render(){
        return(
            <List.Item>
                <Checkbox label={this.props.label} />
            </List.Item>
        )
    }
} 


//algorithm for shuffling array elements
function shuffleArray(array) {
    for (let i = array.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        [array[i], array[j]] = [array[j], array[i]];
    }
};




class Test extends Component{

    constructor(props){
        super(props);
        this.state = {
            questionsIDArray: [], //array with IDs for every relevant question
            questionsArray: [], //array which contains relevant question objects
            test: {}, //test object
            answersArray: [], //array which contains relevant answers objects
            shuffle: '',
            feedback: '',
            result: 0,
            time: 0,
            iteration: -1, //keeps track of current question
            maxIteration: -1, //knows when the test should be finished
            currentQuestion: {},
            currentAnswers: []
        }

        this.navigate = this.navigate.bind(this);

    }



    componentDidMount() {
        //fetch data here
        const testData = MockedData.data.tests[0];//get test info

        const questionsID = testData.questions_id;//get all question ids for the test
        console.log("question IDs",questionsID)

        const allAnswersArray = MockedData.data.answers;//first get all answers, then reduce to answers for questions from test

        const allQuestionsArray = MockedData.data.questions;//first get all questions, then reduce to questions from test
        console.log("allQuestionsArr", allQuestionsArray);

        function compareIDs(id){

            for(let questionID of questionsID){
                if(id == questionID){
                    return true;
                }
            }
            return false;
        }

        const relevantAnswersArray = allAnswersArray.filter(answerElement=>   
           compareIDs(answerElement.question_id)
        )    
        console.log("Relevant answers array", relevantAnswersArray);
        
        const relevantQuestionsArray = allQuestionsArray.filter(questionElement=>
            compareIDs(questionElement.id)
        )
        console.log("Relevant questions array", relevantQuestionsArray);
        
        if(testData.shuffle){
            shuffleArray(testData.questions_id);
            console.log("Array shuffled!", testData.questions_id);
            //the array is now shuffled
        }


        
        //setting the first question and answers
        var currQuest;
        relevantQuestionsArray.map((question)=>{
            if(question.id == testData.questions_id[0]){
                currQuest = question;
            }
        })
        console.log(currQuest);

        var currAns = [];
        relevantAnswersArray.map((answer)=>{
            if(answer.question_id == testData.questions_id[0]){
                currAns.push(answer);
            }
        })
        console.log(currAns);

        this.setState({ 
            questionsIDArray: testData.questions_id,
            test: testData,
            answersArray: relevantAnswersArray,
            questionsArray: relevantQuestionsArray,
            shuffle: testData.shuffle,
            feedback: testData.feedback,
            result: testData.result,
            time: testData.time,
            iteration: 0,
            maxIteration: testData.questions_id.length-1,
            currentQuestionID: relevantQuestionsArray[0].id,
            currentQuestion: currQuest,
            currentAnswers: currAns
        }, ()=>console.log("1state",this.state));
        
        
    }



    navigate(){

        var nextIteration = this.state.iteration + 1;
        console.log("nextIteration", nextIteration);
        var nextQuestionID = this.state.questionsIDArray[nextIteration];//moving to the next ID from the list

        // change the current question
        var nextQuestion;
        this.state.questionsArray.map(question=>{
            if(question.id == nextQuestionID){
                nextQuestion = question;
            }
        })

        //change the current answers
        var nextAnswers = [];
        this.state.answersArray.map(answer=>{
            if(answer.question_id == nextQuestionID){
                nextAnswers.push(answer);
            }
        })

        console.log('nextAnswers',nextAnswers)

        this.setState({
            currentAnswers: nextAnswers,
            iteration: nextIteration,
            currentQuestionID: nextQuestionID,
            currentQuestion: nextQuestion
            
        }, ()=>console.log("state",this.state))

        // if(this.state.iteration == this.state.maxIteration){
        //     //this is the last quiz page
        // }  

    }

    render(){
        
        return(
            <div className="test-info">
                <Segment>
                <Header as='h3' textAlign='center'>
                    {this.state.currentQuestion.question}
                </Header>
                </Segment>
                <List>
                    {
                        this.state.currentAnswers.map(answerElement =>  React.createElement(AnswerLine, {label: answerElement.answer}))
                    }
                </List>
                <Button animated type="submit" onClick = {() => this.navigate(this)}>
                    <Button.Content visible>Next</Button.Content>
                    <Button.Content hidden>
                    <Icon name='arrow circle right' />
                    </Button.Content>
                </Button>

            </div>
        )
    }
}

class NewTest extends Component{
    render(){
        return(
            <Grid>
                <Grid.Row>
                    <StudentNavBar></StudentNavBar>
                    <Test></Test>
                </Grid.Row>
            </Grid>
        )
    }
}

export default NewTest;
