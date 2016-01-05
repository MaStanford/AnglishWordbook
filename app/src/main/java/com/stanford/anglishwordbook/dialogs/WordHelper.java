package com.stanford.anglishwordbook.dialogs;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.parse.ParseObject;
import com.stanford.anglishwordbook.R;

import java.util.ArrayList;

/**
 * Created by m.stanford on 9/29/15.
 */
public class WordHelper {
    public static void buildWord(LayoutInflater inflater, View container, ParseObject word, ArrayList<ParseObject> comments){
        //TODO: Build the list here with the comments.
        View view = inflater.inflate(R.layout.item_wordlist, null);

        //Fill out the word
        populateWord(view, word);

        //Add the words to the UI
        FrameLayout wordFrame = (FrameLayout) container.findViewById(R.id.fl_word);
        wordFrame.addView(view);
    }

    public static void buildName(LayoutInflater inflater, View container, ParseObject word, ArrayList<ParseObject> comments) {
        //TODO: Build the list here with the comments.
        View view = inflater.inflate(R.layout.item_namelist, null);

        //Fill out the word
        populateName(view, word);

        //Add the words to the UI
        FrameLayout wordFrame = (FrameLayout) container.findViewById(R.id.fl_word);
        wordFrame.addView(view);
    }

    private static void populateName(View view, ParseObject parseObject) {
        TextView ne = (TextView) view.findViewById(R.id.tv_new_english);
        TextView oe = (TextView) view.findViewById(R.id.tv_new_english);
        TextView meaning = (TextView) view.findViewById(R.id.tv_meaning);

        String meaningText = parseObject.get("meaning").toString();

        ne.setText(parseObject.getString("ne"));
        oe.setText(parseObject.getString("oe"));
        meaning.setText(meaningText);
    }

    /**
     * This is grabbed directly from the Word list adapter
     * TODO: make a helper for both of these.
     * @param view
     * @param word
     */
    public static void populateWord(View view, ParseObject word){
        TextView englishWord = (TextView) view.findViewById(R.id.tv_word);
        TextView wordType = (TextView) view.findViewById(R.id.tv_word_type);
        TextView attested = (TextView) view.findViewById(R.id.tv_attested);
        TextView unAttested= (TextView) view.findViewById(R.id.tv_unattested);

        String attestedDef = word.get("Attested").toString();
        String unattestedDef = word.get("Unattested").toString();
        englishWord.setText(word.getString("Word"));
        wordType.setText(word.getString("Type").replaceAll("[^a-zA-Z0-9\\s]", " "));
        attested.setText(attestedDef);
        unAttested.setText(unattestedDef);
    }
}
