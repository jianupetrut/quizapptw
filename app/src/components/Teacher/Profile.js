import React, { Component } from 'react';
import './Profile.scss';
import TeacherNavBar from './TeacherNavBar'
import myImage from '../../images/image.png'
import { Statistic, Image, Grid, Icon } from 'semantic-ui-react'




class ProfileInfo extends Component{
    render(){
        return(
            <div className = "profile-selector">
                <Image src={ myImage } size='small' centered circular />
                <h2>Welcome, Professor John Doe!</h2>
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
