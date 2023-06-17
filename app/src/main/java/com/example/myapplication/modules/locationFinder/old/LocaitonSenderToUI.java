//package com.example.myapplication.modules.locationFinder.old;
//
//import android.app.Activity;
//import android.content.Context;
//import android.os.AsyncTask;
//import android.util.Log;
//
//import androidx.fragment.app.FragmentActivity;
//
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.MarkerOptions;
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
//public class LocaitonSenderToUI extends FragmentActivity implements OnMapReadyCallback {
//
//    SupportMapFragment mapFragment;
//    GoogleMap mMap;
//    double lat = 19.065689;
//    double lon = 72.883431;
//    Context context;
//    Activity activity;
//    String paretn_username;
//
//    public LocaitonSenderToUI(Context context, Activity activity,String parent_un) {
//        this.context = context;
//        this.activity = activity;
//        this.paretn_username = parent_un;
//    }
//
//    public void runAsyncTask()
//    {
////        LocationFinderClass locationFinderClass = new LocationFinderClass(activity,context);
////    locationFinderClass.execute(paretn_username);
//
//        LocationFinderAsync locationFinderAsync = new LocationFinderAsync(activity,context);
//        locationFinderAsync.execute(paretn_username);
//    }
//
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//        LatLng sydney;
//
//        sydney = new LatLng(lat, lon);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
////        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 15.0f));
//    }
//
//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//
//    }
//    public class LocationFinderAsync extends AsyncTask<String, Void, String>
//    {
//        Activity activity;
//        Context context;
//        String parent_username;
//
//        public LocationFinderAsync(Activity activity, Context context) {
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
//                    break;
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
//            Log.e("onPostExecute", "onPostExecute: append done" + s);
////            Toast.makeText(context, lat + " : " + lon, Toast.LENGTH_SHORT).show();
//
////            mapFragment = (SupportMapFragment) getSupportFragmentManager()
////                    .findFragmentById(R.id.map);
////            mapFragment.getMapAsync(LocaitonSenderToUI.this::onMapReady);
//            MainMapActivity mainMapActivity = new MainMapActivity();
//            mainMapActivity.justTemp();
//        }
//    }
//}
