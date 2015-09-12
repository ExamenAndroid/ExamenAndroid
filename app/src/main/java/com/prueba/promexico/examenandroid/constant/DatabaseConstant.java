package com.prueba.promexico.examenandroid.constant;

import android.os.Environment;

/**
 * Created by Said on 12/09/2015.
 */
public class DatabaseConstant {
    public static final String NAME_DB = "promexico.db";
    public static String ROOT_DB  = Environment.getExternalStorageDirectory().getPath();
    public static final String FOLDER = "promexico";
    public static String PATH_DB = ROOT_DB + "/" + FOLDER + "/";
    public static int VERSION_DB = 1;


    public static String QUERY_OFICINAS = "SELECT * FROM OFICINAS_EXTERIOR";
}
