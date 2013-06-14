package com.maggots.YellowPages;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class yellowPagesActivity extends Activity {

    private sectionCursorAdapter cursorAdapter = null;
    private  dataBaseHelper dbHelper = null;

    private InstanceState state=null;
    public ProgressBar progressBar = null;
    public Cursor model = null;

    static final String KEY_ITEM = "item";
    static final String KEY_SECTION = "Parentcategory";
    static final String KEY_SECTION_TITLE = "ParentcatName";

    static final String KEY_CATEGORY = "category";

    static final String KEY_CATNAME = "catName";
    static final String KEY_TITLE = "title";
    static final String KEY_ADDRESS = "address";
    static final String KEY_PHONE = "phone";
    static final String KEY_TAG = "tag";
    static final String DB_FULL_PATH = "/data/data/com.maggots.YellowPages/databases/yellowpages.db";
    static final String ID_EXTRA= "com.maggots.yellowpage";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);    //To change body of overridden methods use File | Settings | File Templates.
        if (requestCode == 1) {

            if(resultCode == RESULT_OK){
//                String result=data.getStringExtra("result");
            }
            if (resultCode == RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setTitle("Желтые страницы: Главное меню");
        dbHelper = new dataBaseHelper(getApplicationContext());
        dbHelper.getWritableDatabase();
        dbHelper.close();

//        if(!checkDBforExist(dbHelper)){
//            in = new Intent(getApplicationContext(),AndroidFileDownloader.class);
//            startActivity(in);

//            progressBar = (ProgressBar)findViewById(R.id.progressBar);
//            Toast.makeText(getApplicationContext(),"You need to download and install YellowPages database", Toast.LENGTH_LONG).show();
//            state=(InstanceState)getLastNonConfigurationInstance();
//            if (state==null) {
//                state=new InstanceState();
//                state.task=new convertXmltoDbTask(this);
//                state.task.execute();
//            }
//            else {
//                if (state.task!=null) {
//                    state.task.attach(this);
//                }
//            }
//        }
//        else {
//            Toast.makeText(getApplicationContext(),"EveryThing is OK",Toast.LENGTH_LONG).show();
            ListView lv = (ListView)findViewById(R.id.sectionListView);
            dbHelper = new dataBaseHelper(getApplicationContext());
            model = dbHelper.getSectionNames();
            startManagingCursor(model);
            cursorAdapter = new sectionCursorAdapter(getApplicationContext(),model);
            lv.setAdapter(cursorAdapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent in = new Intent(getApplicationContext(), categoryActivity.class);
                String secName = ((TextView)view.findViewById(R.id.sectionName)).getText().toString();
                in.putExtra(ID_EXTRA, secName);

                startActivity(in);

            }
        });
//        }





//        NodeList nl = null;
//        if(doc !=null){
//            nl = doc.getElementsByTagName(KEY_ITEM);}
//
//        // looping through all item nodes <item>
//        for (int i = 0; i < nl.getLength(); i++) {
//            // creating new HashMap
//            HashMap<String, String> map = new HashMap<String, String>();
//            Element e = (Element) nl.item(i);
//            // adding each child node to HashMap key => value
////            map.put(KEY_CATNAME, parser.getValue(e, KEY_CATNAME));
//            map.put(KEY_TITLE, parser.getValue(e, KEY_TITLE));
//            map.put(KEY_PHONE, parser.getValue(e, KEY_PHONE));
//            map.put(KEY_ADDRESS,  parser.getValue(e, KEY_ADDRESS));
//            map.put(KEY_TAG, parser.getValue(e, KEY_TAG));
//
//            // adding HashList to ArrayList
//            yellowPageItems.add(map);
//        }


        // Adding menuItems to ListView
//		ListAdapter adapter = new SimpleAdapter(this, menuItems,
//				R.layout.list_item,
//				new String[] { KEY_NAME,KEY_SURNAME, KEY_DEPT, KEY_MOBILE, KEY_INNO}, new int[] {
//						R.id.name, R.id.surname, R.id.desciption, R.id.cost, R.id.inno });

//        adapter = new customAdapter(this, R.layout.list_item, yellowPageItems);
//
//        lv.setAdapter(adapter);
//        final EditText inputSearch = (EditText)findViewById(R.id.search);
//        inputSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                inputSearch.setText("");
//            }
//        });
//
//        inputSearch.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
//                //To change body of implemented methods use File | Settings | File Templates.
//
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
//                //To change body of implemented methods use File | Settings | File Templates.
//                adapter.getFilter().filter(charSequence);
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                //To change body of implemented methods use File | Settings | File Templates.
//            }
//        });



