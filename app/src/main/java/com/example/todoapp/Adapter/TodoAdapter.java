package com.example.todoapp.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.Database.SQLiteHelper;
import com.example.todoapp.R;
import com.example.todoapp.Models.Todos;
import com.example.todoapp.databinding.TodoItemsBinding;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

    private Context mContext;
    private List<Todos> mTodos;

    public TodoAdapter(Context mContext, List<Todos> mTodos) {
        this.mContext = mContext;
        this.mTodos = mTodos;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.todo_items, parent, false);
        return new TodoAdapter.TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        SQLiteHelper db = new SQLiteHelper(mContext);
        Cursor cursor = db.getAllData();
        if (cursor != null && cursor.getCount() > 0){
            while (cursor.moveToNext()){
                holder.binding.TodoName.setText(cursor.getString(1));
                holder.binding.TodoTime.setText(cursor.getString(2));
                holder.binding.TodoDate.setText(cursor.getString(3));
            }
        }else {
            Toast.makeText(mContext, "No Data retrieved.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return mTodos.size();
    }

    public class TodoViewHolder extends RecyclerView.ViewHolder{

        TodoItemsBinding binding;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = TodoItemsBinding.bind(itemView);
        }
    }
}
