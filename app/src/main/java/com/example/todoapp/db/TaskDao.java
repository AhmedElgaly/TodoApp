package com.example.todoapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface TaskDao {

    @Query("SELECT * FROM note")
    List<Note> getAll();

    @Insert
    void insert(Note task);

    @Delete
    void delete(Note task);

    @Update
    void update(Note task);
}
