package com.example.votingapp.utility;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import com.example.votingapp.R;

public class Util {

    public static void showNormal(Context context , String msg) {
        AlertDialog.Builder a = new AlertDialog.Builder(context);
        a.setTitle("Are you sure..???");
        a.setMessage(msg);
        a.setIcon(R.drawable.normal);

        a.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        a.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        a.show();

    }
    public static void showWarning(Context context , String msg) {
        AlertDialog.Builder a = new AlertDialog.Builder(context);
        a.setTitle("Are you sure..???");
        a.setMessage(msg);
        a.setIcon(R.drawable.warn);

        a.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        a.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        a.show();
    }

    public static void showError(Context context , String msg) {
        AlertDialog.Builder a = new AlertDialog.Builder(context);
        a.setTitle("Are you sure..???");
        a.setMessage(msg);
        a.setIcon(R.drawable.error);

        a.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        a.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        a.show();
    }
}
