package com.example.ykozh.criminalintent;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.util.Date;

public class FancyImageDialog extends DialogFragment {

    private ImageView mImageView;
    private static final String ARG_IMAGE = "image";
    public static final String EXTRA_IMAGE =
            "com.example.ykozh.criminalintent.image";

    public static FancyImageDialog newInstance(File file) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_IMAGE, file);
        FancyImageDialog fragment = new FancyImageDialog();
        fragment.setArguments(args);
        return fragment;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_image, null);

        mImageView = (ImageView) v.findViewById(R.id.full_image);

        File file = (File) getArguments().getSerializable(ARG_IMAGE);
        Bitmap bitmap = PictureUtils.getScaledBitmap(
                file.getPath(), getActivity());
        mImageView.setImageBitmap(bitmap);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which){
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
