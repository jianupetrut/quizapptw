import { Grid, Table, Card, Icon, Button, Modal, Header } from 'semantic-ui-react'
import React, { Component } from 'react';
import TeacherNavBar from './TeacherNavBar';
import _ from 'lodash'
import { MockedData } from '../../api/mocks/mockedData'

import './AssignmentsHistory.scss';

class Statistics extends Component{

    constructor(props){
        super(props);
        this.state={
            relevantTests:[],
            relevantFinishedTests:[]
        }
    }

    componentDidMount(){


        //fetch data here
        const finishedTests = MockedData.data.finished_tests;
        const userID = MockedData.data.users[4].id;
        const allTests = MockedData.data.tests;

        const relevantTests = allTests.filter(test=>{
            if(test.owner_id == userID){
                return true;
            }
        })
        console.log("Relevant Tests", relevantTests);
        console.log("Finished tests", finishedTests);

        const testsID = [];
        relevantTests.map(test=>testsID.push(test.id));

        console.log('testsID', testsID);

        const relevantFinishedTests = finishedTests.filter(test=>{
            if(testsID.includes(test.test_id)){
                return true;
            }
            return false;
        })

        console.log("relevant finished tests", relevantFinishedTests);

        this.setState({
            relevantTests: relevantTests,
            relevantFinishedTests: relevantFinishedTests
        })

    }

    render(){
        return(
        <div className="assignment-history">
            {this.state.relevantTests.map((test,i)=>React.createElement(TestCard, {data: this.state.relevantFinishedTests, header: test.test, currentTestID: test.id}))}
        </div> 
        )
    }
}


class TestCard extends Component{
    constructor(props){
        super(props);
    }


    goodData = this.props.data.filter(item=>{
        if(item.test_id === this.props.currentTestID){
            return true;
        }
    })



    render(){

        return(
            <Card>
                <Card.Content>
                    <Icon name='tasks' size='big' />
                </Card.Content>
                <Card.Content extra>
                <Card.Header>{this.props.header}</Card.Header>
                    <div className='ui three buttons'>
                        <Modal trigger={<Button basic color='black'>Table</Button>} closeIcon>
                            <Header icon='list ul' content='Test results' />
                            <Modal.Content>
                                {
                                    React.createElement(AssignmentsHistoryTable, {data: this.goodData, currentTestID: this.props.currentTestID})
                                }
                            </Modal.Content>
                        </Modal>
                        <Button basic color='black'>Statistics</Button>
                    </div>
                </Card.Content>
            </Card>
        )
    }
} 
  
class AssignmentsHistoryTable extends Component {

    constructor(props){
        super(props);

    }

    state = {
        column: null,
        data: this.props.data,
        direction: null,
    }



    handleSort = clickedColumn => () => {
        const { column, data, direction } = this.state

        if (column !== clickedColumn) {
        this.setState({
            column: clickedColumn,
            data: _.sortBy(data, [clickedColumn]),
            direction: 'ascending',
        })

        return
        }

        this.setState({
            data: data.reverse(),
            direction: direction === 'ascending' ? 'descending' : 'ascending',
        })
    }

    render() {
        const { column, data, direction } = this.state

        return (
        <Table sortable celled fixed>
            <Table.Header>
            <Table.Row>
                <Table.HeaderCell
                sorted={column === 'name' ? direction : null}
                onClick={this.handleSort('name')}
                >
                Name
                </Table.HeaderCell>
                <Table.HeaderCell
                sorted={column === 'group' ? direction : null}
                onClick={this.handleSort('group')}
                >
                Group
                </Table.HeaderCell>
                <Table.HeaderCell
                sorted={column === 'score' ? direction : null}
                onClick={this.handleSort('score')}
                >
                Score
                </Table.HeaderCell>
            </Table.Row>
            </Table.Header>
            <Table.Body>
            {_.map(data, ({ name, group, score }) => (
                <Table.Row key={name}>
                <Table.Cell>{name}</Table.Cell>
                <Table.Cell>{group}</Table.Cell>
                <Table.Cell>{score}</Table.Cell>
                </Table.Row>
            ))}
            </Table.Body>
        </Table>
        )
    }
}

class AssignmentsHistory extends Component{
    render(){
        return(
            <Grid>
                <Grid.Row>
                    <TeacherNavBar></TeacherNavBar>
                    <Statistics></Statistics>
                </Grid.Row>
            </Grid>
        )
    }
}

export default AssignmentsHistory;