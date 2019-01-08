import { Grid, Card, Icon, Button, Modal, Header, Input, Checkbox, List } from 'semantic-ui-react'
import React, { Component } from 'react';
import TeacherNavBar from './TeacherNavBar';
import './ExistingTests.scss';

class Slideshow extends Component{
    render(){
        return(
            <div className="existing-tests">
                {tests.map((test,i)=>React.createElement(TestCard, {header: test.text, date: test.creationDate}))}
                
            </div>
        )
    }
}

const tests = [
    {
        text:"Test 1",
        value:"test 1",
        creationDate:"23.10.2018"
    },
    {
        text:"Test 2",
        value:"test 2",
        creationDate:"02.11.2018"
    },
    {
        text:"Test 3",
        value:"test 3",
        creationDate:"08.12.2018"
    }
]

const TestCard = (props) => (
    <Card>
        <Card.Content>
        <Icon name='tasks' size='big' />
        <Card.Header>{props.header}</Card.Header>
        <Card.Description>
            <strong>Creation Date:</strong>{props.date}
        </Card.Description>
        </Card.Content>
        <Card.Content extra>

        <div className='ui three buttons'>

            <Modal trigger={<Button basic color='black'>Activate</Button>} closeIcon>
                <Header icon='lock open' content='Activate this test' />
                <Modal.Content>
                <p>
                    Do you want to activate this test?
                </p>
                </Modal.Content>
                <Modal.Actions>
                <Button color='red'>
                    <Icon name='remove' /> No
                </Button>
                <Button color='green'>
                    <Icon name='checkmark' /> Yes
                </Button>
                </Modal.Actions>
            </Modal>

            <Modal trigger={<Button basic color='black'>Edit</Button>} closeIcon size='tiny' centered={true}>
                <Header icon='edit' content='Edit this test' />
                <Modal.Content>
                    <List>
                        <List.Item>{React.createElement(CheckboxToggle, {label: 'Shuffle questions and answers'})}</List.Item>
                        <List.Item>{React.createElement(CheckboxToggle, {label: 'Question feedback'})}</List.Item>
                        <List.Item>{React.createElement(CheckboxToggle, {label: 'Show test result'})}</List.Item>
                        <List.Item>{React.createElement(CheckboxToggle, {label: 'One way test completion'})}</List.Item>
                        <List.Item>{React.createElement(DigitInput, {placeholder: 'Enter time...', content: 'mins'})}</List.Item>
                        <List.Item> {React.createElement(DigitInput, {placeholder: 'Enter # retries...', content: 'retrieves'})}</List.Item>
                    </List>
                    <ButtonAnimated></ButtonAnimated>
                </Modal.Content>

            </Modal>

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

const CheckboxToggle = (props) => <Checkbox toggle label={props.label}/>
const DigitInput = (props) => (
    <Input
      label={{ basic: true, content: props.content }}
      labelPosition='right'
      placeholder={props.placeholder}
    />
  )
const ButtonAnimated = ()=>    <Button animated>
  <Button.Content visible>Edit</Button.Content>
  <Button.Content hidden>
  <Icon name='edit' />
  </Button.Content>
</Button>

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