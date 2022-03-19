package com.example.ssuk;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PictureGameListAdapter extends BaseAdapter {

    List<PictureGameItem> items;
    Context context;

    public PictureGameListAdapter(Context context, List<PictureGameItem> items) {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.picture_game_answer_item, viewGroup, false);

        TextView answerView = view1.findViewById(R.id.answer);
        ImageView imageView = view1.findViewById(R.id.picture);

        PictureGameItem item = items.get(i);
        answerView.setText(item.getName());
        imageView.setImageResource(item.getId_());

        return view1;
    }
}
