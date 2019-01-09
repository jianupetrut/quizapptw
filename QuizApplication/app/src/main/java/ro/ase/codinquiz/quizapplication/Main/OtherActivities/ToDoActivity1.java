package ro.ase.codinquiz.quizapplication.Main.OtherActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import ro.ase.codinquiz.quizapplication.R;

public class ToDoActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do1);
    }

    public void downloadFile(String text)
    {
        File myFile = new File("sdcard/students.json");
        if (!myFile.exists())
        {
            try
            {
                myFile.createNewFile();
            }
            catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        try
        {
            //BufferedWriter for performance, true to set append to file flag
            BufferedWriter buf = new BufferedWriter(new FileWriter(myFile, true));
            buf.append(text);
            buf.newLine();
            buf.close();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void getStudentsList(View v){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // downloading here
                URL url = null;
                try {
                    url = new URL("https://jsonplaceholder.typicode.com/users");
                    URLConnection urlConnection = url.openConnection();
                    urlConnection.setConnectTimeout(1000);
                    downloadFile(urlConnection.getInputStream().toString());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }
}
