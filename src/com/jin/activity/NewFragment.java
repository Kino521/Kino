package com.jin.activity;

import android.os.Bundle;
import com.actionbarsherlock.app.SherlockFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NewFragment extends SherlockFragment {

	public NewFragment(){
		setRetainInstance(true);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.main_page_layout, null);
		return view;
	}

	
	
	
}
