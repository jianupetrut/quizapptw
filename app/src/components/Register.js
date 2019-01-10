import React, { Component } from 'react';
import './Register.scss';
import { Button, Form, Select, Icon } from 'semantic-ui-react'


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
                    <Form.Field>
                        <label>Name</label>
                        <input placeholder='Name' />
                    </Form.Field>
                    <Form.Field>
                        <label>Username</label>
                        <input placeholder='Username' />
                    </Form.Field>
                    <Form.Field>
                        <label>Password</label>
                        <input placeholder='Password' type='password' />
                    </Form.Field>
                    <Form.Field>
                        <label>Group</label>
                        <input placeholder='Group' />
                    </Form.Field>
                    <Form.Field
                    control={Select}
                    options={Options}
                    label='Role'
                    placeholder='Role'
                    onChange={(e, { value }) => {this.role = value;}}
                    />
                        

                    <Button icon labelPosition='right' type="submit" onClick = {() => this.navigate(this.role)}>
                    Register
                    <Icon name='right arrow' />
                    </Button>


    
                </Form>
            </div>
          )
      }
  }
  
  export default LoginForm;
