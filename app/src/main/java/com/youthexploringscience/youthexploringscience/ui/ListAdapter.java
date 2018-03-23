package com.youthexploringscience.youthexploringscience.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youthexploringscience.youthexploringscience.R;

/**
 * Created by AgafonovaO on 3/23/2018.
 */

public class ListAdapter  extends RecyclerView.Adapter<ListAdapter.ResourceListAdapterViewHolder> {

    private String[] mDataset;

    public ListAdapter(String[] myDataset) {
        mDataset = myDataset;
    }

    @Override
    public ResourceListAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.resource_item, parent, false);
        ResourceListAdapterViewHolder viewHolder = new ResourceListAdapterViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ResourceListAdapterViewHolder holder, int position) {
        holder.mResourceTextView.setText(mDataset[position]);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    public class ResourceListAdapterViewHolder extends RecyclerView.ViewHolder {

        public final TextView mResourceTextView;

        public ResourceListAdapterViewHolder(View itemView) {
            super(itemView);
            mResourceTextView = (TextView) itemView.findViewById(R.id.resource_title);
        }
    }

}
