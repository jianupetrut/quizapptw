import React, { Component } from 'react';
import './Login.scss';
import { Button, Form, Select, Image, Icon } from 'semantic-ui-react'
import myImage from '../images/logo.jpeg';


const Options = [
    { key: 't', text: 'Teacher', value: 'teacher' },
    { key: 's', text: 'Student', value: 'student' },
  ]




  class LoginForm extends Component{
    
    role = '';

    navigate = function(role){
        if(role == 'student'){
            this.props.history.push('/student/profile');
        }
        else if(role == 'teacher'){
            this.props.history.push('/teacher/profile')
        }
        
    }

      render(){
          return(
            <div className="component-login">
                <Form>
                <Image src={ myImage } size='small' />
                <Form.Field>
                    <label className='labelRed'>Username</label>
                    <input placeholder='Username' />
                </Form.Field>
                <Form.Field>
                    <label>Password</label>
                    <input placeholder='Password' type='password' />
                </Form.Field>
                <Form.Field
                control={Select}
                options={Options}
                label='Role'
                placeholder='Role'
                onChange={(e, { value }) => {this.role = value;}}
                />
                    

                <Button icon labelPosition='right' type="submit" onClick = {() => this.navigate(this.role)}>
                Log In
                <Icon name='right arrow' />
                </Button>


    
                </Form>
            </div>
          )
      }
  }
  
  export default LoginForm;
