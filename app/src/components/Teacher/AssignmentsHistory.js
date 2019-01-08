import { Grid, Table, Card, Icon, Button, Modal, Header } from 'semantic-ui-react'
import React, { Component } from 'react';
import TeacherNavBar from './TeacherNavBar';
import _ from 'lodash'

import './AssignmentsHistory.scss';

class Statistics extends Component{
    render(){
        return(
        <div className="assignment-history">
            {testHistory.map((test,i)=>React.createElement(TestCard, {date: test.date}))}
        </div> 
        )
    }
}

const tableData = [
    { name: 'John', group: 15, score: 80 },
    { name: 'Daniel', group: 1415, score: 65 },
    { name: 'Johnny', group: 1511, score: 100 },
  ]

const testHistory = [
    {
        date: '12.11.2018',
        info: tableData
    },
    {
        date: '06.12.2018',
        info: tableData
    },
    {
        date: '16.12.2018',
        info: tableData
    }
]

const TestCard = (props) => (
    <Card>
        <Card.Content>
        <Icon name='tasks' size='big' />
        <Card.Header><strong>Date:</strong>{props.date}</Card.Header>
        </Card.Content>
        <Card.Content extra>
            <div className='ui three buttons'>
                <Modal trigger={<Button basic color='black'>Table</Button>} closeIcon>
                    <Header icon='list ul' content='Test results' />
                    <Modal.Content>
                        {React.createElement(AssignmentsHistoryTable, {data: tableData})}
                    </Modal.Content>
                </Modal>
                <Button basic color='black'>Statistics</Button>
            </div>
        </Card.Content>
    </Card>
)
  
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