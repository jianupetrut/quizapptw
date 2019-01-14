import { Grid, Card, Icon, Button, Modal, Header, Input, Checkbox, List } from 'semantic-ui-react'
import React, { Component } from 'react';
import TeacherNavBar from './TeacherNavBar';
import './ExistingTests.scss';

class Slideshow extends Component{

    constructor(props){
        super(props);
        this.state={
            tests:[]
        }
    }

    componentDidMount(){
        //fetch data
        Promise.all([
            fetch('https://quiz-app-api-georgedobrin.c9users.io/api/users/2').then(res => res.json()),
            fetch('https://quiz-app-api-georgedobrin.c9users.io/api/tests').then(res => res.json())
          ])
          .then(responses => {
            console.log('responses', responses)
            const userID = responses[0].id;
            const tests = responses[1];
    
            //get all tests created by this user
            const relevantTests = tests.filter(test => {
                if(test.owner_id === userID){
                    return true;
                }
            })
    
            console.log("Relevant tests", relevantTests);
            this.setState({
                tests: relevantTests
            })
          })


    }

    toggleTest = (id, status) =>{
        //put method
        console.log("Entered toggle test. Test ID: " ,id);
        console.log("isActive", status);
        var test={};
        test.isActive = !status;
        fetch('https://quiz-app-api-georgedobrin.c9users.io/api/tests/' + id, {
            method: 'put',
            body: JSON.stringify(test),
            headers:{
                'Content-Type': 'application/json'
              }
        }).then(()=>window.location.reload())//refresh the page
        


    }

    render(){
        return(
            <div className="existing-tests">
                {this.state.tests.map((test,i)=>React.createElement(TestCard, {header: test.test, isActive: test.isActive, toggleTest: this.toggleTest, testInfo: test}))}
            </div>
        )
    }
}

const TestCard = (props) => (
    <Card>
        <Card.Content>
        <Icon name='tasks' size='big' />
        <Card.Header>{props.header}</Card.Header>
        </Card.Content>
        <Card.Content extra>

        <div className='ui three buttons'>

            <ActivateTestModal isActive={props.isActive} toggleTest={props.toggleTest} id={props.testInfo.id} ></ActivateTestModal>
            <EditTestModal testData={props.testInfo} ></EditTestModal>


            <Modal trigger={<Button basic color='black'>Share</Button>} closeIcon>
                <Header icon='share square' content='Share this test' />
                <Modal.Content>
                <Button>via e-mail</Button>
                <Button>via app</Button>
                </Modal.Content>
            </Modal>
        </div>
        </Card.Content>
    </Card>
)

class ActivateTestModal extends Component{

    state = { modalOpen: true }

    handleClose = () => {
        this.setState({ modalOpen: false })
        console.log('Handle close state', this.state)
    }
    

    render(){
        return(
            <Modal 
            trigger={this.props.isActive ? <Button negative>Deactivate</Button> : <Button positive>Activate</Button>} 
            closeIcon 
            >
                {
                    this.props.isActive
                    ?
                        <Header icon='lock' content='Deactivate this test' />
                    :
                        <Header icon='lock open' content='Activate this test' />
                }
                <Modal.Content>
                    {
                        this.props.isActive
                        ?
                            <p>
                                Do you want to deactivate this test?
                            </p>
                        :
                            <p>
                            Do you want to activate this test?
                            </p>

                    }

                </Modal.Content>
                <Modal.Actions>
                <Button color='green' onClick = {()=>{
                    this.handleClose();
                    this.props.toggleTest(this.props.id, this.props.isActive);
                    
                    }
                }>
                    <Icon name='checkmark' /> Yes
                </Button>
                </Modal.Actions>
            </Modal>
        )
    }
}

class EditTestModal extends Component{
    constructor(props){
        super(props);
        this.state={
            shuffle:'',
            feedback:'',
            showResult:'',
            one_way:'',
            time: '',
            retrieves: ''
        }
    }

    checkboxChanged = (element, newStatus) =>{
        console.log(element + " checkbox changed and became " + !newStatus);
        if(element == 'shuffle'){
            this.setState({
                shuffle: !newStatus
            }, ()=>console.log('shuffle state', this.state))
        }
        else if(element == 'one-way'){
            this.setState({
                one_way: !newStatus
            }, ()=>console.log('one-way state', this.state))
        }
        else if(element == 'showResult'){
            this.setState({
                showResult: !newStatus
            }, ()=>console.log('showResult state', this.state))
        }
        else if(element == 'feedback'){
            this.setState({
                feedback: !newStatus
            }, ()=>console.log('feedback state', this.state))
        }
        console.log('state after checkboxes', this.state);
    }

