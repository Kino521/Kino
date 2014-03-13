package com.jin.adapter;



import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class AdvPagerAdapter extends PagerAdapter{
	private ImageView[] imageViews;
	private Context context;
	public AdvPagerAdapter(Context context,ImageView[] images) {
		// TODO Auto-generated constructor stub
		this.imageViews=images;
		this.context=context;
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return imageViews.length;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0==arg1;
	}
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		container.removeView(imageViews[position% imageViews.length]);
	}
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		int currentItem = position % imageViews.length;
		ImageView iv = imageViews[currentItem];
		if (iv.getParent() == null) {
			((ViewGroup) container).addView(iv);
		}
		return iv;
	}


}
