package com.prueba.promexico.examenandroid.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.MapFragment;
import com.prueba.promexico.examenandroid.R;
import com.prueba.promexico.examenandroid.bean.OficinasBean;
import com.prueba.promexico.examenandroid.constant.DatabaseConstant;
import com.prueba.promexico.examenandroid.database.DatabaseHelper;
import com.prueba.promexico.examenandroid.fragment.DialogAlertFragment;
import com.prueba.promexico.examenandroid.utils.CommonBeanUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by Said Rincon on 12/09/2015.
 */
public class QuestionsActivity extends AppCompatActivity implements View.OnClickListener, DialogInterface.OnClickListener {
    public static final String TAG = QuestionsActivity.class.getSimpleName();
    private Toolbar mToolbar;
    private TextView mTextViewAtras;
    private TextView mTextViewSincronizar;
    private int mOfficeSelected;
    private AppCompatSpinner mSpinnerOficinas;
    private DatabaseHelper mDatabaseHelper;
    private List<OficinasBean> mOficinasBeanList;
    private EditText mEditTextNPersona;
    private EditText mEditTextTrato;
    private EditText mEditTextAyuda;
    private EditText mEditTextLugar;
    private Button mButtonAceptar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        mOfficeSelected = getIntent().getIntExtra("officeSelected",0);
        mToolbar = (Toolbar) findViewById(R.id.widget_toolbar);
        mTextViewAtras = (TextView)mToolbar.findViewById(R.id.toolbar_logout);
        mTextViewSincronizar = (TextView)mToolbar.findViewById(R.id.toolbar_sincronizar);
        mSpinnerOficinas = (AppCompatSpinner) findViewById(R.id.spinner_oficinas);
        mEditTextNPersona = (EditText) findViewById(R.id.input_nombre_atendio);
        mEditTextTrato = (EditText) findViewById(R.id.input_trato);
        mEditTextAyuda = (EditText) findViewById(R.id.input_info_ayuda);
        mEditTextLugar = (EditText) findViewById(R.id.input_lugar_limpio);
        mButtonAceptar = (Button) findViewById(R.id.btn_aceptar);

        initDatabase();
        populateOficinas();
        initListeners();
    }

    private void initDatabase(){
        mDatabaseHelper = new DatabaseHelper(this);
        try {
            mDatabaseHelper.openDatabase();
        } catch (Exception e) {
            e.printStackTrace();
            DialogAlertFragment dialogError = DialogAlertFragment.newInstance("Error al acceder a la Base de datos");
            dialogError.setOnClickListener(this);
            dialogError.show(getSupportFragmentManager(), "dialogError");
        }
    }

    private void initListeners(){
        Log.i(TAG, "initListeners");
        mTextViewAtras.setOnClickListener(this);
        mTextViewSincronizar.setOnClickListener(this);
        mButtonAceptar.setOnClickListener(this);
    }

    private void populateOficinas(){
        List<Map<String,String>> resultOficinas = mDatabaseHelper.getSelectQueryMapValues(DatabaseConstant.QUERY_OFICINAS,null);
        mOficinasBeanList = CommonBeanUtils.getDataList(resultOficinas, OficinasBean.class);

        ArrayAdapter<OficinasBean> arrayAdapter = new ArrayAdapter<OficinasBean>(this,android.R.layout.simple_spinner_item,mOficinasBeanList);
        mSpinnerOficinas.setAdapter(arrayAdapter);
        mSpinnerOficinas.setSelection(mOfficeSelected);
    }

    @Override
    public void onClick(View v) {
        Log.i(TAG, "onClick");
        if (v.getId() == mTextViewAtras.getId()){
            finish();
        }
        else if(v.getId() == mTextViewSincronizar.getId()){

        }
        else{

        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        finish();
    }
}
