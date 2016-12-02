package com.blast.inapp;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blast.inapp.databinding.PersonListItemBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import java.util.List;

/**
 * Created by owner on 25/9/2016.
 */
class PersonListAdapter extends RecyclerView.Adapter<PersonListAdapter.PersonViewHolder> {
    private List<Person> people;
    private Context context;

    private FirebaseRemoteConfig remoteConfig;
    private static final long CACHE_TIME_SECONDS = 0;
    private static final String TAG = "PersonListActivity";
    private static final String EXPERIMENT_A = "Variant_A";

    private static final String EXPERIMENT_B = "Variant_B";

    static class PersonViewHolder extends RecyclerView.ViewHolder {
        private PersonListItemBinding listItemBinding;

        PersonViewHolder(View v){
            super(v);
            listItemBinding = DataBindingUtil.bind(v);
        }

        PersonListItemBinding getBinding(){
            return listItemBinding;
        }
    }

    PersonListAdapter(List<Person> people, Context context) {
        this.people = people;
        this.context = context;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.person_list_item, parent, false);
        final PersonViewHolder holder = new PersonViewHolder(v);
        remoteConfig = FirebaseRemoteConfig.getInstance();

        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build();
        remoteConfig.setConfigSettings(configSettings);

        remoteConfig.setDefaults(R.xml.remote_config);

        remoteConfig.fetch(CACHE_TIME_SECONDS).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Log.d(TAG, "Fetch Succeeded");
                    remoteConfig.activateFetched();
                } else {
                    Log.d(TAG, "Fetch Failed");
                }

                displayExperiment(holder);
            }
        });
        return holder;
    }

    private void displayExperiment(PersonViewHolder holder) {
        String experiment = remoteConfig.getString("experiment_variant");
        Log.d(TAG, "Running experiment: " + experiment);
        FirebaseAnalytics.getInstance(context).setUserProperty("Experiment", experiment);
        holder.getBinding().exitButton.setVisibility(View.VISIBLE);
        if(experiment.equals(EXPERIMENT_A)){
            holder.getBinding().exitButton.setText(R.string.remove);
            holder.getBinding().exitButton.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
        } else if (experiment.equals(EXPERIMENT_B)){
            holder.getBinding().exitButton.setText(R.string.delete);
            holder.getBinding().exitButton.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
            holder.getBinding().exitButton.setTextColor(ContextCompat.getColor(context, android.R.color.white));
        } else {
            holder.getBinding().exitButton.setText(R.string.bye);
        }
    }

    @Override
    public void onBindViewHolder(final PersonViewHolder holder, final int position) {
        final Person person = people.get(position);
        holder.getBinding().setVariable(com.blast.inapp.BR.person, person);
        holder.getBinding().executePendingBindings();

        holder.getBinding().exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CLICK", String.format("position = %d, holder.getAdapterPosition() = %d, size = %d",
                        position, holder.getAdapterPosition(), getItemCount()));
                FirebaseAnalytics.getInstance(context).logEvent(FirebaseAnalytics.Event.BEGIN_CHECKOUT, new Bundle());
                people.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    void addPerson(Person person){
        people.add(0, person);
        notifyItemInserted(0);
    }
}
