package ro.ase.codinquiz.quizapplication.Main.APIFunctionsAndWorkers;

import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ro.ase.codinquiz.quizapplication.Main.Entities.Answer;
import ro.ase.codinquiz.quizapplication.Main.Entities.Category;
import ro.ase.codinquiz.quizapplication.Main.Entities.FinishedTest;
import ro.ase.codinquiz.quizapplication.Main.Entities.Question;
import ro.ase.codinquiz.quizapplication.Main.Entities.Test;
import ro.ase.codinquiz.quizapplication.Main.Entities.User;

public class APIFunctions {
    private static final String questionsAddress="https://quiz-app-georgedobrin.c9users.io/api/questions/%d";
    private static final String categoryAddress="https://quiz-app-georgedobrin.c9users.io/api/question_categories/%d";
    private static final String categoryAddressForAll="https://quiz-app-georgedobrin.c9users.io/api/question_categories";
    private static final String categoryPostAddress="https://quiz-app-georgedobrin.c9users.io/api/question_categories";
    private static final String finishedTestAddress="https://quiz-app-georgedobrin.c9users.io/api/finished_tests/owner_id/%d";
    private static final String finishedTestAddress_byUsername="https://quiz-app-georgedobrin.c9users.io/api/finished_tests/username/%s";
   // private static final String answerAddress="https://quiz-app-georgedobrin.c9users.io/api/answers/%d";
    private static final String answerPostAdress="https://quiz-app-georgedobrin.c9users.io/api/answers";
    private static final String questionPostAddress="https://quiz-app-georgedobrin.c9users.io/api/questions";
    private static final String finishedTestPostAddress="https://quiz-app-georgedobrin.c9users.io/api/finished_tests";
    private static final String testPostAddress="https://quiz-app-georgedobrin.c9users.io/api/tests";
    private static final String userPostAddress="https://quiz-app-georgedobrin.c9users.io/api/users";

    private static final String getAnswer_byQuestionIdAddress="https://quiz-app-georgedobrin.c9users.io/api/answers/question_id/%d";
    private static final String testAddress="https://quiz-app-georgedobrin.c9users.io/api/tests/owner_id/%d";
    private static final String testAddressById="https://quiz-app-georgedobrin.c9users.io/api/tests/%d";
    private static final String userAddress="https://quiz-app-georgedobrin.c9users.io/api/users/username/%s";
    HttpURLConnection connection=null;

