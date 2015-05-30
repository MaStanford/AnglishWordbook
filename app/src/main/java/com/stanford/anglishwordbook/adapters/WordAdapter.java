package com.stanford.anglishwordbook.adapters;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.stanford.anglishwordbook.R;
import com.stanford.anglishwordbook.models.Word;

import java.util.List;

/**
 * Created by m.stanford on 5/29/15.
 */
public class WordAdapter extends BaseAdapter{
    List<Word> mWordList;
    private Context mContext;

    public WordAdapter(Context context, List<Word> wordList) {
        mContext = context;
        mWordList = wordList;
    }

    @Override
    public int getCount() {
        return mWordList.size();
    }

    @Override
    public Object getItem(int position) {
        return mWordList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mWordList.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_wordlist, parent, false);
        }

        TextView word = ViewHolder.get(convertView, R.id.tv_word);
        TextView wordType = ViewHolder.get(convertView, R.id.tv_word_type);
        TextView attested = ViewHolder.get(convertView, R.id.tv_attested);
        TextView unAttested= ViewHolder.get(convertView, R.id.tv_unattested);

        Word data = (Word) getItem(position);
        word.setText("Word: " + data.getEnglishWord());
        wordType.setText("Type: " + data.getType());
        attested.setText("Attested: " + data.getAttested().toString());
        unAttested.setText("Unattested: " + (data.getUnAttested().toString()));

        return convertView;
    }

    public void updateWords(List<Word> words) {
        this.mWordList = words;
        notifyDataSetChanged();
    }

    public static class ViewHolder {
        @SuppressWarnings("unchecked")
        public static <T extends View> T get(View view, int id) {
            SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
            if (viewHolder == null) {
                viewHolder = new SparseArray<View>();
                view.setTag(viewHolder);
            }
            View childView = viewHolder.get(id);
            if (childView == null) {
                childView = view.findViewById(id);
                viewHolder.put(id, childView);
            }
            return (T) childView;
        }
    }
}
