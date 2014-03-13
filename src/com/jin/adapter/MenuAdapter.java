package com.jin.adapter;

import com.jin.activity.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MenuAdapter extends BaseAdapter {
	private int[]iconMenus=new int[4];
	private LayoutInflater mInflater;
	public MenuAdapter(Context context,int[]iconMenus) {
		// TODO Auto-generated constructor stub
		this.mInflater = LayoutInflater.from(context);
		this.iconMenus=iconMenus;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return iconMenus.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Item item =new Item();
		if(convertView==null){
			convertView=mInflater.inflate(R.layout.menu_item, null);
			item.iconLay=(RelativeLayout)convertView.findViewById(R.id.icon_lay);
			item.icon=(ImageView)convertView.findViewById(R.id.icon_id);
			convertView.setTag(item);
		}
		else{
			convertView.getTag();
		}
		item.icon.setImageResource(iconMenus[position]);
		return convertView;
	}
	class Item{
		RelativeLayout iconLay;
		ImageView icon;
	}

}
