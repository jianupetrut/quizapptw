import _ from 'lodash'
import React, { Component } from 'react';
import './MyProfile.scss';
import StudentNavBar from './StudentNavBar'
import myImage from '../../images/image.png'
import { Image, Grid, Table } from 'semantic-ui-react'
import {MockedData} from '../../api/mocks/mockedData'


  
  class SummaryTable extends Component {

    constructor(props){
      super(props);
    }

    state = {
      column: null,
      data: this.props.grades,
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
                sorted={column === 'test_id' ? direction : null}
                onClick={this.handleSort('test_id')}
              >
                Test ID
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
            {_.map(data, ({ test_id, date, score }) => (
              <Table.Row key={test_id}>
                <Table.Cell>{test_id}</Table.Cell>
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

  constructor(props){
    super(props);

  }



    render(){
      //fetch data

      const userData = MockedData.data.users[0];//get user
      const finishedTests = MockedData.data.finished_tests; //get all finished tests

      //find user test results
      const userTests = finishedTests.filter(test=>{
        if(test.username === userData.username){
          return true;
        }
      })

      console.log("userTests", userTests);


        return(
            <div className = "myprofile-selector">
                <Image src={ myImage } size='small' centered circular />
                <h2>Name: {userData.name}</h2>
                <h4>Role: Student</h4>
                <h4>Group: {userData.group}</h4>
                <SummaryTable grades={ userTests }></SummaryTable>
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
