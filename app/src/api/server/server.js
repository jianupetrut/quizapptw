'use strict'
const express = require('express')
const bodyParser = require('body-parser')
const { Answers, FinishedTests, QuestionCategories, Questions, Tests, Users } = require('./sequelize')

const app = express()
app.use(bodyParser.json())

//endpoints
const port = 6000
app.listen(port, () => {
    console.log(`Running on http://localhost:${port}`)
})

app.get('/api/users', (req, res) => {
    Users.findAll().then(users => res.json(users))
})