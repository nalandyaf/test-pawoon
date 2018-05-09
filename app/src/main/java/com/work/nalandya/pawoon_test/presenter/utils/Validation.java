package com.work.nalandya.pawoon_test.presenter.utils;

import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Validation {
    private static final String EMAIL_ERROR = "Insert Valid Email";
    private static final String INPUT_ERROR = "This Field Must Be Filled";
    private static final String PASSWORD_ERROR = "This Field Must Be Filled minimum 6 character";
    private static final String MIN_ERROR = "This Field Must Be Filled minimum ";
    private static final String CHAR = " character";
    private static final String PASSWORD_NOT_SAME = "Your password doesn't match";

    public static boolean checkEmail(EditText editText) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = editText.getText().toString();
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (editText.getText().toString().equals("") && editText.getText().length() == 0) {
            editText.setError(INPUT_ERROR);
            return false;
        } else {
            if (matcher.matches()) {
                return true;
            } else {
                editText.setError(EMAIL_ERROR);
                editText.requestFocus();
                return false;
            }

        }


    }

    public static boolean checkPassword(EditText editText) {
        if (editText.getText().toString().equals("") || editText.getText().length() < 6) {
            editText.setError(PASSWORD_ERROR);
            editText.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkEdittext(EditText editText) {
        if (editText.getText().toString().equals("") && editText.getText().length() == 0) {
            editText.setError(INPUT_ERROR);
            editText.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkEdittextMin(EditText editText, int min) {
        if (editText.getText().toString().equals("") || editText.getText().length() < min) {
            editText.setError(MIN_ERROR + String.valueOf(min) + CHAR);
            editText.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkPasswordSame(EditText password, EditText passwordConfirm) {
        if (!password.getText().toString().equals(passwordConfirm.getText().toString())) {
            passwordConfirm.setError(PASSWORD_NOT_SAME);
            passwordConfirm.requestFocus();
            return false;
        } else {
            return true;
        }
    }
}
