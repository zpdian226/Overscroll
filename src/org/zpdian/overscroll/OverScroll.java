
package org.zpdian.overscroll;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.OnGestureListener;
import android.view.animation.TranslateAnimation;

public class OverScroll {

    private boolean mOutBound = false;

    private View mNewView;

    public static final int START = 0;

    public static final int END = 1;

    public static final int MIDDLE = 2;

    public OverScroll(Context context, AttributeSet attrs, View v) {
        mNewView = v;
    }

    public OverScroll(Context context, AttributeSet attrs, int defStyle, View v) {
        mNewView = v;
    }

    public OverScroll(Context context, View v) {
        mNewView = v;
    }

    public interface CurrentPostion {
        int position();
    }

    public GestureDetector gestureDetector = new GestureDetector(new OnGestureListener() {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.w("myTag", "onSingleTapUp");
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            Log.w("myTag", "onShowPress");
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if ((((CurrentPostion) mNewView).position() == START && distanceY < 0)
                    || (((CurrentPostion) mNewView).position() == END) && (distanceY > 0)) {
                if (distanceY != 0) {
                    mNewView.scrollBy(0, (int) (distanceY * 0.5f));
                }
                return true;
            }
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }
    });

    public void onTouchEvent(MotionEvent ev) {
        int act = ev.getAction();
        switch (act) {
            case MotionEvent.ACTION_UP:
                Log.e("myTag", "ACTION_UP");
                Rect rect = new Rect();
                mNewView.getLocalVisibleRect(rect);
                TranslateAnimation am = new TranslateAnimation(0, 0, -rect.top, 0);
                am.setDuration(300);
                mNewView.startAnimation(am);
                mNewView.scrollTo(0, 0);
                mOutBound = false;
                break;
            default:
                break;
        }
    }

    public boolean isOutBound() {
        return mOutBound;
    }

    public void dispatchTouchEvent(MotionEvent event) {
        Log.d("myTag", "dispatchTouchEvent_outBound = " + mOutBound);
        if (gestureDetector.onTouchEvent(event)) {
            mOutBound = true;
            Log.d("myTag", "gestureDetector.onTouchEvent(event) == true");
        } else {
            Log.d("myTag", "gestureDetector.onTouchEvent(event) == false");
        }
    }

}
