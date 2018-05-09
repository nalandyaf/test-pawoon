package com.work.nalandya.pawoon_test.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.work.nalandya.pawoon_test.R;
import com.work.nalandya.pawoon_test.model.response.ResponseTodoList;
import com.work.nalandya.pawoon_test.presenter.callback.RecyclerListener;
import com.work.nalandya.pawoon_test.presenter.localdb.table.TodoList;
import com.work.nalandya.pawoon_test.view.viewholder.VHTodoList;

import java.util.ArrayList;

/**
 * Created by Rizky Nalandya on 5/9/2018.
 */

public class AdapterTodoList extends BaseAdapter<TodoList> {

    private ArrayList<TodoList> listItemCopy;


    //Adapter Constructor
    public AdapterTodoList(ArrayList<TodoList> listItem, RecyclerListener recyclerListener) {
        super(listItem, recyclerListener);
        listItemCopy = new ArrayList<>();
        this.listItemCopy.addAll(listItem);
    }

    //Adding Data To ArrayList
    public void add(ArrayList<TodoList> posts) {
        int OldSize = listItem.size();
        listItem.addAll(posts);
        listItemCopy.addAll(posts);
        notifyItemRangeChanged(OldSize, posts.size());
    }

    //Set Layout For Recyclerview adapter
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.adapter_list_todo, parent, false);
        VHTodoList vhTodoList = new VHTodoList(view, recyclerListener);
        return vhTodoList;
    }

    //Binding to ViewHolder
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof VHTodoList && listItem != null && listItem.size() > 0) {
            ((VHTodoList) holder).setData(listItem.get(position));
        }
    }

}
