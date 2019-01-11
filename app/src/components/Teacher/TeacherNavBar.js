import React, { Component } from 'react'
import { Menu } from 'semantic-ui-react'
import './TeacherNavBar.scss'
import { NavLink } from 'react-router-dom';

class TeacherNavBar extends Component {
  handleItemClick = name => this.setState({ activeItem: name })

  render() {
    const { activeItem } = this.state || {}

    return (
      <div className="teacher-navbar">
      <Menu vertical>
        <Menu.Item>
          <Menu.Header>General</Menu.Header>

          <Menu.Menu>
            <Menu.Item
              name='My Profile'
              active={activeItem === 'myprofile'}
              onClick={this.handleItemClick}
              as={NavLink} exact to="/teacher/profile"
            />
          </Menu.Menu>
        </Menu.Item>

        <Menu.Item>
          <Menu.Header>Tests</Menu.Header>

          <Menu.Menu>
            <Menu.Item
              name='Create a new test'
              active={activeItem === 'newtest'}
              onClick={this.handleItemClick}
              as={NavLink} exact to="/teacher/create-test"
            />
            <Menu.Item
              name='Existing tests'
              active={activeItem === 'existingtests'}
              onClick={this.handleItemClick}
              as={NavLink} exact to="/teacher/existing-tests"
            />
          </Menu.Menu>
          
        </Menu.Item>
        <Menu.Item>
          <Menu.Header>Questions</Menu.Header>

          <Menu.Menu>
            <Menu.Item
              name='Create a new question'
              active={activeItem === 'newquestion'}
              onClick={this.handleItemClick}
              as={NavLink} exact to="/teacher/create-question"
            />
            <Menu.Item
              name='Existing questions'
              active={activeItem === 'existingquestions'}
              onClick={this.handleItemClick}
              as={NavLink} exact to="/teacher/existing-questions"
            />
          </Menu.Menu>
          
        </Menu.Item>
        <Menu.Item>
          <Menu.Header>Statistics</Menu.Header>

          <Menu.Menu>
            <Menu.Item
              name='Assignments History'
              active={activeItem === 'assignmentshistory'}
              onClick={this.handleItemClick}
              as={NavLink} exact to="/teacher/assignments-history"
            />
          </Menu.Menu>
          
        </Menu.Item>
        <Menu.Item>
        </Menu.Item>
      </Menu>
      </div>
    )
  }
}

export default TeacherNavBar;