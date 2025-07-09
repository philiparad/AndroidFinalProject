package com.example.finalproject.ui;

import android.os.Bundle;
import android.view.*;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.*;
import com.example.finalproject.R;
import com.example.finalproject.viewmodel.TaskViewModel;

public class TaskListFragment extends Fragment {
    private TaskViewModel taskViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.task_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        TaskAdapter adapter = new TaskAdapter();
        recyclerView.setAdapter(adapter);

        taskViewModel.getAllTasks().observe(getViewLifecycleOwner(), adapter::submitList);
        return view;
    }
}