package com.blast.inapp;

import android.databinding.DataBindingUtil;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blast.inapp.databinding.EmployerListItemBinding;

import java.util.List;

/**
 * Created by owner on 22/10/2016.
 */

class RecyclerViewCursorAdapter extends RecyclerView.Adapter<RecyclerViewCursorAdapter.ViewHolder> {
    private List<Employer> employers;
    static final String TAG = "RecyclerViewAdapter";

    RecyclerViewCursorAdapter(List<Employer> employers) {
        this.employers = employers;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employer_list_item, parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Employer employer = employers.get(position);
        holder.getItemBinding().setVariable(com.blast.inapp.BR.employer, employer);
        holder.getItemBinding().executePendingBindings();
    }


    @Override
    public int getItemCount() {
        return employers.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private EmployerListItemBinding itemBinding;

        ViewHolder(View itemView) {
            super(itemView);
            itemBinding = DataBindingUtil.bind(itemView);
        }

        EmployerListItemBinding getItemBinding(){
            return itemBinding;
        }

    }
}