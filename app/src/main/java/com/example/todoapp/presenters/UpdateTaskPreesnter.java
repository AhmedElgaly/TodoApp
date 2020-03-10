package com.example.todoapp.presenters;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;

import androidx.databinding.ObservableField;

import com.example.todoapp.db.DatabaseClient;
import com.example.todoapp.db.Note;
import com.example.todoapp.view.ViewInterfaces.AddTaskActivityView;
import com.example.todoapp.view.ViewInterfaces.UpdateTaskActivityView;

public class UpdateTaskPreesnter {
    private static final String TAG = "Login Error";
    private UpdateTaskActivityView mView;
    public ObservableField<String> TextTask;
    public ObservableField<String> TextDesc;
    public ObservableField<String> TextFinishBy;

    public  ObservableField<Boolean> finished;
    private Context ctx;

    public UpdateTaskPreesnter(UpdateTaskActivityView mView, Context ctx) {
        this.mView = mView;
        this.ctx = ctx;
        initFields();
    }
    private void initFields() {
        TextTask = new ObservableField<>();
        TextDesc = new ObservableField<>();
        TextFinishBy = new ObservableField<>();
        finished=new ObservableField<>();
       // finished.set(false);
    }
    private boolean isValidate(String TextTask,String TextDesc,String TextFinishBy ) {
        if (TextUtils.isEmpty(TextTask)) {
            mView.ShowError("Please Enter Your TextTask");
            return false;
        }
        if (TextUtils.isEmpty(TextDesc)) {
            mView.ShowError("Please Enter Your TextDesc");
            return false;
        }

        if (TextUtils.isEmpty(TextFinishBy)) {
            mView.ShowError("Please Enter Your TextFinishBy");
            return false;
        }
        return true;
    }

    public void updateTask(final Note task) {

       // final Note task= new Note();

        if (isValidate(TextTask.get(),TextDesc.get(),TextFinishBy.get())){
            class UpdateTask extends AsyncTask<Void, Void, Void> {

                @Override
                protected Void doInBackground(Void... voids) {
                    task.setTask(TextTask.get());
                    task.setDesc(TextDesc.get());
                    task.setFinishBy(TextFinishBy.get());
                    task.setFinished(finished.get());
                    DatabaseClient.getInstance(ctx).getAppDatabase()
                            .taskDao()
                            .update(task);
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    mView.ShowMainActivity();
                    mView.ShowError("Updated");
                }
            }

            UpdateTask ut = new UpdateTask();
            ut.execute();
        }


    }
    public void deleteTask(final Note task) {
        class DeleteTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(ctx).getAppDatabase()
                        .taskDao()
                        .delete(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                mView.ShowMainActivity();
                mView.ShowError("Deleted");
            }
        }

        DeleteTask dt = new DeleteTask();
        dt.execute();

    }

}
