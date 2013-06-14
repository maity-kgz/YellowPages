package com.maggots.YellowPages;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import org.w3c.dom.Document;

import javax.crypto.spec.IvParameterSpec;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: maity
 * Date: 5/8/13
 * Time: 8:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class dataBaseDownloadActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
    }

    private static class convertXmltoDbTask extends AsyncTask<String, Void, String> {

        private dataBaseDownloadActivity activity = null;
        private Exception e = null;
        convertXmltoDbTask(dataBaseDownloadActivity activity){
            attach(activity);

        }

        private void attach(dataBaseDownloadActivity activity){
            this.activity=activity;
        }

        private void deattach(){
            this.activity = null;
        }
        @Override
        protected String doInBackground(String... strings) {
            AssetManager assetManager = activity.getAssets();
            InputStream inputStream = null;
            try {
                inputStream = assetManager.open("yellowpage.xml");
            } catch (IOException e) {
                Log.e("tag", e.getMessage());
            }
            String xml = readTextFile(inputStream);
            XMLParser parser = new XMLParser();


            Document doc = null;
            try{
                doc = parser.getDomElement(xml); // getting DOM element
            } catch (Exception e){
                Log.e("tag", e.getMessage());
            }
            return "Successfully done";  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void onPostExecute(String feed) {
            if (e==null) {
//                activity.setStatus(feed);
            }
            else {
                Log.e("LunchList", "Exception parsing feed", e);
//                activity.goBlooey(e);
            }
        }


        private String readTextFile(InputStream inputStream) {

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte buf[] = new byte[1000];
            int len;
            try {
                while ((len = inputStream.read(buf)) != -1) {
                    outputStream.write(buf, 0, len);
                }
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {

            }
            return outputStream.toString();
        }

    }
}
