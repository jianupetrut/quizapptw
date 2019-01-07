import React, { Component } from 'react';
import './Profile.scss';
import TeacherNavBar from './TeacherNavBar'
import myImage from '../../images/image.png'
import { Segment, Image, Grid } from 'semantic-ui-react'




class ProfileInfo extends Component{
    render(){
        return(
            <Segment>
                <Image src={ myImage } size='small' centered circular />
                <h2>Welcome, Professor John Doe!</h2>
                <h4>Please select an item from the left side vertical menu</h4>
            </Segment>
        )
    }
}

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
