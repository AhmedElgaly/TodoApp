package com.example.todoapp.presenters;

import android.content.Context;
import android.os.AsyncTask;

import com.example.todoapp.db.DatabaseClient;
import com.example.todoapp.db.Note;
import com.example.todoapp.presenters.Adapters.TasksAdapter;
import com.example.todoapp.view.ViewInterfaces.AddTaskActivityView;
import com.example.todoapp.view.ViewInterfaces.MainActivityView;

import java.util.List;

public class MainPresenter implements TasksAdapter.TaskListener {
    private static final String TAG = "Login Error";
    private MainActivityView mView;
    private Context ctx;

    public MainPresenter(MainActivityView mView, Context ctx) {
        this.mView = mView;
        this.ctx = ctx;

    }

    public void getTasks() {
        class GetTasks extends AsyncTask<Void, Void, List<Note>> {

            @Override
            protected List<Note> doInBackground(Void... voids) {
                List<Note> taskList = DatabaseClient
                        .getInstance(ctx)
                        .getAppDatabase()
                        .taskDao()
                        .getAll();
                return taskList;
            }

            @Override
            protected void onPostExecute(List<Note> tasks) {
                super.onPostExecute(tasks);
              mView.setAdapter(tasks);
                //mView.ShowError("");
            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();
    }

    public void GoToAddNote(){
        mView.ShowAddNote();
    }

    @Override
    public void onNoteClicked(Note note) {
        mView.ShowUpdateActivty(note);
    }
}
