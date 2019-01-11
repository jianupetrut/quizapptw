import { Grid, Card, Icon, Button, Modal, Header, Input, Checkbox, List } from 'semantic-ui-react'
import React, { Component } from 'react';
import TeacherNavBar from './TeacherNavBar';
import './ExistingTests.scss';
import { MockedData } from '../../api/mocks/mockedData'

class Slideshow extends Component{

    constructor(props){
        super(props);
        this.state={
            tests:[]
        }
    }

    componentDidMount(){
        //fetch data
        const userID = MockedData.data.users[4].id;
        const tests = MockedData.data.tests;

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

    }

    deactivateTest = () =>{
        console.log("deactivate/activate test clicked!")
    }

    render(){
        return(
            <div className="existing-tests">
                {this.state.tests.map((test,i)=>React.createElement(TestCard, {header: test.test, isActive: test.isActive, deactivateTest: this.deactivateTest, testInfo: test}))}
                
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

            <ActivateTestModal isActive={props.isActive} deactivateTest={props.deactivateTest} ></ActivateTestModal>
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

    state = { modalOpen: false }

    handleClose = () => this.setState({ modalOpen: false })
    

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
                <Button color='green' onClick = {this.props.deactivateTest}>
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

        }
    }
    render(){
        return(
            <Modal trigger={<Button basic color='black'>Edit</Button>} closeIcon size='tiny' centered={true}>
                <Header icon='edit' content='Edit this test' />
                <Modal.Content>
                    <List>
                        <List.Item>{React.createElement(CheckboxToggle, {label: 'Shuffle questions', checked: this.props.testData.shuffle})}</List.Item>
                        <List.Item>{React.createElement(CheckboxToggle, {label: 'Question feedback', checked: this.props.testData.feedback})}</List.Item>
                        <List.Item>{React.createElement(CheckboxToggle, {label: 'Show test result', checked: this.props.testData.result})}</List.Item>
                        <List.Item>{React.createElement(CheckboxToggle, {label: 'One way test completion', checked: this.props.testData.one_way})}</List.Item>
                        <List.Item>{React.createElement(DigitInput, {placeholder: 'Enter time...', content: 'mins', value: this.props.testData.time})}</List.Item>
                        <List.Item> {React.createElement(DigitInput, {placeholder: 'Enter # retries...', content: 'retrieves', value: this.props.testData.retrieves})}</List.Item>
                    </List>
                    <Button animated>
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
    state = { checked: false }

    toggle = () => this.setState({ checked: !this.state.checked })
  
    render() {
      return (
        <div>
          <Checkbox label={this.props.label} onChange={this.toggle} defaultChecked={this.props.checked} />
        </div>
      )
    }
  }

const DigitInput = (props) => (
    <Input
      label={{ basic: true, content: props.content }}
      labelPosition='right'
      placeholder={props.placeholder}
      value={props.value}
    />
  )
 

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