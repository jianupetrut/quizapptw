package ro.ase.codinquiz.quizapplication.Main.Student;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

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
import java.util.Date;
import java.util.List;

import ro.ase.codinquiz.quizapplication.Main.Adapters.FinishedTestsAdapter;
import ro.ase.codinquiz.quizapplication.Main.Entities.FinishedTest;
import ro.ase.codinquiz.quizapplication.R;

public class StudentMain extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ArrayList<FinishedTest> arrayOfWebData=new ArrayList<FinishedTest>();
    ListView assignmentHistoryListView;

    private FinishedTestsAdapter adapter;

    static ArrayList<String> resultRow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assignmentHistoryListView=(ListView)findViewById(R.id.assignment_history_listview);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        String webaddress="TODO";
        HttpURLConnection connection= null;

        try {
            URL url=new URL(webaddress);
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
            FinishedTest finishedTest=new FinishedTest();
            JSONObject jsonObject=new JSONObject(result);
            JSONArray jsonArray=jsonObject.getJSONArray("tests");
            DateFormat df=new SimpleDateFormat( "dd/MM/yyyy") ;

            for(int i=0;i<jsonArray.length();i++){
                JSONObject testObject=(JSONObject)jsonArray.get(i);
                finishedTest.setId(Integer.parseInt(testObject.getString("id")));
                finishedTest.setTest_id(Integer.parseInt(testObject.getString("test_id")));

                finishedTest.setDate((df.parse(testObject.getString("date"))));
                finishedTest.setScore(Integer.parseInt(testObject.getString("score")));
                arrayOfWebData.add(finishedTest);
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

        assignmentHistoryListView=(ListView)findViewById(R.id.assignment_history_listview);
        adapter=new FinishedTestsAdapter(this,R.layout.item_finishedtest,arrayOfWebData);
        adapter.notifyDataSetChanged();
        assignmentHistoryListView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.student_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void joinTest(View view){
        Intent intent = new Intent(this, JoinTestActivity.class);
        startActivity(intent);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_my_profile) {

        } else if (id == R.id.nav_new_assignment) {

        } else if (id == R.id.nav_leave_feedback) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
