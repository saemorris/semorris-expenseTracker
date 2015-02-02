/*  ExpenseTracker is an android app to track expense claims
    Copyright (C) 2015  Sarah Morris

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.*/

package ca.ualberta.cs.expensetracker;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {

	public ArrayList<HashMap<String, String>> list;
    Activity activity;
     
    public ListViewAdapter(Activity activity,ArrayList<HashMap<String, String>> list){
        super();
        this.activity=activity;
        this.list=list;
    }

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
	
    private class ViewHolder{
        TextView txtFirst;
        TextView txtSecond;
        TextView txtThird;
    }

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
        
        LayoutInflater inflater=activity.getLayoutInflater();
         
        if(convertView == null){
             
            convertView=inflater.inflate(R.layout.row_view, null);
            holder=new ViewHolder();
             
            holder.txtFirst=(TextView) convertView.findViewById(R.id.expenseName);
            holder.txtSecond=(TextView) convertView.findViewById(R.id.expenseCurrency);
            holder.txtThird=(TextView) convertView.findViewById(R.id.expenseAmount);
            
            convertView.setTag(holder);
        }else{
             
            holder=(ViewHolder) convertView.getTag();
        }
         
        HashMap<String, String> map=list.get(position);
        holder.txtFirst.setText(map.get("First"));
        holder.txtSecond.setText(map.get("Second"));
        holder.txtThird.setText(map.get("Third"));
         
        return convertView;
	}

}
