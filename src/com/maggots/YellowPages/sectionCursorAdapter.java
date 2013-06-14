package com.maggots.YellowPages;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: maity
 * Date: 5/10/13
 * Time: 10:45 AM
 * To change this template use File | Settings | File Templates.
 */
public class sectionCursorAdapter extends CursorAdapter {
    Context cntx;
    LayoutInflater inflater;
    private class ViewHolder {
//        View view;
        TextView txtCatName;


    }

    sectionCursorAdapter(Context cntx, Cursor c){
        super(cntx, c);
        this.cntx = cntx;
        inflater = LayoutInflater.from(cntx);

    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {

        View row=inflater.inflate(R.layout.section_list_item, viewGroup, false);
        ViewHolder holder=new ViewHolder();
//        holder.view = row;

        row.setTag(holder);
        return  row;

    }

    @Override
    public void bindView(View convertView, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder)convertView.getTag();

            holder.txtCatName = (TextView) convertView.findViewById(R.id.sectionName);

        String catName = cursor.getString(cursor.getColumnIndex("section"));
        holder.txtCatName.setText(catName.trim());



    }
}
