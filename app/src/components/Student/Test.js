import React, { Component } from 'react';
import './Test.scss';
import StudentNavBar from './StudentNavBar'
import { Grid, Checkbox, Header, Label, Icon, List, Button, Segment, Form, Radio } from 'semantic-ui-react'
import {MockedData} from '../../api/mocks/mockedData'


class AnswerLine extends Component{

    constructor(props){
        super(props);
    }

    state = {}
    handleChange = (e, {value}) => {

        var isCorrect = '';
        if( this.props.isCorrect ){
            isCorrect = true;
        }
        else{
            isCorrect = false;
        }
        this.setState({ isCorrect: isCorrect, value: value},()=>console.log('answerLine state', this.state))
        this.props.onChangeAnswer(isCorrect);  
    }
    // handleChange = (e, { value }) => this.setState({ value })

    render(){
        return(
            <div>
                <Form.Field>
                <Radio
                    label={this.props.label}
                    name='radioGroup'
                    value={this.props.id}
                    checked={this.state.value === this.props.id}
                    onChange={this.handleChange}
                />
                </Form.Field>
            </div>
        )
    }
} 

const FinishedTestSegment = (props) => (
    <Segment placeholder>
      <Header icon>
        <Icon name='checkmark' />
        You have completed this test!
        { props.showResult ? <div> Correct answers: {props.score} <div>Total questions: {props.maxIteration + 1} </div><div>Percentage: {props.score/(props.maxIteration + 1) * 100}% </div> <Button primary>Go to my profile</Button></div> : <Button primary>Go to my profile</Button>}
      </Header>
    </Segment>
  )




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
            showResult: '',
            score: 0,
            time: 0,
            iteration: -1, //keeps track of current question
            maxIteration: -1, //knows when the test should be finished
            currentQuestion: {},
            currentAnswers: []
        }

        this.navigate = this.navigate.bind(this);

    }

    isCorrectAnswer = ''; //stores whether the choice is the correct answer or not



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
            showResult: testData.result,
            score: 0,
            time: testData.time,
            iteration: 0,
            maxIteration: testData.questions_id.length-1,
            currentQuestionID: relevantQuestionsArray[0].id,
            currentQuestion: currQuest,
            currentAnswers: currAns
        }, ()=>console.log("1state",this.state));
        
        
    }

    storeAnswer(){
        //store the choice and compute the score
        if(this.state.iteration == 0){
            //reset local storage
            localStorage.setItem('score', 0)
            localStorage.setItem('outOf', 0)
            localStorage.setItem('maxScore', 0)
        }
        // console.log('storeanswer test', this.state);
        // console.log('isCorrect in storeanswer', this.isCorrectAnswer)
        if(this.isCorrectAnswer){
            //correct answer
            var newScore = this.state.score + 1;
            this.setState({
                score: newScore
            })
            localStorage.setItem('score', newScore)
            localStorage.setItem('outOf', this.state.iteration+1)
            localStorage.setItem('maxScore', this.state.maxIteration+1)
        }else{
            localStorage.setItem('outOf', this.state.iteration+1)
        }

        if(this.state.iteration === this.state.maxIteration){
            //last iteration, test will be finished
            //TO DO: send results to DB
        }

    }



    navigate(){
        //go to next question or to the end of the quiz
        //store the answer
        //the state changes here
        this.storeAnswer(this);

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


    }

    onChangeAnswer = isCorrect =>{
        //this triggers whenever the user chooses an answer and tells if it's correct or not
        this.isCorrectAnswer = isCorrect;
        console.log('isCorrectAnswer', this.isCorrectAnswer);
    }

    render(){
        
        return(
            <div className="test-info">


                {
                    this.state.iteration <= this.state.maxIteration 
                    ? 
                        //test is not finished, show next question
                        <div>
                            <Segment>
                            <Header as='h3' textAlign='center'>
                                {this.state.currentQuestion.question}
                            </Header>
                            </Segment>
                            <Form>
                                {
                                    this.state.currentAnswers.map((answerElement,i) =>  React.createElement(AnswerLine, {index: i, label: answerElement.answer, isCorrect: answerElement.isCorrect, id: answerElement.id, onChangeAnswer: this.onChangeAnswer}))
                                }
                            </Form>
            
                        </div>
                    
                    : 
                        //test is finished
                        
                            <FinishedTestSegment showResult={this.state.showResult} score={this.state.score} maxIteration={this.state.maxIteration}></FinishedTestSegment>
                              
                        
                    
                }


                {
                    //verify if we should show the 'next' button
                    this.state.iteration <= this.state.maxIteration 
                    ? 
                        <Button type="submit" onClick = {() => this.navigate(this)}>
                            <Button.Content visible>Next</Button.Content>
                        </Button>
                    : 
                        <div></div>
                }
                

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
