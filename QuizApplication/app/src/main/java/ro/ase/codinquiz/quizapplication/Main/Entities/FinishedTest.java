package ro.ase.codinquiz.quizapplication.Main.Entities;

import java.util.Date;

public class FinishedTest {
    private int id;
    private int test_id;
    private int score;
    private String username;
    private String testName;
    private Date date;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public FinishedTest(int id, int test_id, int score, String username, String testName, Date date) {

        this.id = id;
        this.test_id = test_id;
        this.score = score;
        this.username = username;
        this.testName = testName;
        this.date = date;

    }

    public FinishedTest() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTest_id() {
        return test_id;
    }

    public void setTest_id(int name) {
        this.test_id = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
