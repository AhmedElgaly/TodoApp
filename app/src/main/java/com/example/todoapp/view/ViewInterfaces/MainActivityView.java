package com.example.todoapp.view.ViewInterfaces;

import com.example.todoapp.db.Note;

import java.util.List;

public interface MainActivityView {
    void ShowAddNote();
    void ShowError(String err);
    void ShowUpdateActivty(Note note);
    void setAdapter(List<Note> notes);
}
