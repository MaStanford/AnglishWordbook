package com.stanford.anglishwordbook.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
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
import com.stanford.anglishwordbook.dialogs.WordDialog;

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
                if (e == null) {
                    getActivity().findViewById(R.id.tv_word_error).setVisibility(View.GONE);
                    mWordManager.setWordList(parseObjects);
                    mAdapter.updateWords(parseObjects);
                } else {
                    mAdapter.updateWords(null);
                    getActivity().findViewById(R.id.tv_word_error).setVisibility(View.VISIBLE);
                    ((TextView) getActivity().findViewById(R.id.tv_word_error)).setText("Word not found, you could create a definition.");
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        getActivity().findViewById(R.id.tv_word_error).setVisibility(View.GONE);

        //Set up the keyboard
        ((EditText) getActivity().findViewById(R.id.et_wordbook)).setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    queryWord();
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getActivity().findViewById(R.id.et_wordbook).getWindowToken(), 0);
                }
                return false;
            }
        });

        //set up the list
        mListView = (ListView) getActivity().findViewById(R.id.lv_wordbook);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                launchWordDialog(position);
            }
        });
        mAdapter = new WordAdapter(getActivity(), mWordManager.getWordList());
        mListView.setAdapter(mAdapter);
    }

    private void launchWordDialog(int position) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        WordDialog wordDialog = WordDialog.createFragment(position);
        wordDialog.show(fm, MainActivity.FRAG_TAG_WORD);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
