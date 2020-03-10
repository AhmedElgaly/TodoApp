package com.example.todoapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.todoapp.R;
import com.example.todoapp.databinding.ActivityUpdateTaskBinding;
import com.example.todoapp.db.Note;
import com.example.todoapp.presenters.UpdateTaskPreesnter;
import com.example.todoapp.view.ViewInterfaces.UpdateTaskActivityView;

public class UpdateTaskActivity extends AppCompatActivity implements UpdateTaskActivityView {

    ActivityUpdateTaskBinding binding;
    UpdateTaskPreesnter updateTaskPreesnter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_update_task);
        updateTaskPreesnter =new UpdateTaskPreesnter(this,getApplicationContext());
        final Note task = (Note) getIntent().getSerializableExtra("task");
        binding = DataBindingUtil.setContentView(UpdateTaskActivity.this,R.layout.activity_update_task);
    binding.setNote(task);
    binding.setUpdatePresenter(updateTaskPreesnter);
        LoadTask(task);

    }

    @Override
    public void ShowMainActivity() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        // intent.putExtra("Iscompany", response.body().getFOUserIsCompany().toString());
        startActivity(intent);
        finish();
    }

    @Override
    public void ShowError(String err) {
        Toast.makeText(getApplicationContext(),err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void LoadTask(Note note) {
        binding.checkBoxFinished.setChecked(note.isFinished());
        updateTaskPreesnter.TextTask.set(note.getTask());
        updateTaskPreesnter.TextDesc.set(note.getDesc());
        updateTaskPreesnter.TextFinishBy.set(note.getFinishBy());
    }
}
