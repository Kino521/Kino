package com.jin.activity;

import com.jin.adapter.MenuAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListView;

/**
 * 滑动菜单
 * @author Ryan
 */
public class MenuFragment extends Fragment {
	private ListView menuListView;
	private int []menu_icons=new int[]{R.drawable.blog_ui_dl_file,R.drawable.blog_ui_dl_drawble,R.drawable.blog_ui_dl_link,R.drawable.blog_ui_dl_chart};
	private MenuAdapter adapter;
	private MainSlidingMenuActivity fca;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View menuView=inflater.inflate(R.layout.list_fragment, container, false);
		menuListView=(ListView)menuView.findViewById(R.id.list_menu);
		fca = (MainSlidingMenuActivity) getActivity();
		adapter=new MenuAdapter(fca,menu_icons);
		menuListView.setAdapter(adapter);
		return menuView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	
		
	}



	// the meat of switching the above fragment
	private void switchFragment(Fragment fragment) {
		if (getActivity() == null)
			return;
		
		if (getActivity() instanceof MainSlidingMenuActivity) {
			//fca = (MainSlidingMenuActivity) getActivity();
			fca.switchContent(fragment);
		} 
	}
}
