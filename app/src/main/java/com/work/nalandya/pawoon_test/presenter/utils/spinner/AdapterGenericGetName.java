package com.work.nalandya.pawoon_test.presenter.utils.spinner;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.work.nalandya.pawoon_test.R;

import java.lang.reflect.Method;


public class AdapterGenericGetName<T> extends ArrayAdapter<T> {

    public AdapterGenericGetName(Context context, T[] responses) {
        super(context, R.layout.spinner_list_item, responses);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater lInflater = (LayoutInflater) parent.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = convertView;
        if (view == null) view = lInflater.inflate(R.layout.spinner_item, parent, false);
        TextView text = (TextView) view.findViewById(R.id.item);

        try {
            T item = getItem(position);
            Method method = item.getClass().getMethod("getName", new Class[]{});
            String result = (String) method.invoke(item, new Object[]{});
            text.setText(result.toUpperCase());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    public int getPosition(Number id) {
        int position = 0;
        try {
            for (int pos = 0; pos < getCount(); pos++) {
                T item = getItem(pos);
                Method method = item.getClass().getMethod("getId", new Class[]{});
                Number result = (Number) method.invoke(item, new Object[]{});

                if (id.longValue() == result.longValue()) {
                    position = pos;
                    break;
                }
            }
        } catch (Exception e) {
            Log.e("Error : ", e.getMessage());
        }
        return position;
    }

}
