export const MockedData = 
{
    "data": {
        "tests":
            [
                {
                    "id": 0,
                    "test": "General Recap - Seminar 6",
                    "questions_id": [0, 1, 2, 3],
                    "shuffle": false,
                    "one-way": true,
                    "retrieves": 0,
                    "feedback": false,
                    "result": true,
                    "time": 30,
                    "isActive": false
                },
                {
                    "id": 1,
                    "test": "Quiz for seminar 9",
                    "questions_id": [2, 3],
                    "shuffle": false,
                    "one-way": true,
                    "retrieves": 0,
                    "feedback": false,
                    "result": false,
                    "time": 20,
                    "isActive": false
                }
            ],
        "questions":
        [
            {
                "id": 0,
                "question_category_id": 0,
                "question": "What does HTML stand for?",
                "image": ""
            },
            {
                "id": 1,
                "question_category_id": 1,
                "question": "What does R.id.android do?",
                "image": ""
            },
            {
                "id": 2,
                "question_category_id": 2,
                "question": "What does SSE stand for?",
                "image": ""
            },
            {
                "id": 3,
                "question_category_id": 3,
                "question": "What does PCA stand for?",
                "image": ""
            }
        
        ],
        "answers":
        [
            {
                "id": 0,
                "answer": "Hyper text markup language",
                "isCorrect": true,
                "question_id": 0
            },
            {
                "id": 1,
                "answer": "Hyper text language",
                "isCorrect": false,
                "question_id": 0
            },
            {
                "id": 2,
                "answer": "markup language",
                "isCorrect": false,
                "question_id": 0
            },
            {
                "id": 3,
                "answer": "language",
                "isCorrect": false,
                "question_id": 0
            },
            {
                "id": 4,
                "answer": "Android function for getting the ID",
                "isCorrect": true,
                "question_id": 1
            },
            {
                "id": 5,
                "answer": "function",
                "isCorrect": false,
                "question_id": 1
            },
            {
                "id": 6,
                "answer": "Android",
                "isCorrect": false,
                "question_id": 1
            },
            {
                "id": 7,
                "answer": "This is not correct",
                "isCorrect": false,
                "question_id": 1
            },
            {
                "id": 8,
                "answer": "Service",
                "isCorrect": false,
                "question_id": 2
            },
            {
                "id": 9,
                "answer": "Sum of squared errors",
                "isCorrect": true,
                "question_id": 2
            },
            {
                "id": 10,
                "answer": "False answer",
                "isCorrect": false,
                "question_id": 2
            },
            {
                "id": 11,
                "answer": "Do not choose this",
                "isCorrect": false,
                "question_id": 2
            },
            {
                "id": 12,
                "answer": "NOT THIS",
                "isCorrect": false,
                "question_id": 3
            },
            {
                "id": 13,
                "answer": "Wrong answer !!!!!!!!!!!!!",
                "isCorrect": false,
                "question_id": 3
            },
            {
                "id": 14,
                "answer": "Nope",
                "isCorrect": false,
                "question_id": 3
            },
            {
                "id": 15,
                "answer": "Principal component analysis",
                "isCorrect": true,
                "question_id": 3
            }
        ],
        "question_categories":
        [
            {
                "id": 0,
                "category": "Web Technologies"
            },
            {
                "id": 1,
                "category": "Android"
            },
            {
                "id": 3,
                "category": "Econometrics"
            },
            {
                "id": 4,
                "category": "Data Analysis"
            }
        ],
        "finished_tests":
        [
            {
                "id": 0,
                "test_id": 0,
                "username": "user0",
                "date": "09.01.2018",
                "score": 80
            },
            {
                "id": 1,
                "test_id": 0,
                "username": "user1",
                "date": "09.01.2018",
                "score": 90
            },
            {
                "id": 2,
                "test_id": 0,
                "username": "user2",
                "date": "09.01.2018",
                "score": 60
            },
            {
                "id": 3,
                "test_id": 1,
                "username": "user3",
                "date": "09.01.2018",
                "score": 40
            },
            {
                "id": 4,
                "test_id": 1,
                "username": "user4",
                "date": "09.01.2018",
                "score": 100
            }
        ],
        "users":
        [
            {
                "id": 0,
                "name": "Student Zero",
                "username": "stud0",
                "hashedPassword": "",
                "group": 1077,
                "isStudent": true
            },
            {
                "id": 1,
                "name": "Student One",
                "username": "stud1",
                "hashedPassword": "",
                "group": 1077,
                "isStudent": true
            },
            {
                "id": 2,
                "name": "Student Two",
                "username": "stud2",
                "hashedPassword": "",
                "group": 1078,
                "isStudent": true
            },
            {
                "id": 3,
                "name": "Student Three",
                "username": "stud3",
                "hashedPassword": "",
                "group": 1055,
                "isStudent": true
            },
            {
                "id": 4,
                "name": "Teacher Zero",
                "username": "teacher0",
                "hashedPassword": "",
                "group": 0,
                "isStudent": false
            }
        ]
        }
    }

