package com.prueba.promexico.examenandroid.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.prueba.promexico.examenandroid.constant.DatabaseConstant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Said on 12/09/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    private SQLiteDatabase mSqLiteDatabase;
    private Context mContext;

    public DatabaseHelper(Context context) {
        super(context, DatabaseConstant.NAME_DB, null, DatabaseConstant.VERSION_DB);
        this.mContext = context;
    }


    public void openDatabase() throws Exception{
        String myPath = DatabaseConstant.PATH_DB + DatabaseConstant.NAME_DB;
        mSqLiteDatabase = SQLiteDatabase.openDatabase(myPath, null,SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase(){
        if (mSqLiteDatabase != null){
            mSqLiteDatabase.close();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



    public List<Map<String,String>> getSelectQueryMapValues(String queryString, String... parameters){
        List<Map<String,String>> resultList =  new ArrayList<Map<String,String>>();
        Cursor cursorResult = mSqLiteDatabase.rawQuery(queryString, parameters);
        while(cursorResult.moveToNext()){
            Map<String,String> valuesMap = new HashMap<String, String>();
            for(int i=0;i<cursorResult.getColumnCount();i++){
                valuesMap.put(cursorResult.getColumnName(i), cursorResult.getString(i));
            }
            resultList.add(valuesMap);
        }
        cursorResult.close();

        return resultList;
    }


    public Map<String,String> getSelectQueryFirstMap(String queryString, String... parameters){
        Map<String,String> result = new HashMap<String, String>();
        Cursor cursorResult = mSqLiteDatabase.rawQuery(queryString, parameters);
        if(cursorResult.moveToFirst()){
            for(int i=0;i<cursorResult.getColumnCount();i++){
                result.put(cursorResult.getColumnName(i), cursorResult.getString(i));
            }
        }
        cursorResult.close();

        return result;
    }
}
