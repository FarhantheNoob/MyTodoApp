package com.example.todoapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.todoapp.Adapter.TodoAdapter;
import com.example.todoapp.Database.SQLiteHelper;
import com.example.todoapp.Models.Todos;
import com.example.todoapp.R;
import com.example.todoapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    List<Todos> mTodos;
    TodoAdapter adapter;
    SQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.ToolBar);

        db = new SQLiteHelper(this);
        mTodos = new ArrayList<>(db.getAllData());
        adapter = new TodoAdapter(MainActivity.this, mTodos);
        if (!db.getAllData().isEmpty()){
            binding.TodoList.setVisibility(View.VISIBLE);
            binding.TodoList.setAdapter(adapter);
            binding.TodoList.setLayoutManager(new LinearLayoutManager(this));
            binding.imageView4.setVisibility(View.GONE);
            adapter.notifyDataSetChanged();
        }else {
            Toast.makeText(MainActivity.this, "No Data found.", Toast.LENGTH_SHORT).show();
            binding.TodoList.setVisibility(View.GONE);
            binding.imageView4.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.sorting:
                Toast.makeText(MainActivity.this, "Sort the list.", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.deleteList:
                Toast.makeText(MainActivity.this, "Deleted all Todos.", Toast.LENGTH_SHORT).show();
                db.deleteAllData();
                binding.TodoList.setVisibility(View.GONE);
                binding.imageView4.setVisibility(View.VISIBLE);
                binding.TodoList.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                return true;

            case R.id.settings:
                Toast.makeText(MainActivity.this, "Settings Page.", Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }

    public void AddTodo(View view) {
        startActivity(new Intent(MainActivity.this, AddTodo.class));
        finishAffinity();
    }
}