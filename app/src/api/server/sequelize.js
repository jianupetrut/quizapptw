const Sequelize = require('sequelize')

const sequelize = new Sequelize('codindb', 'root', 'root', {
  host: 'localhost',// ?
  dialect: 'mysql'
})

const QuestionCategories = sequelize.define('question_categories', {
    id: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    category: {
        type: Sequelize.STRING(150),
        validate : {
			len : [1, 150]
        },
        allowNull: false
    }
})

const Questions = sequelize.define('questions', {
    id: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    question_category_id: {
        type: Sequelize.INTEGER,
        allowNull: false,
        references: {
            model: QuestionCategories,
            key: 'id',
        }
    },
    question: {
        type: Sequelize.STRING(150),
        allowNull: false
    },
    image: {
        type: Sequelize.TEXT   
    }
})

const Users = sequelize.define('users', {
    id: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    name: {
        type: Sequelize.STRING(100),
        validate : {
			len : [3, 100]
        },
        allowNull: false
    },
    username: {
        type: Sequelize.STRING(100),
        validate : {
			len : [3, 100]
        },
        allowNull: false
    },
    hashedPassword: {
        type: Sequelize.STRING(100),
        allowNull: false
    },
    group: {
        type: Sequelize.INTEGER
    },
    isStudent: {
        type: Sequelize.BOOLEAN,
        allowNull: false
    }
})

const Tests = sequelize.define('tests', {
    id: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    test: {
        type: Sequelize.STRING(100),
        validate : {
			len : [1, 100]
        },
        allowNull: false
    },
    questions_id: {
        //Sequelize does not have Array for mySQL, so i have to join and split all the ids
        type: Sequelize.INTEGER,
        get() {
            return this.getDataValue('questions_id').split(';')
        },
        set(val) {
           this.setDataValue('questions_id', val.join(';'));
        }
    },
    shuffle: {
        type: Sequelize.BOOLEAN,
        allowNull: false
    },
    one_way: {
        type: Sequelize.BOOLEAN,
        allowNull: false
    },
    feedback: {
        type: Sequelize.BOOLEAN,
        allowNull: false
    },
    showResult: {
        type: Sequelize.BOOLEAN,
        allowNull: false
    },
    isActive: {
        type: Sequelize.BOOLEAN,
        allowNull: false
    },
    retrieves: {
        type: Sequelize.INTEGER,
    },
    time: {
        type: Sequelize.INTEGER,
        allowNull: false
    },
    owner_id: {
        type: Sequelize.INTEGER,
        allowNull: false,
        references: {
            model: Users,
            key: 'id',
        }
    }

})

const Answers = sequelize.define('answers', {
    id: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    answer: {
        type : Sequelize.STRING(100),
		allowNull : false,
		validate : {
			len : [3, 100]
		}
    },
    isCorrect: {
        type: Sequelize.BOOLEAN,
        allowNull: false
    },
    question_id: {
        type: Sequelize.INTEGER,
        allowNull : false,
        references: {
            model: Questions,
            key: 'id',
        }
    }
})

const FinishedTests = sequelize.define('finished_tests', {
    id: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    test_id: {
        type: Sequelize.INTEGER,
        allowNull: false,
        references: {
            model: Tests,
            key: 'id',
        }
    },
    username: {
        type: Sequelize.STRING(100),
        validate : {
			len : [3, 100]
		}
    },
    name: {
        type: Sequelize.STRING(100),
        validate : {
			len : [3, 100]
		}
    },
    date:{
        type: Sequelize.STRING(100)
    },
    score:{
        type: Sequelize.INTEGER
    }
})








sequelize.sync({ force: true })
  .then(() => {
    console.log('Database & tables created!')
  })

module.exports = {
  Answers,
  FinishedTests,
  QuestionCategories,
  Questions,
  Users,
  Tests
}