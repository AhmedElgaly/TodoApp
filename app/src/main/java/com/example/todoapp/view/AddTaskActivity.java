package com.example.todoapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.todoapp.R;
import com.example.todoapp.databinding.ActivityAddTaskBinding;
import com.example.todoapp.presenters.AddTaskPresenter;
import com.example.todoapp.presenters.MainPresenter;
import com.example.todoapp.view.ViewInterfaces.AddTaskActivityView;

public class AddTaskActivity extends AppCompatActivity implements AddTaskActivityView {

    AddTaskPresenter addTaskPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_add_task);

        addTaskPresenter =new AddTaskPresenter(AddTaskActivity.this,getApplicationContext());
        ActivityAddTaskBinding binding = DataBindingUtil.setContentView(AddTaskActivity.this,R.layout.activity_add_task);
    binding.setAddTaskPresentr(addTaskPresenter);
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
}
