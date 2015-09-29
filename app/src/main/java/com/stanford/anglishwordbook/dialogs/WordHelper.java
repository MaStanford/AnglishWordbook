package com.stanford.anglishwordbook.dialogs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.ParseObject;
import com.stanford.anglishwordbook.R;

import java.util.ArrayList;

/**
 * Created by m.stanford on 9/29/15.
 */
public class WordHelper {
    public static final void buildWord(LayoutInflater inflater, ViewGroup container, ParseObject word, ArrayList<ParseObject> comments){
        //TODO: Build the list here with the comments.
        View view = inflater.inflate(R.layout.item_wordlist, null);

        container.addView(view);
    }
}
