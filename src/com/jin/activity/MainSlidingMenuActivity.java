package com.jin.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Window;

import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingFragmentActivity;


/**
 * 滑动菜单框架
 * @author Ryan
 *
 */
public class MainSlidingMenuActivity extends SlidingFragmentActivity {

	private Fragment mContent;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// set the Above View
		SlidingMenu sm = getSlidingMenu();
		if (savedInstanceState != null)
			mContent = getSupportFragmentManager().getFragment(savedInstanceState, "mContent");
		if (mContent == null)
			mContent = new MainPageFragment(sm);	
		
		// set the Above View
		setContentView(R.layout.fragment_content);
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.content_frame, mContent)
		.commit();
		
		// set the Behind View(Menu)
		setBehindContentView(R.layout.fragment_menu);
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.menu_frame, new MenuFragment())
		.commit();
		
		// customize the SlidingMenu
		sm.setFadeDegree(1.0f);
		sm.setMode(SlidingMenu.LEFT);//Direction of the menu
		sm.setBehindWidth(110);
		
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		getSupportFragmentManager().putFragment(outState, "mContent", mContent);
	}
	
	public void switchContent(Fragment fragment) {
		mContent = fragment;
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.content_frame, fragment)
		.commit();
		getSlidingMenu().showContent();
	}

}
