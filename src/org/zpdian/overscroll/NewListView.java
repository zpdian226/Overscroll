package org.zpdian.overscroll;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

public class NewListView extends ListView implements OverScroll.CurrentPostion{

    private OverScroll mOverScroll;
    
    public NewListView(Context context) {
        super(context);
        mOverScroll = new OverScroll(context, this);
    }
    

    public NewListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mOverScroll = new OverScroll(context, attrs, defStyle, this);
    }


    public NewListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mOverScroll = new OverScroll(context, attrs, this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        mOverScroll.onTouchEvent(ev);
        if (mOverScroll.isOutBound()) {
            return true;
        }
        return super.onTouchEvent(ev);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        mOverScroll.dispatchTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }
    
   
    @Override
    public int position() {
        if (this.getFirstVisiblePosition() == 0) {
            return OverScroll.START;
        } else if (this.getLastVisiblePosition() == this.getCount() - 1) {
            return OverScroll.END;
        } else {
            return OverScroll.MIDDLE;
        }
    }

}
