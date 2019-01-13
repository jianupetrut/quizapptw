package ro.ase.codinquiz.quizapplication.Main.APIFunctionsAndWorkers;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import ro.ase.codinquiz.quizapplication.Main.Entities.Answer;
import ro.ase.codinquiz.quizapplication.Main.Entities.Category;
import ro.ase.codinquiz.quizapplication.Main.Entities.FinishedTest;
import ro.ase.codinquiz.quizapplication.Main.Entities.Question;
import ro.ase.codinquiz.quizapplication.Main.Entities.Test;
import ro.ase.codinquiz.quizapplication.Main.Entities.User;

public class APIFunctions {
    private static final String questionsAddress="https://quiz-app-api-georgedobrin.c9users.io/api/questions/%d";
    private static final String categoryAddress="https://quiz-app-api-georgedobrin.c9users.io/api/question_categories/%d";
    private static final String finishedTestAddress="https://quiz-app-api-georgedobrin.c9users.io/api/finished_tests/owner_id/%d";
    private static final String finishedTestAddress_byUsername="https://quiz-app-api-georgedobrin.c9users.io/api/finished_tests/username/%s";
   // private static final String answerAddress="https://quiz-app-api-georgedobrin.c9users.io/api/answers/%d";
    private static final String getAnswer_byQuestionIdAddress="https://quiz-app-api-georgedobrin.c9users.io/api/answers/question_id/%d";
    private static final String testAddress="https://quiz-app-api-georgedobrin.c9users.io/api/tests/owner_id/%d";
    private static final String userAddress="https://quiz-app-api-georgedobrin.c9users.io/api/users/username/%s";
    HttpURLConnection connection=null;

    public Answer retrieveAnswer(JSONArray jsonArray,int jsonArrayPosition){

        Answer answer=null;

        try{


                JSONObject jsonObject = jsonArray.getJSONObject(jsonArrayPosition);
                int id=jsonObject.getInt("id");
                String text_answer=jsonObject.getString("answer");
                boolean isCorrect=jsonObject.getBoolean("isCorrect");
                int question_id=jsonObject.getInt("question_id");
                answer=new Answer(id,text_answer,isCorrect,question_id);





        } catch (JSONException e) {
            e.printStackTrace();
        }


        return answer;

    }
    public List<Answer> retrieveAnswers_byQuestionId(int questionId){
        List<Answer> answerList=new ArrayList<>();

        try{
            String address=String.format(getAnswer_byQuestionIdAddress,questionId);
            URL url = new URL(address);
            connection=(HttpURLConnection)url.openConnection();
            InputStream inputStream=connection.getInputStream();
            BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder=new StringBuilder();
            String line=null;
            while((line=reader.readLine())!=null){
                stringBuilder.append(line);
            }
            String result=stringBuilder.toString();
            JSONArray jsonArray=new JSONArray(result);
            for(int i=0;i<jsonArray.length();i++){
                answerList.add(retrieveAnswer(jsonArray,i));
            }



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                connection.disconnect();
            }
        }


