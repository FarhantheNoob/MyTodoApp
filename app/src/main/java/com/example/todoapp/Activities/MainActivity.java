package com.example.todoapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.todoapp.R;
import com.example.todoapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.ToolBar);
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
                Toast.makeText(MainActivity.this, "Delete all Todos.", Toast.LENGTH_SHORT).show();
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