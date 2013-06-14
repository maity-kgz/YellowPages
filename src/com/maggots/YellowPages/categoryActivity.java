package com.maggots.YellowPages;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: maity
 * Date: 5/12/13
 * Time: 12:17 AM
 * To change this template use File | Settings | File Templates.
 */
public class categoryActivity extends Activity {
    static final String ID_EXTRA= "com.maggots.yellowpage.cats";
    dataBaseHelper dataBaseHelper = null;
    Cursor model = null;
    categoryCursorAdapter cursorAdapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        setContentView(R.layout.categories);
        String secName = getIntent().getStringExtra(yellowPagesActivity.ID_EXTRA);

        if(secName != null || !secName.isEmpty()){
            setTitle("Категория: "+secName);
            ListView lv = (ListView)findViewById(R.id.categoryListView);
            dataBaseHelper = new dataBaseHelper(getApplicationContext());
            model = dataBaseHelper.getCategoryBySectioinName(secName);
            startManagingCursor(model);
            cursorAdapter = new categoryCursorAdapter(getApplicationContext(),model);
            lv.setAdapter(cursorAdapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    Intent in = new Intent(getApplicationContext(), itemActivity.class);
                    String catName = ((TextView)view.findViewById(R.id.catName)).getText().toString();
                    in.putExtra(ID_EXTRA, catName);

                    startActivity(in);

                }
            });


        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();    //To change body of overridden methods use File | Settings | File Templates.
        stopManagingCursor(model);
        dataBaseHelper.close();
    }
}
