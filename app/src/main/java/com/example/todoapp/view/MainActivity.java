package com.example.todoapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.todoapp.R;
import com.example.todoapp.databinding.ActivityMainBinding;
import com.example.todoapp.db.Note;
import com.example.todoapp.presenters.Adapters.TasksAdapter;
import com.example.todoapp.presenters.MainPresenter;
import com.example.todoapp.view.ViewInterfaces.MainActivityView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainActivityView {

    MainPresenter mainPresenter;
    TasksAdapter mAdapter;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        mainPresenter=new MainPresenter(MainActivity.this,getApplicationContext());
        binding = DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);
    binding.setMainPresenter(mainPresenter);
    binding.getMainPresenter().getTasks();
        initRecyclerView();
    }
    private void initRecyclerView() {
        binding.notesRecycle.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new TasksAdapter(new ArrayList<Note>(), mainPresenter);
        binding.notesRecycle.setAdapter(mAdapter);
    }
    @Override
    public void ShowAddNote() {
        Intent intent = new Intent(getApplicationContext(), AddTaskActivity.class);
        // intent.putExtra("Iscompany", response.body().getFOUserIsCompany().toString());
        startActivity(intent);
        finish();
    }

    @Override
    public void ShowError(String err) {
        Toast.makeText(getApplicationContext(),err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ShowUpdateActivty(Note note) {
        Intent intent = new Intent(getApplicationContext(), UpdateTaskActivity.class);
        intent.putExtra("task", note);
        startActivity(intent);
    }

    @Override
    public void setAdapter(List<Note> notes) {
        mAdapter.updateAdapter(notes);
    }
}
