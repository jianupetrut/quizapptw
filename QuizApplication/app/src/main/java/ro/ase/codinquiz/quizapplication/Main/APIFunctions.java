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

import ro.ase.codinquiz.quizapplication.Main.Entities.Category;
import ro.ase.codinquiz.quizapplication.Main.Entities.FinishedTest;
import ro.ase.codinquiz.quizapplication.Main.Entities.Question;
import ro.ase.codinquiz.quizapplication.Main.Entities.Test;
import ro.ase.codinquiz.quizapplication.Main.Entities.User;

public class APIFunctions {
    private static final String questionsAddress="";
    private static final String categoryAddress="";
    private static final String finishedTestAddress="";
    private static final String answerAddress="";//may not be needed
    private static final String testAddress="";
    private static final String userAddress="";
    HttpURLConnection connection=null;

    public void retrieveQuestion(int QuestionId,ArrayList<Question> questionArrayList){
        Question question=null;


        try{
           //sa formatezi adresa astfel incat sa caute dupa id
            URL url = new URL(questionsAddress);
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

    public Category retrieveCategory(int CategoryID){
        Category category=null;
        try{//format for category id
            URL url = new URL(categoryAddress);
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

            int id=Integer.parseInt(jsonObject.getString("category"));
            String text=jsonObject.getString("question");
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

    public Test getTest(int testId){
        Test test=new Test();
        try{//format for test id
            URL url = new URL(testAddress);
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
            // ai putea sa faci un foreach si sa dai get pe fiecare question id
            for(int i=0;i<jsonArray.length();i++){
                questionIds[i]=Integer.parseInt(((JSONObject)jsonArray.get(i)).getString(""));

            }
            //set question ids

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
        return test;
    }
    public FinishedTest getFinishedTest(int id){

        ArrayList<String> resultRow=new ArrayList<>();

        FinishedTest finishedTest=new FinishedTest();

        try {//format adress
            URL url=new URL(finishedTestAddress);
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
    public User getUser(String username){
        User user=new User();
        ArrayList<String> resultRow=new ArrayList<>();
        try {//format adress
            URL url=new URL(finishedTestAddress);
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