        return answerList;
    }
    public void retrieveQuestion(int QuestionId,ArrayList<Question> questionArrayList){
        Question question=null;
        List<Answer> answerList;

        try{
            String address=String.format(questionsAddress,QuestionId);
            URL url = new URL(address);
            connection=(HttpURLConnection)url.openConnection();
            InputStream inputStream=connection.getInputStream();
            BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder=new StringBuilder();
            String line=null;
            while((line=reader.readLine())!=null){
                stringBuilder.append(line);
            }
            String result=stringBuilder.toString();
            JSONObject jsonObject=new JSONObject(result);

            int question_id=jsonObject.getInt("id");
            Category category=retrieveCategory(jsonObject.getInt("question_category_id"));

            String text=jsonObject.getString("question");
            String image=null;
            String imageFromJson=jsonObject.getString("image");
            if(imageFromJson.equals("")||imageFromJson==null){

            }
            else
            {
                image=imageFromJson;
            }

            answerList=retrieveAnswers_byQuestionId(question_id);

            question=new Question(category.getName(),text,answerList,image);

            question.setId(question_id);




        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                connection.disconnect();
            }
            questionArrayList.add(question);

        }



    }
    public void postQuestion(Question question){

    }

    public Category retrieveCategory(int CategoryID){//done
        Category category=null;
        try{
            String address=String.format(categoryAddress,CategoryID);
            URL url = new URL(address);
            connection=(HttpURLConnection)url.openConnection();
            InputStream inputStream=connection.getInputStream();
            BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder=new StringBuilder();
            String line=null;
            while((line=reader.readLine())!=null){
                stringBuilder.append(line);
            }
            String result=stringBuilder.toString();
            JSONObject jsonObject=new JSONObject(result);

            int id=jsonObject.getInt("id");
            String text=jsonObject.getString("category");
            category=new Category(id,text);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return category;
    }

    public void postCategory(Category category){

    }

    public List<Test> retrieveTest_All(int ownerId){
        List<Test> testList=new ArrayList<>();

        try{String address=String.format(testAddress,ownerId);
            URL url = new URL(address);
            connection=(HttpURLConnection)url.openConnection();
            InputStream inputStream=connection.getInputStream();
            BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder=new StringBuilder();
            String line=null;
            while((line=reader.readLine())!=null){
                stringBuilder.append(line);
            }
            String result=stringBuilder.toString();

            JSONArray jsonArray=new JSONArray(result);

            for(int i=0;i<jsonArray.length();i++){
                testList.add(retrieveTest(jsonArray,i));
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return testList;
    }
    public Test retrieveTest(JSONArray jsonArray,int jsonArrayPosition){
        Test test=new Test();
        try{
            JSONObject jsonObject=(JSONObject)jsonArray.get(jsonArrayPosition);

            String questionIdsString=jsonObject.getString("questions_id").replaceAll("\\D+","");
            String[] separated=questionIdsString.split("(?!^)");

            int[] questionIds=new int[separated.length];
            ArrayList<Question> questions=new ArrayList<>();

           for(int i=0;i<separated.length;i++){
              questionIds[i]=Integer.parseInt(separated[i]);

            }
            for (int questionId: questionIds
                    ) {

                retrieveQuestion(questionId,questions);

            }
            int id=Integer.parseInt(jsonObject.getString("id"));
            String name=jsonObject.getString("test");
            boolean shuffle=jsonObject.getBoolean("shuffle");
            boolean one_way=jsonObject.getBoolean("one_way");
            boolean isActive=jsonObject.getBoolean("isActive");
            boolean showResult=jsonObject.getBoolean("showResult");
            boolean feedback=jsonObject.getBoolean("feedback");
            int retries=jsonObject.getInt("retrieves");
            int time=jsonObject.getInt("time");
            int owner_id=jsonObject.getInt("owner_id");
            test.setActive(isActive);
            test.setFeedback(feedback);
            test.setId(id);
            test.setOneWay(one_way);
            test.setOwner_id(owner_id);
            test.setQuestionList(questions);
            test.setShuffle(shuffle);
            test.setResult(showResult);
            test.setRetries(retries);
            test.setTime(time);
            test.setTestName(name);


        } catch (JSONException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return test;
    }
    public List<FinishedTest> retrieveFinishedTest_All(String username){

        List<FinishedTest> finishedTests=new ArrayList<>();
        try {

            String address = String.format(finishedTestAddress_byUsername, username);
            URL url = new URL(address);
            connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            String result = stringBuilder.toString();
            JSONArray jsonArray=new JSONArray(result);

            for(int i=0;i<jsonArray.length();i++){
                finishedTests.add(retrieveFinishedTest_byUsername_parser(jsonArray,i));
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally{
            if (connection != null) {
                connection.disconnect();
            }
        }

        return finishedTests;
    }
    public FinishedTest retrieveFinishedTest_byUsername_parser(JSONArray jsonArray,int jsonArrayPosition){

        ArrayList<String> resultRow=new ArrayList<>();

        FinishedTest finishedTest=new FinishedTest();
        DateFormat df=new SimpleDateFormat( "dd.MM.yyyy") ;

        try{




            JSONObject testObject=(JSONObject)jsonArray.get(jsonArrayPosition);
            finishedTest.setId(Integer.parseInt(testObject.getString("id")));
            finishedTest.setTest_id(Integer.parseInt(testObject.getString("test_id")));
            finishedTest.setTestName(testObject.getString("name"));
            finishedTest.setUsername(testObject.getString("username"));

            finishedTest.setDate((df.parse(testObject.getString("date"))));
            finishedTest.setScore(Integer.parseInt(testObject.getString("score")));





        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e1) {
            e1.printStackTrace();
        }




        return finishedTest;

    }
    public FinishedTest retrieveFinishedTest(int id){//done

        ArrayList<String> resultRow=new ArrayList<>();

        FinishedTest finishedTest=new FinishedTest();

        try {String address=String.format(finishedTestAddress,id);
            URL url=new URL(address);
            connection= (HttpURLConnection) url.openConnection();
            InputStream inputStream=connection.getInputStream();
            BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder=new StringBuilder();
            String line=null;
            while((line=reader.readLine())!=null){
                stringBuilder.append(resultRow);
            }
            String result=stringBuilder.toString();
            Log.d("JSON",result);

            JSONObject jsonObject=new JSONObject(result);
            JSONArray jsonArray=jsonObject.getJSONArray("");
            DateFormat df=new SimpleDateFormat( "dd.MM.yyyy") ;

            for(int i=0;i<jsonArray.length();i++){
                JSONObject testObject=(JSONObject)jsonArray.get(i);
                finishedTest.setId(Integer.parseInt(testObject.getString("id")));
                finishedTest.setTest_id(Integer.parseInt(testObject.getString("test_id")));
                finishedTest.setTestName(testObject.getString("name"));
                finishedTest.setUsername(testObject.getString("username"));

                finishedTest.setDate((df.parse(testObject.getString("date"))));
                finishedTest.setScore(Integer.parseInt(testObject.getString("score")));

            }



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        finally{
            if(connection !=null){
                connection.disconnect();
            }
        }
        return finishedTest;
    }
    public User retrieveUser(String username){//done
        User user=new User();
        ArrayList<String> resultRow=new ArrayList<>();
        try {String address=String.format(userAddress,username);
            URL url=new URL(address);
            connection= (HttpURLConnection) url.openConnection();
            InputStream inputStream=connection.getInputStream();
            BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder=new StringBuilder();
            String line=null;
            while((line=reader.readLine())!=null){
                stringBuilder.append(resultRow);
            }
            String result=stringBuilder.toString();
            Log.d("JSON",result);

            JSONObject jsonObject=new JSONObject(result);


            user.setGroup(jsonObject.getInt("group"));
            user.setHashedPassword(jsonObject.getString("hashedPassword"));
            user.setName(jsonObject.getString("name"));
            user.setStudent(jsonObject.getBoolean("isStudent"));
            user.setUsername(username);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        finally{
            if(connection !=null){
                connection.disconnect();
            }
        }

        return user;

    }


}
