import React, { Component } from 'react';
import './Profile.scss';
import TeacherNavBar from './TeacherNavBar'
import myImage from '../../images/image.png'
import { Statistic, Image, Grid, Icon } from 'semantic-ui-react'
import { MockedData } from '../../api/mocks/mockedData'



class ProfileInfo extends Component{

  constructor(props){
    super(props);
    this.state={
      userData: []
    }
  }

  componentDidMount(){
    //fetch data
    const userInfo = MockedData.data.users[4];
    this.setState({
      userData: userInfo
    })
  }
    

    render(){
        //fetch data
        return(
            <div className = "profile-selector">
                <Image src={ myImage } size='small' centered circular />
                <h2>Welcome, Professor {this.state.userData.name}!</h2>
                <h4>Please select an item from the left side vertical menu</h4>
                <StatisticProfile></StatisticProfile>
            </div>
        )
    }
}

const StatisticProfile = () => (
    <Statistic.Group>
      <Statistic>
        <Statistic.Value>
          <Icon name='newspaper' />
          0
        </Statistic.Value>
        <Statistic.Label>Tests</Statistic.Label>
      </Statistic>
  
  
      <Statistic>
        <Statistic.Value>
          <Icon name='list' />
          0
        </Statistic.Value>
        <Statistic.Label>Question Categories</Statistic.Label>
      </Statistic>
  
      <Statistic>
        <Statistic.Value>
          <Icon name='question' />
          0
        </Statistic.Value>
        <Statistic.Label>Questions</Statistic.Label>
      </Statistic>

      <Statistic>
        <Statistic.Value>
          <Icon name='copy outline' />
          0
        </Statistic.Value>
        <Statistic.Label>Given Assignments</Statistic.Label>
      </Statistic>
    </Statistic.Group>
  )

class Profile extends Component{
    render(){
        return(
            <Grid>
                <Grid.Row>
                    <TeacherNavBar></TeacherNavBar>
                    <ProfileInfo></ProfileInfo>
                </Grid.Row>
            </Grid>
        )
    }
}



export default Profile;
