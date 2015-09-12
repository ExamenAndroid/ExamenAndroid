package com.prueba.promexico.examenandroid.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.prueba.promexico.examenandroid.R;

public class DialogAlertFragment extends DialogFragment {
	public static final String EXTRA_MESSAGE = "dialog.alert.text";
	private String mMessage;
	private DialogInterface.OnClickListener mOnClickListener;
	
	public static DialogAlertFragment newInstance(String message) {
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_MESSAGE, message);
		
		DialogAlertFragment dialogAlertFragment = new DialogAlertFragment();
		dialogAlertFragment.setCancelable(false);
		dialogAlertFragment.setArguments(args);

		return dialogAlertFragment;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		mMessage =  (String) getArguments().getSerializable(EXTRA_MESSAGE);
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.app_name);
		builder.setMessage(mMessage);
		builder.setPositiveButton(R.string.text_btn_ok, mOnClickListener);

		AlertDialog alertDialog = builder.create();
		return alertDialog;
	}

	public DialogInterface.OnClickListener getOnClickListener() {
		return mOnClickListener;
	}

	public void setOnClickListener(DialogInterface.OnClickListener onClickListener) {
		this.mOnClickListener = onClickListener;
	}
}
