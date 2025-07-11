package com.example.finalproject.ui;

import androidx.lifecycle.ViewModel;
import android.os.Bundle;
import android.view.*;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.finalproject.R;
import com.example.finalproject.viewmodel.TaskViewModel;

public class StatsFragment extends Fragment {
    private TaskViewModel taskViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats, container, false);
        TextView statsText = view.findViewById(R.id.stats_text);

        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        taskViewModel.getAllTasks().observe(getViewLifecycleOwner(), tasks -> {
            int total = tasks.size();
            long completed = tasks.stream().filter(t -> t.isCompleted).count();
            statsText.setText("סה\"כ משימות: " + total + " | הושלמו: " + completed);
        });

        return view;
    }
}
