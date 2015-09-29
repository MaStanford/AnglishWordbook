package com.stanford.anglishwordbook.dialogs;

import android.app.Activity;
import android.app.Dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stanford.anglishwordbook.R;
import com.stanford.anglishwordbook.data.WordManager;

/**
 * Created by m.stanford on 7/30/15.
 */
public class WordDialog extends DialogFragment {

    public static final String KEY_WORD_MANAGER_POSITION = "com.stanford.anglishwordbook.keyWordManagerPosition";

    private int mWordPosition = -1;
    private WordManager mWordManager = WordManager.getInstance();

    public static WordDialog createFragment(int position) {
        WordDialog f = new WordDialog();
        Bundle args = new Bundle();
        args.putInt(KEY_WORD_MANAGER_POSITION, position);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Grab the args
        this.mWordPosition = getArguments().getInt(KEY_WORD_MANAGER_POSITION);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_word, container);
        getDialog().setTitle(mWordManager.getWordList().get(mWordPosition).getString("Word"));
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
