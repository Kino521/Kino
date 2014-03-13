package com.jin.activity;

import android.graphics.Rect;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.jin.adapter.AdvPagerAdapter;
import com.jin.listener.AdvGestureListenter;
import com.slidingmenu.lib.SlidingMenu;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

/**
 * 首页
 * 
 * @author jin
 * 
 */
public class MainPageFragment extends SherlockFragment implements OnTouchListener{
	private ViewPager advPager;
	private ImageView[] tips;
	private ImageView[] mImageViews;
	private int[] images = { R.drawable.bg_img_1, R.drawable.bg_img_2, R.drawable.bg_img_3, R.drawable.bg_img_4, R.drawable.bg_img_5, R.drawable.bg_img_6, R.drawable.bg_img_7 };
	private LinearLayout tipsImgs;
	private SlidingMenu sm;
	private Handler mHandler = null;
	private final static int IntervalTimes = 3000;
	private AdvPagerAdapter advAdapter = null;
	private int currentItem = 0;
	public MainPageFragment() {
		setRetainInstance(true);
	}

	public MainPageFragment(SlidingMenu sm) {
		setRetainInstance(true);
		this.sm = sm;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.main_page_layout, null);
		advPager = (ViewPager) view.findViewById(R.id.viewPager);
		tipsImgs = (LinearLayout) view.findViewById(R.id.tips_imgs);
		initgradio();
		advPager.setOnTouchListener(this);
		sm.addIgnoredView(advPager);
  
       
		advPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				currentItem = arg0;
				setImageBackground(currentItem);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				if(arg0==0&&arg2==0){
					sm.removeIgnoredView(advPager);
				}
				else if(arg0>=0&&arg2>0){
					sm.addIgnoredView(advPager);
				}
				
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				Log.e("hah", "hah");
			}
		});
		return view;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				switch (msg.what) {
				case 1:
			mThread.start();
					break;
				case 2:
					advPager.setCurrentItem(currentItem);
					break;

				default:
					break;
				}
			}
		};
		mHandler.sendEmptyMessage(1);
	}

	/**
	 * 通过此线程来实现viewPager轮播的效果
	 */
	Thread mThread = new Thread() {
		public void run() {
			try {
				while (true) {
					Thread.sleep(IntervalTimes);
					currentItem = (currentItem + 1) % images.length;
					mHandler.sendEmptyMessage(2);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};
	};

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		ActionBar myActionBar = getSherlockActivity().getSupportActionBar();
		myActionBar.setIcon(null);
		BitmapDrawable bg = (BitmapDrawable) getResources().getDrawable(R.drawable.bg_striped);
		bg.setTileModeXY(TileMode.REPEAT, TileMode.REPEAT);
		myActionBar.setBackgroundDrawable(bg);
		BitmapDrawable bgSplit = (BitmapDrawable) getResources().getDrawable(R.drawable.bg_striped_split_img);
		bgSplit.setTileModeXY(TileMode.REPEAT, TileMode.REPEAT);
		myActionBar.setSplitBackgroundDrawable(bgSplit);
		inflater.inflate(R.menu.optionsmenu, menu);
	}

	private void initgradio() {
		// 小圆点
		tips = new ImageView[images.length];
		for (int i = 0; i < tips.length; i++) {
			ImageView imageView = new ImageView(getActivity());
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(7, 7);
			params.setMargins(0, 0, 5, 5);
			imageView.setLayoutParams(params);
			tips[i] = imageView;
			if (i == 0) {
				tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
			} else {
				tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
			}
			tipsImgs.addView(tips[i]);

		}
		mImageViews = new ImageView[images.length];
		for (int i = 0; i < mImageViews.length; i++) {
			ImageView imageView = new ImageView(getActivity());
			imageView.setImageDrawable(getActivity().getResources().getDrawable(images[i]));
			imageView.setScaleType(ImageView.ScaleType.FIT_XY);
			imageView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			mImageViews[i] = imageView;
			advPager.addView(mImageViews[i]);
		}
		advAdapter = new AdvPagerAdapter(getActivity(), mImageViews);
		advPager.setAdapter(advAdapter);

	}

	private void setImageBackground(int selectItems) {
		// TODO Auto-generated method stub
		for (int i = 0; i < tips.length; i++) {
			if (i == selectItems) {
				tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
			} else {
				tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
			}
		}
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {

		return false;
	}

}
