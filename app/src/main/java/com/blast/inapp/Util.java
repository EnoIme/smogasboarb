package com.blast.inapp;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * Created by owner on 25/9/2016.
 */
public class Util {
    private static final String TAG = "Util";

    static List<Person> getPeopleList(Context context) {
        List<Person> people = new ArrayList<>();

        people.add(new Person("Enoobong", "Ibanga", context.getResources().getString(R.string.enoobong_role), context.getResources().getString(R.string.enoobong),
                ContextCompat.getDrawable(context, R.drawable.enoobong)));
        people.add(new Person("Obaro", "Ogbo", context.getResources().getString(R.string.ogbo_role),
                context.getResources().getString(R.string.ogbo),
                ContextCompat.getDrawable(context, R.drawable.obaro)));
        people.add(new Person("Peter", "Parker", context.getResources().getString(R.string.parker_role),
                context.getResources().getString(R.string.parker),
                ContextCompat.getDrawable(context, R.drawable.parker)));
        people.add(new Person("Clark", "Kent", context.getResources().getString(R.string.kent_role),
                context.getResources().getString(R.string.kent),
                ContextCompat.getDrawable(context, R.drawable.kent)));
        people.add(new Person("Barack", "Obama", context.getResources().getString(R.string.obama_role),
                context.getResources().getString(R.string.obama),
                ContextCompat.getDrawable(context, R.drawable.obama)));
        people.add(new Person("Bruce", "Wayne", context.getResources().getString(R.string.wayne_role),
                context.getResources().getString(R.string.wayne),
                ContextCompat.getDrawable(context, R.drawable.wayne)));
        people.add(new Person("Oliver", "Queen", context.getResources().getString(R.string.queen_role),
                context.getResources().getString(R.string.queen),
                ContextCompat.getDrawable(context, R.drawable.queen)));

        Collections.shuffle(people, new Random(System.nanoTime()));
        return people;
    }

    static Person getRandomPerson(Context context){
        return getPeopleList(context).get(0);
    }

    static long getDate(String date){
        Log.d(TAG, date);
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime((new SimpleDateFormat("dd/MM/yyyy", Locale.UK)).parse(date));
        } catch (Exception e){
            Log.e(TAG,e.getMessage());
        }
        Log.d(TAG, calendar.getTimeInMillis() + "");
        return calendar.getTimeInMillis();
    }

    static String formatDate(long date){
        Log.d(TAG, date+"");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        Log.d(TAG,new SimpleDateFormat("dd/MM/yyyy", Locale.UK).format(calendar.getTime()));
        return new SimpleDateFormat("dd/MM/yyyy", Locale.UK).format(calendar.getTime());
    }
}
