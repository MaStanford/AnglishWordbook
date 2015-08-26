package com.stanford.anglishwordbook.data;

import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by m.stanford on 8/26/15.
 */
public class WordManager {

    public static final String TAG = WordManager.class.getSimpleName();

    private static WordManager INSTANCE;

    private List<ParseObject> mWordList = new ArrayList<>();

    public static WordManager getInstance(){
        if(INSTANCE == null){
            INSTANCE = new WordManager();
        }
        return INSTANCE;
    }

    public List<ParseObject> getWordList() {
        return mWordList;
    }

    public void setWordList(List<ParseObject> wordList) {
        mWordList = wordList;
    }
}
