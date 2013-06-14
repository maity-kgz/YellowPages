package com.maggots.YellowPages;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
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
public class itemActivity extends Activity {
    static final String ID_EXTRA= "com.maggots.yellowpage.item";
    dataBaseHelper dataBaseHelper = null;
    Cursor model = null;
    itemCursorAdapter cursorAdapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        setContentView(R.layout.item);
        String catName = getIntent().getStringExtra(categoryActivity.ID_EXTRA);

        if(catName != null || !catName.isEmpty()){
            setTitle("Данные");
            ListView lv = (ListView)findViewById(R.id.itemListView);
            dataBaseHelper = new dataBaseHelper(getApplicationContext());
            model = dataBaseHelper.getItemByCategoryName(catName);
            startManagingCursor(model);
            cursorAdapter = new itemCursorAdapter(getApplicationContext(),model);
            lv.setAdapter(cursorAdapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    Intent in = new Intent(Intent.ACTION_DIAL);
                    String phone = ((TextView)view.findViewById(R.id.address)).getText().toString();
                    phone = phone.replaceAll(".+\\*+,","");
                    final CharSequence[] split = phone.split(",");

                    AlertDialog.Builder builder = new AlertDialog.Builder(itemActivity.this);
                    builder.setTitle("Выберите номер");
                    builder.setItems(split, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // The 'which' argument contains the index position
                            // of the selected item
                            Intent in = new Intent(Intent.ACTION_DIAL);
                            String tel = "tel:"+(split[which]).toString();


                            Uri uri = Uri.parse(tel);
                            in.setData(uri);

                            startActivity(in);

                        }
                    });
                    builder.create().show();
//                    Uri uri = Uri.parse(phone);
//                    in.setData(uri);
//
//                    startActivity(in);
//                    new AlertDialog.Builder(itemActivity.this)
//                            .setTitle("Delete entry")
//                            .setMessage("Are you sure you want to delete this entry?")
//                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    // continue with delete
//                                }
//                            })
//                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    // do nothing
//                                }
//                            })
//                            .show();

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
