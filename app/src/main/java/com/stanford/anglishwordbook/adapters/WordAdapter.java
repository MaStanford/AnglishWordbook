package com.stanford.anglishwordbook.adapters;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.parse.ParseObject;
import com.stanford.anglishwordbook.R;

import java.util.List;

/**
 * Created by m.stanford on 5/29/15.
 */
public class WordAdapter extends BaseAdapter{
    List<ParseObject> mWordList;
    private Context mContext;

    public WordAdapter(Context context, List<ParseObject> wordList) {
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

        ParseObject data = (ParseObject) getItem(position);

        String attestedDef = data.get("Attested").toString();

        String unattestedDef = data.get("Unattested").toString();

        word.setText(data.getString("Word"));
        wordType.setText(data.getString("Type").replaceAll("[^a-zA-Z0-9\\s]", " "));
        attested.setText(attestedDef);
        unAttested.setText(unattestedDef);

        return convertView;
    }

    public void updateWords(List<ParseObject> words) {
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
