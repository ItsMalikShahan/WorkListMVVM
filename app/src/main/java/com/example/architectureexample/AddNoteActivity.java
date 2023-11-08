package com.example.architectureexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE ="EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION ="EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY ="EXTRA_PRIORITY  ";
    EditText title, description;
    NumberPicker priority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        title = findViewById(R.id.et_title);
        description = findViewById(R.id.et_description);
        priority = findViewById(R.id.np_numberPickerPriority);

        priority.setMinValue(0);
        priority.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_cancel);
        setTitle("Add Note");

    }

    public void saveNote() {
        String titleStr = title.getText().toString();
        String descriptionStr = title.getText().toString();
        int priorityInt = priority.getValue();
        if (titleStr.trim().isEmpty() || descriptionStr.trim().isEmpty()) {
            Toast.makeText(this, "Please provide title and description", Toast.LENGTH_SHORT).show();
            return;

        }
        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE,titleStr);
        data.putExtra(EXTRA_DESCRIPTION,descriptionStr);
        data.putExtra(EXTRA_PRIORITY, priorityInt);

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.i_save:
                saveNote();
            default:
                return super.onContextItemSelected(item);
        }
    }
}