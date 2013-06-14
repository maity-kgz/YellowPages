package com.maggots.YellowPages;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: maity
 * Date: 5/7/13
 * Time: 1:14 PM
 * To change this template use File | Settings | File Templates.
 */
class customAdapter extends ArrayAdapter<HashMap<String,String>> implements Filterable {

    private ArrayList<HashMap<String,String>> list;
    private ArrayList<HashMap<String,String>> origList;
    private LayoutInflater inflater;

    public customAdapter(Context context, int textViewResourceId, ArrayList<HashMap<String, String>> list) {
        super(context, textViewResourceId, list);
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,FilterResults results) {

                list = (ArrayList<HashMap<String,String>>) results.values; // has the filtered values
                notifyDataSetChanged();  // notifies the data with new filtered values
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                List<HashMap<String,String>> FilteredArrList = new ArrayList<HashMap<String,String>>();

                if (origList == null) {
                    origList = new ArrayList<HashMap<String,String>>(list); // saves the original data in mOriginalValues
                }

                /********
                 *
                 *  If constraint(CharSequence that is received) is null returns the mOriginalValues(Original) values
                 *  else does the Filtering and returns FilteredArrList(Filtered)
                 *
                 ********/
                if (constraint == null || constraint.length() == 0) {

                    // set the Original result to return
                    results.count = origList.size();
                    results.values = origList;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < origList.size(); i++) {
                        String data = origList.get(i).get(yellowPagesActivity.KEY_TITLE).toLowerCase();
                        if (data.contains(constraint.toString())) {


                            FilteredArrList.add(origList.get(i));
                        }
                    }
                    // set the Filtered result to return
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }
        };
        return filter;
    }

    @Override
    public int getCount() {
        return list.size();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public HashMap getItem(int i) {
        return list.get(i);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long getItemId(int i) {
        return i;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private class ViewHolder {
        TextView txtTitle;
        TextView txtAddress;
        TextView txtPhone;
        TextView txtTag;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {

            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_item, null);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.title);
            holder.txtAddress = (TextView) convertView.findViewById(R.id.address);
            holder.txtPhone = (TextView) convertView.findViewById(R.id.phone);
            holder.txtTag = (TextView) convertView.findViewById(R.id.tag);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        HashMap<String, String> item = (HashMap<String, String>)getItem(position);
        holder.txtTitle.setText(item.get(yellowPagesActivity.KEY_TITLE));
        holder.txtAddress.setText(item.get(yellowPagesActivity.KEY_ADDRESS));
        holder.txtPhone.setText(item.get(yellowPagesActivity.KEY_PHONE));
        holder.txtTag.setText(item.get(yellowPagesActivity.KEY_TAG));
        return convertView;
    }
}
