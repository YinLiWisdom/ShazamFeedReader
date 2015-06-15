package com.yinli.shazamfeedreader.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yinli.shazamfeedreader.R;

/**
 * ShazamFeedReader
 * Created by Yin Li on 15/06/15.
 * Copyright (c) 2015 Yin Li. All rights reserved.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{
    private String[] titles;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTextView;
        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.title);
        }

        public TextView getTextView() {
            return mTextView;
        }
    }

    public ListAdapter(String[] titles) {
        this.titles = titles;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);

        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.getTextView().setText(titles[i]);
    }

    @Override
    public int getItemCount() {
        return this.titles.length;
    }
}
