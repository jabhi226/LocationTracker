//package com.example.myapplication.modules.locationFinder.old;
//
//import android.app.Activity;
//import android.content.Context;
//import android.os.AsyncTask;
//import android.util.Log;
//import android.widget.Toast;
//
//import com.example.myapplication.modules.locationFinder.old.SetMarkerListener;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.io.UnsupportedEncodingException;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLConnection;
//import java.net.URLEncoder;
//import java.util.StringTokenizer;
//
////class SalesFragment extends FragmentActivity {
////
////    SupportMapFragment mapFragment;
////    public void initMap()    {
////        mapFragment = (SupportMapFragment) getSupportFragmentManager()
////                .findFragmentById(R.id.map);
////        mapFragment.getMapAsync(MainMapActivity.this::onMapReady);
////    }
//
//    public class LocationFinderClass extends AsyncTask<String, Void, String> implements SetMarkerListener {
//        Activity activity;
//        Context context;
//        String parent_username;
//
//        public LocationFinderClass(Activity activity, Context context) {
//            this.activity = activity;
//            this.context = context;
//        }
//
//        @Override
//        protected String doInBackground(String... strings) {
//            parent_username = strings[0];
//
//            String link = "http://10.0.2.2:8080/location/fetch_child_location.php";
//
//            try {
//                String data = URLEncoder.encode("username", "UTF-8") + "="
//                        + URLEncoder.encode(parent_username, "UTF-8");
//                URL url = new URL(link);
//                URLConnection conn = url.openConnection();
//
//                conn.setDoOutput(true);
//
//                OutputStreamWriter outputStreamWriter = null;
//                try {
//                    outputStreamWriter = new OutputStreamWriter(conn.getOutputStream());
//                } catch (IOException e) {
//                    return "Server is Off";
//                }
//
//                outputStreamWriter.write(data);
//                outputStreamWriter.flush();
//
//                InputStreamReader inputStreamReader = new InputStreamReader(conn.getInputStream());
//                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//
//                StringBuilder stringBuilder = new StringBuilder();
//                String line = null;
//
//                while ((line = bufferedReader.readLine()) != null) {
//                    stringBuilder.append(line);
//                    Log.e("doInBackground", "doInBackground: append done");
//                }
//                Log.e("doInBackground", "doInBackground: append done" + stringBuilder.toString());
//                return stringBuilder.toString();
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
////        super.onPostExecute(s);
//
//            StringTokenizer tokens = new StringTokenizer(s, ":");
//            String lat = tokens.nextToken();
//            String lon = tokens.nextToken();
//
////        LocationDisplay locationDisplay = new LocationDisplay();
////        locationDisplay.setUpMapIfNeeded(activity,context);
//
//
////            mapFragment = (SupportMapFragment) getSupportFragmentManager()
////                    .findFragmentById(R.id.map);
////            mapFragment.getMapAsync(this);
//
//            //setMarker();
//            Log.e("onPostExecute", "onPostExecute: append done" + s);
//            Toast.makeText(context, lat + " : " + lon, Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public void setMarker() {
//            MainMapActivity mainMapActivity = new MainMapActivity();
//            mainMapActivity.justTemp();
//        }
//    }
////}