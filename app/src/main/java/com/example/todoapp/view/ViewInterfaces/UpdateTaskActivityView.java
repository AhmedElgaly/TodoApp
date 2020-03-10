package com.example.todoapp.view.ViewInterfaces;

import com.example.todoapp.db.Note;

public interface UpdateTaskActivityView {
    void ShowMainActivity();
    void ShowError(String err);
    void LoadTask(Note note);
}