    public List<Category> retrieveCategories_All(){
        List<Category> categories=new ArrayList<>();
        try{

            URL url = new URL(categoryAddressForAll);
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

            for(int i=0;i<jsonArray.length();i++) {
                JSONObject jsonObject=(JSONObject)jsonArray.get(i);
                int id = jsonObject.getInt("id");
                String text = jsonObject.getString("category");
                Category category = new Category(id, text);
                categories.add(category);
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
        return categories;
    }
    public Test retrieveTestByID(int TestID){
        Test test=new Test();

        try{
            String address=String.format(testAddressById,TestID);
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

            String questionIdsString=jsonObject.getString("questions_id").replaceAll("\"","").replaceAll("\\[", "").replaceAll("\\]","");;
            questionIdsString.replaceAll("[\\[\\]]", "").replace("[","").replace("]","");
            questionIdsString.replace("[","").replace("]","");
            questionIdsString.replace("\\[\\", "");
            questionIdsString.replaceAll("]","");
            // String[] separated=questionIdsString.split("(?!^)");

            String[] separated=questionIdsString.split(",");

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
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return test;
    }
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
    public void retrieveQuestion_All(ArrayList<Question> questionArrayList){
        Question question=null;
        List<Answer> answerList;

        try{
            String address=String.format(questionPostAddress);
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
            for(int i=0;i<jsonArray.length();i++) {

                JSONObject jsonObject=(JSONObject)jsonArray.get(i);
                int question_id = jsonObject.getInt("id");
                Category category = retrieveCategory(jsonObject.getInt("question_category_id"));

                String text = jsonObject.getString("question");
                String image = null;
                String imageFromJson = jsonObject.getString("image");
                if (imageFromJson.equals("") || imageFromJson == null) {

                } else {
                    image = imageFromJson;
                }

                answerList = retrieveAnswers_byQuestionId(question_id);

                question = new Question(category.getName(), text, answerList, image);

                question.setId(question_id);

                questionArrayList.add(question);
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
    public void postAnswers(List<Answer> answers){
        try{


            URL url = new URL(answerPostAdress);
            connection=(HttpURLConnection)url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            try {
                connection.setRequestMethod("POST");
            }catch (ProtocolException e){

            }
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept","application/json");
            //     connection.setChunkedStreamingMode(0);
            JSONObject mainObject=new JSONObject();

            JSONArray jsonArray=new JSONArray();
            for (Answer a :
                    answers) {
                JSONObject jsonObject=new JSONObject();

                jsonObject.put("answer",a.getText());
                jsonObject.put("isCorrect",a.isCorrect());
                jsonObject.put("question_id",a.getQuestionId());
                jsonArray.put(jsonObject);
            }


            mainObject.put("answers",jsonArray);



            DataOutputStream os=new DataOutputStream(connection.getOutputStream());

            os.writeBytes(mainObject.toString());

            os.flush();

            connection.connect();
            connection.getContent();

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

    }
    public void postTest(Test test){
        try{


            URL url = new URL(testPostAddress);
            connection=(HttpURLConnection)url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            try {
                connection.setRequestMethod("POST");
            }catch (ProtocolException e){

            }
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept","application/json");
            //     connection.setChunkedStreamingMode(0);
            JSONObject jsonObject=new JSONObject();


            //questions_id , test (name),shuffle,one_way,feedback,showResult,isActive,retrieves,time,owner_id
            int[] questions_id=new int[test.getQuestionList().size()];
            jsonObject.put("questions_id",questions_id);
            jsonObject.put("test",test.getTestName());
            jsonObject.put("shuffle",test.isShuffle());
            jsonObject.put("one_way",test.isOneWay());
            jsonObject.put("feedback",test.isFeedback());
            jsonObject.put("showResult",test.getResult());
            jsonObject.put("isActive",test.isActive());
            jsonObject.put("retrieves",test.getRetries());
            jsonObject.put("time",test.getTime());
            jsonObject.put("owner_id",test.getOwner_id());





            DataOutputStream os=new DataOutputStream(connection.getOutputStream());

            os.writeBytes(jsonObject.toString());

            os.flush();

            connection.connect();
            connection.getContent();

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
    }
    public void postFinishedTest(FinishedTest finishedTest){
        try{


            URL url = new URL(finishedTestPostAddress);
            connection=(HttpURLConnection)url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            try {
                connection.setRequestMethod("POST");
            }catch (ProtocolException e){

            }
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept","application/json");
            //     connection.setChunkedStreamingMode(0);
            JSONObject jsonObject=new JSONObject();


            jsonObject.put("test_id",finishedTest.getTest_id());
            jsonObject.put("username",finishedTest.getUsername());
            jsonObject.put("name",finishedTest.getTestName());
            jsonObject.put("date",finishedTest.getDate().toString());
            jsonObject.put("score",finishedTest.getScore());




            DataOutputStream os=new DataOutputStream(connection.getOutputStream());

            os.writeBytes(jsonObject.toString());

            os.flush();

            connection.connect();
            connection.getContent();

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
    }
    public void postQuestion(Question question){
        List<Category> categoriesList=retrieveCategories_All();
        Category correctCategory=null;
        for (Category c :
                categoriesList) {
            if (c.getName().equals(question.getCategory())) {
                correctCategory=c;
            }

            }

            if(correctCategory!=null) {
                try {


                    URL url = new URL(questionPostAddress);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    try {
                        connection.setRequestMethod("POST");
                    } catch (ProtocolException e) {

                    }
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setRequestProperty("Accept", "application/json");
                    //     connection.setChunkedStreamingMode(0);
                    JSONObject jsonObject = new JSONObject();


                    jsonObject.put("question_category_id", correctCategory.getId());//MODIFY THE QUESTION CATEGORY!!!
                    jsonObject.put("question", question.getText());
                    jsonObject.put("image", question.getImage());


                    DataOutputStream os = new DataOutputStream(connection.getOutputStream());

                    os.writeBytes(jsonObject.toString());

                    os.flush();

                    connection.connect();
                    connection.getContent();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }

        postAnswers(question.getAnswerList());
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

        try{


            URL url = new URL(categoryPostAddress);
            connection=(HttpURLConnection)url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            try {
                connection.setRequestMethod("POST");
            }catch (ProtocolException e){

            }
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept","application/json");
       //     connection.setChunkedStreamingMode(0);
            JSONObject jsonObject=new JSONObject();

            jsonObject.put("category",category.getName());



            String string=jsonObject.toString();
            DataOutputStream os=new DataOutputStream(connection.getOutputStream());

            os.writeBytes(jsonObject.toString());

            os.flush();

            connection.connect();
            connection.getContent();

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

            String questionIdsString=jsonObject.getString("questions_id").replaceAll("\"","").replaceAll("\\[", "").replaceAll("\\]","");;
            questionIdsString.replaceAll("[\\[\\]]", "").replace("[","").replace("]","");
            questionIdsString.replace("[","").replace("]","");
            questionIdsString.replace("\\[\\", "");
            questionIdsString.replaceAll("]","");
           // String[] separated=questionIdsString.split("(?!^)");

            String[] separated=questionIdsString.split(",");

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


            DateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");
            String s=sdf.format(Calendar.getInstance().getTime());

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
