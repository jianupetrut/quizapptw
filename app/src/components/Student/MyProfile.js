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
      this.state = {
        column: null,
        data: this.props.grades,
        direction: null,
      }
    }

    static getDerivedStateFromProps(props, state){
      return{
        data: props.grades
      }
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
    this.state={
      userData: '',
      finishedTests: [],
      userTests: []
    }
  }

  componentDidMount(){

    Promise.all([
      fetch('https://quiz-app-api-georgedobrin.c9users.io/api/users/1').then(res => res.json()),
      fetch('https://quiz-app-api-georgedobrin.c9users.io/api/finished_tests').then(res => res.json())
    ])
    .then(responses => {
      console.log('responses', responses)
      this.setState({
        userData: responses[0],
        finishedTests: responses[1]
      })
    })
    .then(() => {
      const userTests = this.state.finishedTests.filter(test=>{
        if(test.username === this.state.userData.username){
          return true;
        }
      })
      console.log('User tests', userTests)

      this.setState({
        userTests: userTests
      })

      console.log('user tests state', this.state.userTests)
    })

   
  }

    render(){
      console.log('user tests situation', this.state.userTests)
        return(
            <div className = "myprofile-selector">
                <Image src={ myImage } size='small' centered circular />
                <h2>Name: {this.state.userData.name}</h2>
                <h4>Role: Student</h4>
                <h4>Group: {this.state.userData.group}</h4>
                <SummaryTable grades={ this.state.userTests }></SummaryTable>
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
