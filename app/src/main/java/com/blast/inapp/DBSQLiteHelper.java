package com.blast.inapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by owner on 21/10/2016.
 */

class DBSQLiteHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "sample_database";
    private static final String TAG = "DBSQLiteHelper";
    DBSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL(DBContract.Employer.CREATE_TABLE);
       db.execSQL(DBContract.Employee.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        if(oldVersion == 0 && newVersion == 2){
//            db.execSQL("DROP TABLE IF EXISTS " + DBContract.Employee.TABLE_NAME);
//        } else {
//            db.execSQL("DROP TABLE IF EXISTS " + DBContract.Employer.TABLE_NAME);
//            db.execSQL("DROP TABLE IF EXISTS " + DBContract.Employee.TABLE_NAME);
//        }
        db.execSQL("DROP TABLE IF EXISTS " + DBContract.Employer.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DBContract.Employee.TABLE_NAME);
        onCreate(db);
    }

    

    void saveEmployerDetails(Employer employer){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DBContract.Employer.COLUMN_NAME, employer.getName());
        contentValues.put(DBContract.Employer.COLUMN_DESCRIPTION, employer.getDescription());
        contentValues.put(DBContract.Employer.COLUMN_FOUNDED_DATE, employer.getDateFounded());

        database.insert(DBContract.Employer.TABLE_NAME, null, contentValues);
        database.close();
    }

    List<Employer> getEmployerDetails(String name, String desc, long foundedDate){
        SQLiteDatabase database = getReadableDatabase();
        List<Employer> employers = new ArrayList<>();
        String[] projection = {
                DBContract.Employer._ID,
                DBContract.Employer.COLUMN_NAME,
                DBContract.Employer.COLUMN_DESCRIPTION,
                DBContract.Employer.COLUMN_FOUNDED_DATE
        };

        String selection = DBContract.Employer.COLUMN_NAME + " like ? and " +
                DBContract.Employer.COLUMN_FOUNDED_DATE + " > ? and " +
                DBContract.Employer.COLUMN_DESCRIPTION + " like ?";


        String[] selectionArgs = {"%" + name + "%", foundedDate + "", "%" + desc + "%"};

        Cursor cursor = database.query(
                DBContract.Employer.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        try{
            while(cursor.moveToNext()){
                Employer employer = new Employer();
                employer.setName(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.Employer.COLUMN_NAME)));
                employer.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.Employer.COLUMN_DESCRIPTION)));
                employer.setDateFounded(cursor.getLong(cursor.getColumnIndexOrThrow(DBContract.Employer.COLUMN_FOUNDED_DATE)));
                Log.d(TAG, ""+cursor.getLong(cursor.getColumnIndexOrThrow(DBContract.Employer.COLUMN_FOUNDED_DATE)));
                employers.add(employer);
            }
        } finally {
            cursor.close();
            database.close();
        }

        return employers;
    }

    void saveEmployeeDetails(Employee employee){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DBContract.Employee.COLUMN_FIRSTNAME, employee.getFirstName());
        values.put(DBContract.Employee.COLUMN_LASTNAME, employee.getLastName());
        values.put(DBContract.Employee.COLUMN_DATE_OF_BIRTH, employee.getDateOfBirth());
        values.put(DBContract.Employee.COLUMN_JOB_DESCRIPTION, employee.getJobDescription());
        values.put(DBContract.Employee.COLUMN_EMPLOYER_ID, employee.getEmployerID());
        values.put(DBContract.Employee.COLUMN_EMPLOYED_DATE, employee.getDateOfEmployment());

        database.insert(DBContract.Employee.TABLE_NAME, null, values);
        database.close();

    }

    List<Employee> getEmployeeDetails(String firstName, String lastName){
        SQLiteDatabase database = getReadableDatabase();
        List<Employee> employees = new ArrayList<>();

        String[] selectionArgs = {"%" + firstName + "%", "%" + lastName + "%"};

        Cursor cursor = database.rawQuery(DBContract.SELECT_EMPLOYEE_WITH_EMPLOYER, selectionArgs);
        try{
            while (cursor.moveToNext()){
                Employee employee = new Employee();
                employee.setFirstName(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.Employee.COLUMN_FIRSTNAME)));
                employee.setLastName(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.Employee.COLUMN_LASTNAME)));
                employee.setJobDescription(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.Employee.COLUMN_JOB_DESCRIPTION)));
                employee.setDateOfBirth(cursor.getLong(cursor.getColumnIndexOrThrow(DBContract.Employee.COLUMN_DATE_OF_BIRTH)));
                employee.setEmployerName(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.Employer.COLUMN_NAME)));
                employee.setEmployerDescription(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.Employer.COLUMN_DESCRIPTION)));
                employee.setEmployerFoundedDate(cursor.getLong(cursor.getColumnIndexOrThrow(DBContract.Employer.COLUMN_FOUNDED_DATE)));
                employees.add(employee);
            }
        } finally {
            cursor.close();
            database.close();
        }
        return employees;
    }

    Cursor getEmployersNames(){
        SQLiteDatabase database = getReadableDatabase();
        String[] queryCols = new String[]{DBContract.Employer._ID, DBContract.Employer.COLUMN_NAME};

        return database.query(
                DBContract.Employer.TABLE_NAME,     // The table to query
                queryCols,                                // The columns to return
                null,                                     // The columns for the WHERE clause
                null,                                     // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                      // don't sort
        );
    }


}
