import React, { Component } from 'react';
import Profile from './Profile';
import CreateTest from './CreateTest';
import ExistingTests from './ExistingTests';
import CreateQuestion from './CreateQuestion';
import ExistingQuestions from './ExistingQuestions';
import AssignmentsHistory from './AssignmentsHistory';
import ActiveTests from './ActiveTests';
import { Switch, Route } from 'react-router-dom';


class IndexTeacher extends Component{
    render() {
        return(
        <div>
            <Switch>
                <Route exact path='/teacher/profile' component={Profile}/>
                <Route exact path='/teacher/active-tests' component={ActiveTests}/>
                <Route exact path='/teacher/create-test' component={CreateTest}/>
                <Route exact path='/teacher/existing-tests' component={ExistingTests}/>
                <Route exact path='/teacher/create-question' component={CreateQuestion}/>
                <Route exact path='/teacher/existing-questions' component={ExistingQuestions}/>
                <Route exact path='/teacher/assignments-history' component={AssignmentsHistory}/>
            </Switch>
        </div>
        );
    }
}

export default IndexTeacher;

