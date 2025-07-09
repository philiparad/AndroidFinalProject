package com.example.finalproject.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.finalproject.data.Task;
import com.example.finalproject.data.TaskDatabase;
import com.example.finalproject.data.TaskDao;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    private final TaskDao taskDao;
    private final LiveData<List<Task>> allTasks;

    public TaskViewModel(Application application) {
        super(application);
        TaskDatabase db = TaskDatabase.getDatabase(application);
        taskDao = db.taskDao();
        allTasks = taskDao.getAllTasks();
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }
}