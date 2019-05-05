package com.example.roomtodoapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roomtodoapp.local.DatabaseClient;
import com.example.roomtodoapp.model.Task;

public class AddTask extends AppCompatActivity {

    private EditText editTextTask, editTextDesc, editTextFinishBy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        editTextTask = findViewById(R.id.editTextTask);
        editTextDesc = findViewById(R.id.editTextDesc);
        editTextFinishBy = findViewById(R.id.editTextFinishBy);

        findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTask();
            }
        });
    }

    private void saveTask() {
        final String task = editTextTask.getText().toString().trim();
        final String desc = editTextDesc.getText().toString().trim();
        final String finishBy = editTextFinishBy.getText().toString().trim();

        if (task.isEmpty()) {
            editTextTask.setError("Task Required");
            editTextTask.requestFocus();
            return;
        }

        if (desc.isEmpty()) {
            editTextDesc.setError("Desc required");
            editTextDesc.requestFocus();
            return;
        }

        if (finishBy.isEmpty()) {
            editTextFinishBy.setError("Finish by required");
            editTextFinishBy.requestFocus();
            return;
        }


        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                Task taskObj = new Task();
                taskObj.setTask(task);
                taskObj.setDesc(desc);
                taskObj.setDesc(finishBy);
                taskObj.setFinished(false);

                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .taskDao()
                        .insert(taskObj);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(AddTask.this, "Saved", Toast.LENGTH_SHORT).show();
            }
        }

    }


}






















