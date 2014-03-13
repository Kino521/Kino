package com.jin.listener;
import com.jin.activity.R;
import com.slidingmenu.lib.SlidingMenu;

import android.content.Context;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

public class AdvGestureListenter extends BaseListener implements OnGestureListener{
	private ViewFlipper advPager;
	private int selectItem=0;
	private Context context;
	private SlidingMenu sm;
	public AdvGestureListenter(ViewFlipper advPager, int selectItem,Context context, SlidingMenu sm) {
		// TODO Auto-generated constructor stub
		this.advPager=advPager;
		this.selectItem=selectItem;
		this.context=context;
		this.sm=sm;
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
	}

	@Override
	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	/**onFling
	 * @paramp终点（e2）与down起点（e1）**/
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		// 滑动手势
		if(e2.getX()-e1.getX()>200){
			Animation rInAnim = AnimationUtils.loadAnimation(context, R.anim.push_right_in);  // 向右滑动左侧进入的渐变效果（alpha  0.1 -> 1.0）  
            Animation rOutAnim = AnimationUtils.loadAnimation(context, R.anim.push_right_out); // 向右滑动右侧滑出的渐变效果（alpha 1.0  -> 0.1）  
            advPager.setInAnimation(rInAnim);  
            advPager.setOutAnimation(rOutAnim);  
            advPager.showPrevious();  
		}else if (e2.getX() - e1.getX() < -200) {        // 从右向左滑动（右进左出）  
            Animation lInAnim = AnimationUtils.loadAnimation(context, R.anim.push_left_in);       // 向左滑动左侧进入的渐变效果（alpha 0.1  -> 1.0）  
            Animation lOutAnim = AnimationUtils.loadAnimation(context, R.anim.push_left_out);     // 向左滑动右侧滑出的渐变效果（alpha 1.0  -> 0.1）  
  
            advPager.setInAnimation(lInAnim);  
            advPager.setOutAnimation(lOutAnim);  
            advPager.showNext();  
            return true;  
        }  
		return true;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		advPager.stopFlipping();             // 点击事件后，停止自动播放  
        advPager.setAutoStart(false);  
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		e.getAction();
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		   advPager.stopFlipping();             // 点击事件后，停止自动播放  
	       advPager.setAutoStart(false); 
		return super.onTouch(v, event);
	}
	
	

}
