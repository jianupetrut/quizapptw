import React, { Component } from 'react';
import Profile from './MyProfile';
import NewAssignment from './NewAssignment';
import { Switch, Route } from 'react-router-dom';


class IndexStudent extends Component{
    render() {
        return(
        <div>
            <Switch>
                <Route exact path='/student/profile' component={Profile}/>
                <Route exact path='/student/assignment' component={NewAssignment}/>
            </Switch>
        </div>
        );
    }
}

export default IndexStudent;