    inputChanged = (element, newValue) =>{
        console.log("Element: ", element);
        console.log("Value: ", newValue)
        if(element == 'mins'){
            this.setState({
                time: newValue
            })
        }
        else if(element == "retrieves"){
            this.setState({
                retrieves: newValue
            })
        }
    }

    editBtn = () =>{
        console.log("Edit btn pressed for test ID: " + this.props.testData.id);
        console.log("This is the state right now: ", this.state)

        var newObj ={};
        if(this.state.feedback === true || this.state.feedback === false){
            newObj.feedback = this.state.feedback;
        }
        if(this.state.shuffle === true || this.state.shuffle === false){
            newObj.shuffle = this.state.shuffle;
        }
        if(this.state.showResult === true || this.state.showResult === false){
            newObj.showResult = this.state.showResult;
        }
        if(this.state.one_way === true || this.state.one_way === false){
            newObj.one_way = this.state.one_way;
        }
        if(this.state.time != ''){
            newObj.time = this.state.time;
        }
        if(this.state.retrieves != ''){
            newObj.retrieves = this.state.retrieves;
        }
        console.log("NEW OBJ", newObj);

        fetch('https://quiz-app-api-georgedobrin.c9users.io/api/tests/' + this.props.testData.id, {
            method: 'put',
            body: JSON.stringify(newObj),
            headers:{
                'Content-Type': 'application/json'
              }
        }).then(()=>window.location.reload())//refresh the page)

        


    }

    render(){
        return(
            <Modal trigger={<Button basic color='black'>Edit</Button>} closeIcon size='tiny' centered={true}>
                <Header icon='edit' content='Edit this test' />
                <Modal.Content>
                    <List>
                        <List.Item>{React.createElement(CheckboxToggle, {label: 'Shuffle questions', checked: this.props.testData.shuffle, checkboxChanged: this.checkboxChanged})}</List.Item>
                        <List.Item>{React.createElement(CheckboxToggle, {label: 'Question feedback', checked: this.props.testData.feedback, checkboxChanged: this.checkboxChanged})}</List.Item>
                        <List.Item>{React.createElement(CheckboxToggle, {label: 'Show test result', checked: this.props.testData.showResult, checkboxChanged: this.checkboxChanged})}</List.Item>
                        <List.Item>{React.createElement(CheckboxToggle, {label: 'One way test completion', checked: this.props.testData.one_way, checkboxChanged: this.checkboxChanged})}</List.Item>
                        <List.Item>{React.createElement(DigitInput, {placeholder: 'Enter time...', content: 'mins', value: this.props.testData.time, inputChanged: this.inputChanged})}</List.Item>
                        <List.Item> {React.createElement(DigitInput, {placeholder: 'Enter # retries...', content: 'retrieves', value: this.props.testData.retrieves, inputChanged: this.inputChanged})}</List.Item>
                    </List>
                    <Button animated onClick={this.editBtn}>
                        <Button.Content visible>Edit</Button.Content>
                        <Button.Content hidden>
                            <Icon name='edit' />
                        </Button.Content>
                    </Button>
                </Modal.Content>

            </Modal>
        )
    }
}




class CheckboxToggle extends Component {

    constructor(props){
        super(props);
        
    }
    state = { checked: '' }

    componentDidMount(){
        this.setState({
            checked: this.props.checked
        })
    }

    toggle = () => {

        this.setState({ checked: !this.state.checked })

        if(this.props.label == 'Shuffle questions'){
            this.props.checkboxChanged('shuffle', this.state.checked);
        }
        else  if(this.props.label == 'One way test completion'){
            this.props.checkboxChanged('one-way', this.state.checked);
        }
        else  if(this.props.label == 'Question feedback'){
            this.props.checkboxChanged('feedback', this.state.checked);
        }
        else  if(this.props.label == 'Show test result'){
            this.props.checkboxChanged('showResult', this.state.checked);
        }
        
    }
  
    render() {
      return (
        <div>
          <Checkbox label={this.props.label} onChange={this.toggle} defaultChecked={this.props.checked} />
        </div>
      )
    }
  }

class DigitInput extends Component{
    constructor(props){
        super(props);
    }
    state= { 
        value: '',
    }

    componentDidMount(){
        this.setState({
            value: this.props.value
        })
    }

    newValue = (e)=>{
        this.setState({
            value: e.target.value
        }, ()=>{
            console.log("input changed. new value:",this.state)
            this.props.inputChanged(this.props.content, this.state.value);
        })
    }


    render(){
        return(
            <Input
              label={{ basic: true, content: this.props.content }}
              labelPosition='right'
              placeholder={this.props.placeholder}
              value={this.state.value}
              onChange={this.newValue}
            />
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