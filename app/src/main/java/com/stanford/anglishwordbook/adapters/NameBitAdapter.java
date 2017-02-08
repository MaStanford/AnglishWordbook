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
public class NameBitAdapter extends BaseAdapter{
    List<ParseObject> mWordList;
    private Context mContext;

    public NameBitAdapter(Context context, List<ParseObject> wordList) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_namelist, parent, false);
        }

        TextView ne = ViewHolder.get(convertView, R.id.tv_new_english);
        TextView oe = ViewHolder.get(convertView, R.id.tv_old_english);
        TextView meaning = ViewHolder.get(convertView, R.id.tv_meaning);

        ParseObject data = (ParseObject) getItem(position);

        String meaningText = data.get("meaning").toString().replace(" ", "").replaceAll("[',\"()\\[\\]]", " ");

        ne.setText(data.getString("ne"));
        oe.setText(data.getString("oe"));
        meaning.setText(meaningText);

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
