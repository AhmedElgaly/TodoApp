package com.example.todoapp.presenters.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;
import com.example.todoapp.databinding.RecyclerviewTasksBinding;
import com.example.todoapp.db.Note;
import com.example.todoapp.generated.callback.OnClickListener;

import java.util.List;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ProductHolder>{


    private List<Note> noteList;
    private TaskListener noteListener;
   // private ProductImages productImages;

    public TasksAdapter(List<Note> noteList, TaskListener noteListener) {
        this.noteList = noteList;
        this.noteListener = noteListener;
       // productImages=new ProductImages();
    }
    @Override
    public ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerviewTasksBinding recyclerviewTasksBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.recyclerview_tasks, parent, false);
        return new ProductHolder(recyclerviewTasksBinding);

        //return new ProductHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.notes, parent, false));
    }
    @Override
    public void onBindViewHolder(ProductHolder holder, int position) {
      final  Note item = noteList.get(position);
        holder.recyclerviewTasksBinding.setTask(item);
        if (item.isFinished())
            holder.recyclerviewTasksBinding.textViewStatus.setText("Completed");
        else
            holder.recyclerviewTasksBinding.textViewStatus.setText("Not Completed");
        holder.recyclerviewTasksBinding.TaskView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noteListener.onNoteClicked(item);
            }
        });

    }
    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public void updateAdapter(List<Note> notesList) {
        noteList.clear();
        noteList.addAll(notesList);
        notifyDataSetChanged();
    }
    public class ProductHolder extends RecyclerView.ViewHolder {

        private RecyclerviewTasksBinding recyclerviewTasksBinding;

        public ProductHolder(@NonNull RecyclerviewTasksBinding recyclerviewTasksBinding) {
            super(recyclerviewTasksBinding.getRoot());
            //setIsRecyclable(false);
            this.recyclerviewTasksBinding = recyclerviewTasksBinding;

        }

    }

    public interface TaskListener{
        void onNoteClicked(Note note);
    }
}
