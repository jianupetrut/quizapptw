package ro.ase.codinquiz.quizapplication.Main.Entities;

import java.io.Serializable;
import java.util.List;

public class Test implements Serializable {
    private int id;
    private String testName;
    private List<Question> questionList;
    private boolean shuffle;
    private boolean feedback;
    private boolean result;
    private boolean oneWay;
    private int time;
    private int retries;
    private boolean active;
    private int owner_id;

    public Test(int id, String testName, List<Question> questionList, boolean shuffle, boolean feedback, boolean result, int time, boolean oneWay) {
        this.id = id;
        this.testName = testName;
        this.questionList = questionList;
        this.shuffle = shuffle;
        this.feedback = feedback;
        this.result = result;
        this.time = time;
        this.oneWay = oneWay;
    }

    public Test() {
    }

    public Test(int id, String testName, List<Question> questionList, boolean shuffle, boolean feedback, boolean result, boolean oneWay, int time, int retries, boolean active, int owner_id) {

        this.id = id;
        this.testName = testName;
        this.questionList = questionList;
        this.shuffle = shuffle;
        this.feedback = feedback;
        this.result = result;
        this.oneWay = oneWay;
        this.time = time;
        this.retries = retries;
        this.active = active;
        this.owner_id = owner_id;
    }

    public int getOwner_id() {

        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public boolean isShuffle() {
        return shuffle;
    }

    public void setShuffle(boolean shuffle) {
        this.shuffle = shuffle;
    }

    public boolean isFeedback() {
        return feedback;
    }

    public void setFeedback(boolean feedback) {
        this.feedback = feedback;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public boolean isOneWay() {
        return oneWay;
    }

    public void setOneWay(boolean oneWay) {
        this.oneWay = oneWay;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getRetries() {
        return retries;
    }

    public void setRetries(int retries) {
        this.retries = retries;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Test(int id, String testName, List<Question> questionList, boolean shuffle, boolean feedback, boolean result, boolean oneWay, int time, int retrieve, boolean active) {

        this.id = id;
        this.testName = testName;
        this.questionList = questionList;
        this.shuffle = shuffle;
        this.feedback = feedback;
        this.result = result;
        this.oneWay = oneWay;
        this.time = time;
        this.retries = retrieve;
        this.active = active;
    }
}
