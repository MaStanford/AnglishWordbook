package com.stanford.anglishwordbook.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.stanford.anglishwordbook.R;

/**
 * Created by m.stanford on 8/26/15.
 */
public class CommentView extends ViewGroup {


    public CommentView(Context context) {
        super(context);
        inflateLayout(context);
    }

    public CommentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateLayout(context);
    }

    public CommentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflateLayout(context);
    }

    private void inflateLayout(Context context){
        LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mInflater.inflate(R.layout.comment_layout, this, true);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
