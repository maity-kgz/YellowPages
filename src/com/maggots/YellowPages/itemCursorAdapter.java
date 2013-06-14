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
 * Date: 5/12/13
 * Time: 1:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class itemCursorAdapter extends CursorAdapter {
    Context cntx;
    LayoutInflater inflater;
    private class ViewHolder {
        //        View view;
        TextView txtTitle;
        TextView txtAddress;
        TextView txtPhone;
        TextView txtTag;



    }

    itemCursorAdapter(Context cntx, Cursor c){
        super(cntx, c);
        this.cntx = cntx;
        inflater = LayoutInflater.from(cntx);

    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {

        View row=inflater.inflate(R.layout.list_item, viewGroup, false);
        ViewHolder holder=new ViewHolder();
//        holder.view = row;

        row.setTag(holder);
        return  row;

    }

    @Override
    public void bindView(View convertView, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder)convertView.getTag();

        holder.txtTitle = (TextView) convertView.findViewById(R.id.title);
        holder.txtAddress = (TextView) convertView.findViewById(R.id.address);
        holder.txtPhone = (TextView) convertView.findViewById(R.id.phone);
        holder.txtTag = (TextView) convertView.findViewById(R.id.tag);

        String title = cursor.getString(cursor.getColumnIndex("title"));
        String address = cursor.getString(cursor.getColumnIndex("address"));
        String phone = cursor.getString(cursor.getColumnIndex("phone"));
        String tag = cursor.getString(cursor.getColumnIndex("tag"));

        holder.txtTitle.setText(title.trim());
        holder.txtAddress.setText(address.trim());
        holder.txtPhone.setText(phone.trim());
        holder.txtTag.setText(tag.trim());



    }
}
