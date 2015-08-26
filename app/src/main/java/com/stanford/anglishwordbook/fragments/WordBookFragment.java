package com.stanford.anglishwordbook.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.stanford.anglishwordbook.R;
import com.stanford.anglishwordbook.activities.MainActivity;
import com.stanford.anglishwordbook.adapters.WordAdapter;
import com.stanford.anglishwordbook.data.WordManager;
import com.stanford.anglishwordbook.models.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link WordBookFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WordBookFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String TAG = WordBookFragment.class.getSimpleName();
    private static final String KEY_WORD_LIST = "com.stanford.anglishwordbook.key_word_list";

    private int mPageIndex;

    private ListView mListView;
    private WordAdapter mAdapter;

    private WordManager mWordManager;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param pageIndex
     * @return A new instance of fragment WordBookFragment.
     */
    public static WordBookFragment newInstance(int pageIndex) {
        WordBookFragment fragment = new WordBookFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, pageIndex);
        fragment.setArguments(args);
        return fragment;
    }

    public WordBookFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPageIndex = getArguments().getInt(ARG_PARAM1);
        }
        mWordManager = WordManager.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_word_book, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            ((MainActivity) activity).onSectionAttached(mPageIndex);
        }catch (ClassCastException e){

        }
    }

    private void queryWord(){
        String word = ((EditText) getActivity().findViewById(R.id.et_wordbook)).getText().toString();
        ParseQuery<ParseObject> query = new ParseQuery("Word");
        query.whereContains("Word", word);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if(e == null){
                    ((TextView)getActivity().findViewById(R.id.tv_word_error)).setVisibility(View.GONE);
                    ArrayList<Word> wordList = new ArrayList<Word>();
                    if(parseObjects.size() > 0) {
                        for (ParseObject obj : parseObjects) {
                            Word word = new Word(obj.getString("Word"), obj.getString("Type"), (List<String>) (List<?>) obj.getList("Attested"), (List<String>) (List<?>) obj.getList("Unattested"));
                            Log.d(TAG, "New Word: " + word.toString());
                            wordList.add(word);
                        }
                        mWordManager.setWordList(wordList);
                        mAdapter.updateWords(mWordManager.getWordList());
                    }else{
                        mWordManager.getWordList().clear();
                        mAdapter.updateWords(mWordManager.getWordList());
                        ((TextView)getActivity().findViewById(R.id.tv_word_error)).setVisibility(View.VISIBLE);
                        ((TextView)getActivity().findViewById(R.id.tv_word_error)).setText("Word not found, you could create a definition.");
                    }
                }else{
                    //TODO: show error
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        ((TextView)getActivity().findViewById(R.id.tv_word_error)).setVisibility(View.GONE);

        //Set up the keyboard
        ((EditText) getActivity().findViewById(R.id.et_wordbook)).setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    queryWord();
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(((EditText) getActivity().findViewById(R.id.et_wordbook)).getWindowToken(), 0);
                }
                return false;
            }
        });

        //set up the list
        mListView = (ListView) getActivity().findViewById(R.id.lv_wordbook);
        mAdapter = new WordAdapter(getActivity(), mWordManager.getWordList());
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
