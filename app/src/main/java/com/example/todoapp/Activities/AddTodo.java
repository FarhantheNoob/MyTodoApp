package com.example.todoapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.example.todoapp.databinding.ActivityAddTodoBinding;

import java.util.Calendar;
import java.util.Objects;

public class AddTodo extends AppCompatActivity {

    ActivityAddTodoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddTodoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.AddTodoToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    public void AddReminder(View view) {
        if (binding.switch1.isChecked()){
            binding.RemiderLayout.setVisibility(View.VISIBLE);
            binding.SetReminderText.setVisibility(View.VISIBLE);
            binding.DatePicker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Calendar calendar = Calendar.getInstance();
                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH);
                    int day = calendar.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog dialog = new DatePickerDialog(AddTodo.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                            Calendar calendar1 = Calendar.getInstance();
                            calendar1.set(Calendar.YEAR, year);
                            calendar1.set(Calendar.MONTH, month);
                            calendar1.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                            CharSequence charSequence = DateFormat.format("MMM d, yyyy", calendar1);
                            binding.DatePicker.setText(charSequence);
                        }
                    }, year, month, day);
                    dialog.show();
                }
            });

            binding.TimePicker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Calendar calendar = Calendar.getInstance();
                    int hour = calendar.get(Calendar.HOUR_OF_DAY);
                    int minute = calendar.get(Calendar.MINUTE);

                    TimePickerDialog dialog = new TimePickerDialog(AddTodo.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int hourOfDay, int min) {
                            Calendar calendar1 = Calendar.getInstance();
                            calendar1.set(Calendar.HOUR_OF_DAY, hourOfDay);
                            calendar1.set(Calendar.MINUTE, min);

                            CharSequence charSequence = android.text.format.DateFormat.format("hh:mm a", calendar1);
                            binding.TimePicker.setText(charSequence);
                        }
                    }, hour, minute, false);
                    dialog.show();
                }
            });
        }else {
            binding.RemiderLayout.setVisibility(View.GONE);
            binding.SetReminderText.setVisibility(View.GONE);
        }
    }
}