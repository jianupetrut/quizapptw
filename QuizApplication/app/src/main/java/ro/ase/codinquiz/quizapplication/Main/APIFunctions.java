package ro.ase.codinquiz.quizapplication.Main;

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

import ro.ase.codinquiz.quizapplication.Main.Entities.Answer;
import ro.ase.codinquiz.quizapplication.Main.Entities.Category;
import ro.ase.codinquiz.quizapplication.Main.Entities.FinishedTest;
import ro.ase.codinquiz.quizapplication.Main.Entities.Question;
import ro.ase.codinquiz.quizapplication.Main.Entities.Test;
import ro.ase.codinquiz.quizapplication.Main.Entities.User;

public class APIFunctions {
    private static final String questionsAddress="https://quiz-app-api-georgedobrin.c9users.io/api/questions/%d";
    private static final String categoryAddress="https://quiz-app-api-georgedobrin.c9users.io/api/question_categories/%d";
    private static final String finishedTestAddress="https://quiz-app-api-georgedobrin.c9users.io/api/finished_tests/%d";
    private static final String answerAddress="https://quiz-app-api-georgedobrin.c9users.io/api/answers/%d";
    private static final String getAnswer_byQuestionIdAddress="https://quiz-app-api-georgedobrin.c9users.io/api/answers/question_id/%d";
    private static final String testAddress="https://quiz-app-api-georgedobrin.c9users.io/api/tests/%d";
    private static final String userAddress="https://quiz-app-api-georgedobrin.c9users.io/api/users/username/%d";
    HttpURLConnection connection=null;

    public Answer retrieveAnswer(int answerId){

        Answer answer=null;

        try{
            String address=String.format(answerAddress,answerId);
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

            int id=Integer.parseInt(jsonObject.getString("id"+ ""));
            String text=jsonObject.getString("question");
            

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


        return null;

    }
    public Answer retrieveAnswer_byQuestionId(int questionId){
        Answer answer=null;




        return null;
    }
    public void retrieveQuestion(int QuestionId,ArrayList<Question> questionArrayList){
        Question question=null;


        try{
            String address=String.format(questionsAddress,QuestionId);//must work on get answers
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

            String category=jsonObject.getString("category"+"");
            String text=jsonObject.getString("question");
            //get answers?

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



//            for(int i=0;i<jsonArray.length();i++){
//                JSONObject testObject=(JSONObject)jsonArray.get(i);
//                finishedTest.setId(Integer.parseInt(testObject.getString("id")));
//                finishedTest.setTest_id(Integer.parseInt(testObject.getString("test_id")));
//
//                finishedTest.setDate((df.parse(testObject.getString("date"))));
//                finishedTest.setScore(Integer.parseInt(testObject.getString("score")));
//                arrayOfWebData.add(finishedTest);
//            }
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

    public Test retrieveTest(int testId){//must work on getquestion
        Test test=new Test();
        try{String address=String.format(testAddress,testId);
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
            JSONArray jsonArray=new JSONArray(jsonObject.getJSONArray("questions_id"));
            int[] questionIds=new int[jsonArray.length()];
            ArrayList<Question> questions=new ArrayList<>();
            // ai putea sa faci un foreach si sa dai get pe fiecare question id

            for(int i=0;i<jsonArray.length();i++){
                questionIds[i]=Integer.parseInt(((JSONObject)jsonArray.get(i)).getString(""));

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
            int retries=jsonObject.getInt("retries");
            int time=jsonObject.getInt("time");
            int owner_id=jsonObject.getInt("owner_id");





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
        return test;
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
            JSONArray jsonArray=jsonObject.getJSONArray("tests");
            DateFormat df=new SimpleDateFormat( "dd/MM/yyyy") ;

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
