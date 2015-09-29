package com.stanford.anglishwordbook.dialogs;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.parse.ParseObject;
import com.stanford.anglishwordbook.R;

import java.util.ArrayList;

/**
 * Created by m.stanford on 9/29/15.
 */
public class WordHelper {
    public static final void buildWord(LayoutInflater inflater, View container, ParseObject word, ArrayList<ParseObject> comments){
        //TODO: Build the list here with the comments.
        View view = inflater.inflate(R.layout.item_wordlist, null);

        FrameLayout wordFrame = (FrameLayout) container.findViewById(R.id.fl_word);
        wordFrame.addView(view);
    }
}