//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//                // getting values from selected ListItem
//                String name = ((TextView) view.findViewById(R.id.name)).getText().toString();
//                String surname = ((TextView) view.findViewById(R.id.surname)).getText().toString();
//                String mobile = ((TextView) view.findViewById(R.id.cost)).getText().toString();
//                String description = ((TextView) view.findViewById(R.id.desciption)).getText().toString();
//
//                // Starting new intent
//                Intent in = new Intent(getApplicationContext(), DetailedView.class);
//                in.putExtra(KEY_NAME, name);
//                in.putExtra(KEY_SURNAME, surname);
//                in.putExtra(KEY_MOBILE, mobile);
//                in.putExtra(KEY_DEPT, description);
//                startActivity(in);
//
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.close:
                finish();
                return true;
            case R.id.about:
                showAbout();
                return true;
            case R.id.donwload:
                Intent in = new Intent(getApplicationContext(),AndroidFileDownloader.class);
                startActivity(in);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showAbout(){
        Intent intent = new Intent(this, aboutActivity.class);
        startActivity(intent);
    }

    private boolean checkDBforExist(dataBaseHelper checkDB) {
        Cursor cursor = null;


             cursor = checkDB.getSectionNames();
        if(cursor!=null) {
            if(cursor.moveToFirst()) {
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }

    @Override
    public Object onRetainNonConfigurationInstance() {
        if (state.task!=null) {
            state.task.deattach();
        }
        return(state);
    }

    private static class InstanceState {

        convertXmltoDbTask task=null;
    }


    private static class convertXmltoDbTask extends AsyncTask<String, Void, String> {

        private yellowPagesActivity activity = null;
        private Exception e = null;
        private ProgressBar progressBar;
        private int progressIndex = 0;
        private dataBaseHelper dbHelper = null;

        public convertXmltoDbTask(yellowPagesActivity activity){
            attach(activity);



        }


        private void attach(yellowPagesActivity activity){
            this.activity=activity;
            this.progressBar = activity.progressBar;
            dbHelper = new dataBaseHelper(this.activity);
            progressBar.setVisibility(View.GONE);


        }

        private void deattach(){
            this.activity = null;
            this.progressBar = null;
            dbHelper = null;
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
            Log.i("YellowPage", "Going to read XML file");
            String xml = readTextFile(inputStream);
            Log.i("YellowPage", "Done reading xml file");
            Log.i("YellowPage", "Parsing file");
            XMLParser parser = new XMLParser();
            Log.i("YellowPage", "Getting DOM element");


            Document doc = null;
            try{
                doc = parser.getDomElement(xml); // getting DOM element
            } catch (Exception e){
                Log.e("tag", e.getMessage());
            }
            NodeList sectionNod = doc.getElementsByTagName(KEY_SECTION);
            Log.i("YellowPage", "Got "+sectionNod.getLength()+"  sections");
            for(int s = 0; s < sectionNod.getLength(); s++){
                Element secElement = (Element)sectionNod.item(s);
                String sectionTitle = parser.getValue(secElement, KEY_SECTION_TITLE);

                NodeList catNod = secElement.getElementsByTagName(KEY_CATEGORY);

//                progressBar.setMax(catNod.getLength());
//                progressBar.setVisibility(View.VISIBLE);
                Log.i("YellowPage", "Got "+catNod.getLength()+"  categories");
                for(int k = 0; k < catNod.getLength(); k++){
                    Element catElement = (Element)catNod.item(k);
                    String catName =  parser.getValue(catElement,KEY_CATNAME);
                    NodeList nl = catElement.getElementsByTagName(KEY_ITEM);


                    Log.i("YellowPage", "Got in "+catName+" category "+nl.getLength()+"  items");
                    // looping through all item nodes <item>
                    for (int i = 0; i < nl.getLength(); i++) {
                        Element e = (Element) nl.item(i);
                         String phone = parser.getValue(e, KEY_PHONE).trim();
                        phone = phone.replaceAll(".+\\*+,","");
                        dbHelper.insert(
                                sectionTitle.trim(),
                                catName.trim(),
                                parser.getValue(e, KEY_TITLE).trim(),
                                parser.getValue(e, KEY_ADDRESS).trim(),
                                phone,
                                parser.getValue(e, KEY_TAG).trim());

                    }
//                    progressIndex++;
//                    publishProgress();
                }
            }


            Log.i("YellowPage", "Succesfully imported all data");
            return "Successfully done";  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();    //To change body of overridden methods use File | Settings | File Templates.
        }

        @Override
        public void onPostExecute(String feed) {
            if (e==null) {
//                activity.setStatus(feed);
            }
            else {
                Log.e("LunchList", "Exception parsing feed", e);
//                activity.setStatus(e.getMessage());
            }
            progressBar.setVisibility(View.GONE);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            progressBar.incrementProgressBy(progressIndex);
            progressIndex++;
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

    @Override
    protected void onDestroy() {
        super.onDestroy();    //To change body of overridden methods use File | Settings | File Templates.
        stopManagingCursor(model);
        dbHelper.close();
    }
}
