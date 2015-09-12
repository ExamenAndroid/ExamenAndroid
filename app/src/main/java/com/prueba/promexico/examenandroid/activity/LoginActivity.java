package com.prueba.promexico.examenandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.prueba.promexico.examenandroid.R;
import com.prueba.promexico.examenandroid.bean.LoginBean;
import com.prueba.promexico.examenandroid.bean.ResultBean;
import com.prueba.promexico.examenandroid.fragment.DialogAlertFragment;
import com.prueba.promexico.examenandroid.listener.OnFinishListener;
import com.prueba.promexico.examenandroid.utils.NetworkUtils;
import com.prueba.promexico.examenandroid.task.LoginAsyncTask;

/**
 * Created by Said on 12/09/2015.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher, OnFinishListener<ResultBean> {
    public static final String TAG = LoginActivity.class.getSimpleName();
    private EditText mEditTextUsuario;
    private EditText mEditTextContrasenia;
    private Button mButtonAceptar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEditTextUsuario = (EditText) findViewById(R.id.input_usuario);
        mEditTextContrasenia = (EditText) findViewById(R.id.input_contrasenia);
        mButtonAceptar = (Button) findViewById(R.id.btn_aceptar);
        initListener();
    }

    private void initListener(){
        mButtonAceptar.setOnClickListener(this);
        mEditTextUsuario.addTextChangedListener(this);
        mEditTextContrasenia.addTextChangedListener(this);
    }

    @Override
    public void onClick(View v) {

        LoginBean loginBean = new LoginBean();
        loginBean.setUsuario(mEditTextUsuario.getText().toString().trim());
        loginBean.setContrasenia(mEditTextContrasenia.getText().toString().trim());

        LoginAsyncTask loginAsyncTask = new LoginAsyncTask(this);
        loginAsyncTask.setOnFinishListener(this);
        loginAsyncTask.execute(loginBean);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if(s.toString().trim().isEmpty()){
            mButtonAceptar.setEnabled(false);
        }
        else{
            mButtonAceptar.setEnabled(true);
        }
    }

    @Override
    public void finishListener(int id, ResultBean result) {
        if (result.isResult()){
            if (NetworkUtils.isNetworkAvailable(this)){
                Intent myIntent = new Intent(this, MapActivity.class);
                startActivity(myIntent);
            }
            else{
                Intent intent = new Intent(this, QuestionsActivity.class);
                startActivity(intent);
            }
        }
        else{
            DialogAlertFragment dialogError = DialogAlertFragment.newInstance("Usuario y/o contraseña no válidos.");
            dialogError.show(getSupportFragmentManager(),"dialogError");
        }
    }
}
