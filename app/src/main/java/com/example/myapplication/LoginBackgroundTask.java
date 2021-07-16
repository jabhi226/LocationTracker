package com.example.myapplication;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class LoginBackgroundTask extends AsyncTask<String,Void,String> {
    String username;
    Activity activity;
    Context context;

    public LoginBackgroundTask(Context context, Activity activity) {
        this.activity = activity;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        username = strings[0];
        String password = strings[1];

//        String link = "http://192.168.43.17:8080/location/Login.php";
        String link = "http://192.168.0.102:8080/location/parentLogin.php";
//        String link = "http://10.0.2.2:8080/location/parentLogin.php";

        try {
            String data = URLEncoder.encode("username","UTF-8")+"="
                    +URLEncoder.encode(username,"UTF-8")+"&"
                    +URLEncoder.encode("password","UTF-8")+"="
                    +URLEncoder.encode(password,"UTF-8");

            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);

            Log.e("URL", url.getPath() + url.getUserInfo());
            OutputStreamWriter outputStreamWriter = null;
            try {
                outputStreamWriter = new OutputStreamWriter(conn.getOutputStream());
            }
            catch (IOException e) {
                e.printStackTrace();
                return "Server is Off";
            }

            outputStreamWriter.write(data);
            outputStreamWriter.flush();

            InputStreamReader inputStreamReader = new InputStreamReader(conn.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder stringBuilder = new StringBuilder();
            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                Log.e("doInBackground", "append done");
            }
            Log.e("stingBuilder", stringBuilder.toString());

            return stringBuilder.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "null";
    }

    @Override
    protected void onPostExecute(String s) {
        if (s.equals(username)){
            Intent intent = new Intent(activity, MainMapActivity.class);
            intent.putExtra("parent_username",username);
            activity.startActivity(intent);
        }
        else{
            Toast.makeText(context,s,Toast.LENGTH_SHORT).show();
        }
    }
}
