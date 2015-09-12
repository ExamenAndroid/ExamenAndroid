package com.prueba.promexico.examenandroid.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.prueba.promexico.examenandroid.R;
import com.prueba.promexico.examenandroid.bean.LoginBean;
import com.prueba.promexico.examenandroid.bean.ResultBean;
import com.prueba.promexico.examenandroid.listener.OnFinishListener;

import java.util.concurrent.TimeUnit;

/**
 * Created by BEST BUY on 12/09/2015.
 */
public class LoginAsyncTask extends AsyncTask<LoginBean,Void,ResultBean> {
    public static final String TAG = LoginAsyncTask.class.getSimpleName();


    private Context mContext;
    private ProgressDialog mProgressDialog;
    private OnFinishListener<ResultBean> mOnFinishListener;

    public LoginAsyncTask(Context context) {
        this.mContext=context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.i(TAG, "onPreExecute");

        mProgressDialog = ProgressDialog.show(mContext, mContext.getResources().getString(R.string.app_name), "Validando...");
        mProgressDialog.setCancelable(false);
    }

    @Override
    protected ResultBean doInBackground(LoginBean... params) {
        ResultBean resultBean = new ResultBean();
        try {
            TimeUnit.SECONDS.sleep(2);
            LoginBean loginBean = params[0];
            if (loginBean.getUsuario().equals("Prueba") && loginBean.getContrasenia().equals("Prueba")){
                resultBean.setResult(true);
            }
            else{
                resultBean.setDescription("Usuario y/o contraseña no válidos.");
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        return resultBean;
    }

    @Override
    protected void onPostExecute(ResultBean resultBean) {
        super.onPostExecute(resultBean);
        mProgressDialog.cancel();
        if (mOnFinishListener != null){
            mOnFinishListener.finishListener(0,resultBean);
        }
    }

    public OnFinishListener<ResultBean> getOnFinishListener() {
        return mOnFinishListener;
    }

    public void setOnFinishListener(OnFinishListener<ResultBean> onFinishListener) {
        this.mOnFinishListener = onFinishListener;
    }
}
