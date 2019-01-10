import React, { Component } from 'react';
import Profile from './MyProfile';
import NewAssignment from './NewAssignment';
import { Switch, Route } from 'react-router-dom';
import NewTest from './Test';
import FinishTest from './FinishTest';


class IndexStudent extends Component{
    render() {
        return(
        <div>
            <Switch>
                <Route exact path='/student/profile' component={Profile}/>
                <Route exact path='/student/assignment' component={NewAssignment}/>
                <Route exact path='/student/assignment/new' component={NewTest}/>
                <Route exact path='/student/assignment/finish' component={FinishTest}/>
            </Switch>
        </div>
        );
    }
}

export default IndexStudent;

