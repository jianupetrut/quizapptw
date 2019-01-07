import React, { Component } from 'react';
import './App.css';
import IndexStudent from './Student/IndexStudent';
import Switch from 'react-router-dom/Switch';
import Route from 'react-router-dom/Route';
import LoginForm from './Login';
import IndexTeacher from './Teacher/IndexTeacher';

class App extends Component {
  render() {
    return (
     <Switch>
       <Route path='/login' component={LoginForm}></Route>
       <Route path='/student' component={IndexStudent}></Route>
       <Route path='/teacher' component={IndexTeacher}></Route>
     </Switch>
    );
  }
}

export default App;
