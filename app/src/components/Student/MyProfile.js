import _ from 'lodash'
import React, { Component } from 'react';
import './MyProfile.scss';
import StudentNavBar from './StudentNavBar'
import myImage from '../../images/image.png'
import { Image, Grid, Table } from 'semantic-ui-react'


const tableData = [
    { number: '1', test: 'Test 1', date: '12.10.2018', score: 90 },
    { number: '2', test: 'Test 2', date: '23.11.2018', score: 60 },
    { number: '3', test: 'Test 3', date: '02.12.2018', score: 84 },
    { number: '4', test: 'Test 4', date: '12.12.2018', score: 94 },
  ]
  
  class SummaryTable extends Component {
    state = {
      column: null,
      data: tableData,
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
                sorted={column === 'number' ? direction : null}
                onClick={this.handleSort('number')}
              >
                #
              </Table.HeaderCell>
              <Table.HeaderCell
                sorted={column === 'test' ? direction : null}
                onClick={this.handleSort('test')}
              >
                Test
              </Table.HeaderCell>
              <Table.HeaderCell
                sorted={column === 'date' ? direction : null}
                
              >
                Date
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
            {_.map(data, ({ number, test, date, score }) => (
              <Table.Row key={number}>
                <Table.Cell>{number}</Table.Cell>
                <Table.Cell>{test}</Table.Cell>
                <Table.Cell>{date}</Table.Cell>
                <Table.Cell>{score}</Table.Cell>
              </Table.Row>
            ))}
          </Table.Body>
        </Table>
      )
    }
  }



class ProfileInfo extends Component{
    render(){
        return(
            <div className = "myprofile-selector">
                <Image src={ myImage } size='small' centered circular />
                <h2>Name: John Doe</h2>
                <h4>Role: Student</h4>
                <h4>Group: 1077</h4>
                <SummaryTable></SummaryTable>
            </div>
        )
    }
}

class MyProfile extends Component{
    render(){
        return(
            <Grid>
                <Grid.Row>
                    <StudentNavBar></StudentNavBar>
                    <ProfileInfo></ProfileInfo>
                </Grid.Row>
            </Grid>
        )
    }
}



export default MyProfile;
