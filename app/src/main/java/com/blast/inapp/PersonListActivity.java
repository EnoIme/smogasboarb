package com.blast.inapp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.blast.inapp.databinding.PersonListBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;


import java.util.List;

/**
 * Created by owner on 25/9/2016.
 */
public class PersonListActivity extends AppCompatActivity {
    PersonListBinding personListBinding;
    private RecyclerView.LayoutManager layoutManager;
    private PersonListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Important Person List");

        personListBinding = DataBindingUtil.setContentView(this, R.layout.person_list);

        personListBinding.recyclerView.setHasFixedSize(true);

        List<Person> people = Util.getPeopleList(this);

        layoutManager = new GridLayoutManager(this,2);
        personListBinding.recyclerView.setLayoutManager(layoutManager);

        //((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);

        adapter = new PersonListAdapter(people, this);
        personListBinding.recyclerView.setAdapter(adapter);

        personListBinding.insertFAB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                adapter.addPerson(Util.getRandomPerson(PersonListActivity.this));
                ((GridLayoutManager) layoutManager).scrollToPositionWithOffset(0,0);
            }
        });
    }
}
