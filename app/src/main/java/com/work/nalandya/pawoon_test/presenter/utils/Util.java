package com.work.nalandya.pawoon_test.presenter.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;


import com.work.nalandya.pawoon_test.R;
import com.work.nalandya.pawoon_test.presenter.manager.IntentManager;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.regex.Pattern;


@EBean
public class Util {

    @Bean
    protected IntentManager intentManager;

    public String VALIDATION_MAX24 = "Masukkan minimal 3 karakter";
    public String VALIDATION_MINMAX13 = "Masukkan minimal 10 karakter";

    public static String noSpecialChar = "^[\\w_\\s]+$";
    public static String address = "^[\\w\\s.]+$";
    public static String usernameRegex = "^[\\w_]+$";
    public static String inputForm = "^[\\w,\\s]+$";
    public static String onlyCharUnderScore = "^[a-zA-Z\\s]+$";
    public static String onlyDigits = "^\\d+$";

    static final Integer LOCATION = 0x1;

    public void showDialogError(Context context, String message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog alert = builder.create();
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton(context.getResources().getString(R.string.try_again), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alert.cancel();
            }
        });
        builder.show();
    }

    public void showExpired(Context context) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog alert = builder.create();
        builder.setMessage(context.getResources().getString(R.string.expired_session));
        builder.setCancelable(false);
        builder.setPositiveButton(context.getResources().getString(R.string.login_again), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //intentManager.toLogin();
            }
        });
        builder.show();
    }

    public void showUnauthorized(Context context) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog alert = builder.create();
        builder.setMessage(context.getResources().getString(R.string.expired_session));
        builder.setCancelable(false);
        builder.setPositiveButton(context.getResources().getString(R.string.login_again), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //intentManager.toLogin();
            }
        });
        builder.show();
    }

    public boolean validateEdittext(EditText editText, int lengthMin, int lengthMax, String error) {
        if (editText.getText().length() < lengthMin || editText.getText().length() > lengthMax) {
            editText.setError(error);
            editText.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    public final static boolean regexOnlyChar(String character) {
        return regex(character, onlyCharUnderScore);
    }

    public final static boolean regexUsername(String character) {
        return regex(character, usernameRegex);
    }


    public final static boolean regex(String input, String regex) {
        final Pattern pattern = Pattern.compile(regex);
        return (pattern.matcher(input).matches());
    }


    public void exitDialog(final Activity context){
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog alert = builder.create();
        builder.setMessage(context.getString(R.string.prompt_exit));
        builder.setCancelable(true);
        builder.setNegativeButton(context.getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                context.finish();
            }
        });
        builder.setPositiveButton(context.getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alert.cancel();
            }
        });
        builder.show();
    }




}
