package com.example.todoapp.presenters;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;

import androidx.databinding.ObservableField;

import com.example.todoapp.db.DatabaseClient;
import com.example.todoapp.db.Note;
import com.example.todoapp.view.ViewInterfaces.AddTaskActivityView;
import com.example.todoapp.view.ViewInterfaces.MainActivityView;

public class AddTaskPresenter {
    private static final String TAG = "Login Error";
    private AddTaskActivityView mView;
    public ObservableField<String> TextTask;
    public ObservableField<String> TextDesc;
    public ObservableField<String> TextFinishBy;

    private Context ctx;

    public AddTaskPresenter(AddTaskActivityView mView, Context ctx) {
        this.mView = mView;
        this.ctx=ctx;
        initFields();
    }
    private void initFields() {
        TextTask = new ObservableField<>();
        TextDesc = new ObservableField<>();
        TextFinishBy = new ObservableField<>();
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

    public void doLogin() {
        if (isValidate(TextTask.get(),TextDesc.get(),TextFinishBy.get())) {
            class SaveTask extends AsyncTask<Void, Void, Void> {

                @Override
                protected Void doInBackground(Void... voids) {

                    //creating a task
                    Note task = new Note();
                    task.setTask(TextTask.get());
                    task.setDesc(TextDesc.get());
                    task.setFinishBy(TextFinishBy.get());
                    task.setFinished(false);

                    //adding to database
                    DatabaseClient.getInstance(ctx).getAppDatabase()
                            .taskDao()
                            .insert(task);
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
//                    finish();
//                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                    Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
                    mView.ShowMainActivity();
                    mView.ShowError("Saved");
                }
            }

            SaveTask st = new SaveTask();
            st.execute();
        }
    }

}
