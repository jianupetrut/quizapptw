import React, { Component } from 'react'
import { Menu } from 'semantic-ui-react'
import './StudentNavBar.css'
import { NavLink } from 'react-router-dom';

class StudentNavBar extends Component {
  handleItemClick = name => this.setState({ activeItem: name })

  render() {
    const { activeItem } = this.state || {}

    return (
      <Menu vertical>
        <Menu.Item>
          <Menu.Header>General</Menu.Header>

          <Menu.Menu>
            <Menu.Item
              name='My Profile'
              active={activeItem === 'myprofile'}
              onClick={this.handleItemClick}
              as={NavLink} exact to="/student/profile"
            />
          </Menu.Menu>
        </Menu.Item>

        <Menu.Item>
          <Menu.Header>Assignments</Menu.Header>

          <Menu.Menu>
            <Menu.Item
              name='New Assignment'
              active={activeItem === 'newassignment'}
              onClick={this.handleItemClick}
              as={NavLink} exact to="/student/assignment"
            />
          </Menu.Menu>
        </Menu.Item>
        <Menu.Item>
        </Menu.Item>
      </Menu>
    )
  }
}

export default StudentNavBar;