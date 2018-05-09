package com.work.nalandya.pawoon_test.view.viewholder;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.work.nalandya.pawoon_test.R;
import com.work.nalandya.pawoon_test.model.response.ResponseTodoList;
import com.work.nalandya.pawoon_test.presenter.callback.RecyclerListener;
import com.work.nalandya.pawoon_test.presenter.localdb.table.TodoList;

/**
 * Created by Rizky Nalandya on 5/9/2018.
 */

public class VHTodoList extends BaseViewHolder<Object> {

    private TextView title;
    private CheckBox check;

    //inisialisasi view
    public VHTodoList(View itemView, RecyclerListener recyclerListener) {
        super(itemView, recyclerListener);
        title = findView(R.id.title);
        check = findView(R.id.check);
    }

    // Set Data for each row
    public void setData(TodoList data) {
        title.setText(data.getTitle() + "");
        if (data.getCompleted() == 1){
            check.setChecked(true);
            check.setClickable(false);
        }else {
            check.setChecked(false);
            check.setClickable(false);
        }

    }


}
