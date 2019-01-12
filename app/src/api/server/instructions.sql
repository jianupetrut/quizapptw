CREATE TABLE QuestionCategories(
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY, 
    category VARCHAR(255) NOT NULL
    ); 

CREATE TABLE Questions(
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY, 
    question_category_id INT NOT NULL, 
    question VARCHAR(255), image TEXT,
    CONSTRAINT FK_QuestionCategories FOREIGN KEY (question_category_id) REFERENCES QuestionCategories(id)
    );

CREATE TABLE Users(
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(255), 
    username VARCHAR(255), 
    hashedPassword VARCHAR(255), 
    group_no INT, 
    isStudent BOOLEAN
    );

CREATE TABLE Tests(
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY, 
    test VARCHAR(255), 
    questions_id VARCHAR(500),
    shuffle BOOLEAN, 
    one_way BOOLEAN,
    retrieves INT,
    feedback BOOLEAN,
    showResult BOOLEAN,
    time INT, 
    owner_id INT, 
    isActive BOOLEAN
    );

CREATE TABLE Answers(
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY, 
    answer VARCHAR(255), 
    question_id INT, 
    isCorrect BOOLEAN
);

CREATE TABLE FinishedTests(
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY, 
    name VARCHAR(255), 
    username VARCHAR(255), 
    test_id INT, 
    isCorrect BOOLEAN,
    score INT,
    date VARCHAR(255)
);

ALTER TABLE Questions ADD FOREIGN KEY (question_category_id) REFERENCES QuestionCategories(id);
    