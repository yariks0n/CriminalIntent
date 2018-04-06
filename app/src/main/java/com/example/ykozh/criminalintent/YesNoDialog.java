package com.example.ykozh.criminalintent;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

public class YesNoDialog extends DialogFragment {

    public Dialog onCreateDialog(Bundle savedInstanceState) {

            return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.before_delete)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which){
                                sendResult(Activity.RESULT_OK);
                            }
                        })
                .setNegativeButton(android.R.string.no,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sendResult(Activity.RESULT_CANCELED);
                            }
                        })
                .create();
    }

    private void sendResult(int resultCode) {
        if (getTargetFragment() == null) {
            return;
        }
        Intent intent = new Intent();
        getTargetFragment()
                .onActivityResult(getTargetRequestCode(), resultCode, intent);
    }

}
