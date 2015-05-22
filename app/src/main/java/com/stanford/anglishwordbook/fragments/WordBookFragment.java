package com.stanford.anglishwordbook.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stanford.anglishwordbook.R;
import com.stanford.anglishwordbook.activities.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WordBookFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WordBookFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WordBookFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private int mPageIndex;

    private OnFragmentInteractionListener mListener;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_word_book, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }
        ((MainActivity) activity).onSectionAttached(mPageIndex);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
