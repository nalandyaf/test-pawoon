package com.work.nalandya.pawoon_test.view.viewholder;

import android.view.View;
import android.widget.ProgressBar;

import com.work.nalandya.pawoon_test.R;
import com.work.nalandya.pawoon_test.presenter.callback.RecyclerListener;


public class VHLoad extends BaseViewHolder<Object> {

    public ProgressBar progressBar;


    public VHLoad(View itemView, RecyclerListener recyclerListener) {
        super(itemView, recyclerListener);
        progressBar = findView(R.id.load);
    }
}

