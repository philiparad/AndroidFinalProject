import static org.junit.Assert.*;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.example.finalproject.data.Task;
import com.example.finalproject.data.TaskDao;
import com.example.finalproject.data.TaskDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class TaskDaoTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private TaskDao taskDao;
    private TaskDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, TaskDatabase.class)
                .allowMainThreadQueries()
                .build();
        taskDao = db.taskDao();
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void insertAndGetAllTasks() throws Exception {
        Task task1 = new Task();
        task1.title = "Task1";
        task1.description = "Desc1";
        task1.dueDate = "2025-01-01";
        task1.isCompleted = false;
        taskDao.insert(task1);

        Task task2 = new Task();
        task2.title = "Task2";
        task2.description = "Desc2";
        task2.dueDate = "2025-02-02";
        task2.isCompleted = true;
        taskDao.insert(task2);

        List<Task> tasks = getValue(taskDao.getAllTasks());
        assertEquals(2, tasks.size());
        assertEquals("Task1", tasks.get(0).title);
        assertEquals("Task2", tasks.get(1).title);
    }

    private static <T> T getValue(LiveData<T> liveData) throws InterruptedException {
        final Object[] data = new Object[1];
        CountDownLatch latch = new CountDownLatch(1);
        Observer<T> observer = new Observer<T>() {
            @Override
            public void onChanged(T t) {
                data[0] = t;
                latch.countDown();
                liveData.removeObserver(this);
            }
        };
        liveData.observeForever(observer);
        latch.await(2, TimeUnit.SECONDS);
        //noinspection unchecked
        return (T) data[0];
    }
}