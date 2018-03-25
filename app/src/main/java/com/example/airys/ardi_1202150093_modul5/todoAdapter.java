package com.example.airys.ardi_1202150093_modul5;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

/**
 * Created by airys on 23/03/18.
 */

public class todoAdapter extends RecyclerView.Adapter<todoAdapter.ViewHolder>{
    private List<Todo> mTodoList;
    private Context mContext;
    private RecyclerView mRecyclerV;
    String warna;

    public class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        public TextView todoNameTxtV;
        public TextView todoDescTxtV;
        public TextView todoPrioTxtV;
        ConstraintLayout bgColor;

        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            todoNameTxtV = (TextView) v.findViewById(R.id.name);
            todoDescTxtV = (TextView) v.findViewById(R.id.desc);
            todoPrioTxtV = (TextView) v.findViewById(R.id.prio);
            SharedPreferences Preference = PreferenceManager.getDefaultSharedPreferences(v.getContext());
            warna = Preference.getString("chosenColor","-1");
            v.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            int mPosition = getLayoutPosition();
            String element = mTodoList.get(mPosition).toString();
            Toast.makeText(v.getContext(), todoNameTxtV.getText(), Toast.LENGTH_SHORT).show();
        }
    }


    public void add(int positition, Todo todo){
        mTodoList.add(positition, todo);
        notifyItemInserted(todo);

    }

    private void notifyItemInserted(Todo todo) {
    }

    public void remove(int position) {
        final Todo todo = mTodoList.get(position);
//        mTodoList.remove(position);
//        notifyItemRemoved(position);
        todoHelper dbHelper = new todoHelper(mContext);
        dbHelper.deleteTodoRecord(todo.getId(), mContext);

        mTodoList.remove(position);
//        mRecyclerV.removeViewAt(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mTodoList.size());
        notifyDataSetChanged();

    }

    public todoAdapter(List<Todo> myDataset, Context context, RecyclerView recyclerView) {
        mTodoList = myDataset;
        mContext = context;
        mRecyclerV = recyclerView;
    }


    @Override
    public todoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.single_row, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Todo todo = mTodoList.get(position);
        holder.todoNameTxtV.setText(todo.getName());
        holder.todoDescTxtV.setText(todo.getDesc());
        holder.todoPrioTxtV.setText(todo.getPrio());




        //listen to single view layout click
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Choose option");
                builder.setMessage("Update or delete user?");
                builder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        todoHelper dbHelper = new todoHelper(mContext);
                        dbHelper.deleteTodoRecord(todo.getId(), mContext);

                        mTodoList.remove(position);
                        mRecyclerV.removeViewAt(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, mTodoList.size());
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return mTodoList.size();
    }



}
