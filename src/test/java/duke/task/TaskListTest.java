package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class TaskListTest {
    private TaskList sampleTaskList;

    @BeforeEach
    public void setSampleTaskList() {
        sampleTaskList = new TaskList();
        List<Task> tasks = new ArrayList<>();
        tasks.add(new TaskSample());
        tasks.add(new TaskSample());
        tasks.add(new TaskSample());
        sampleTaskList.readTasksFromStorage(tasks);
    }

    @Test
    public void getSize_threeTasks_success() {
        assertEquals(sampleTaskList.getSize(), 3);
    }

    @Test
    public void addTask_success() {
        sampleTaskList.addTask(new TaskSample());
        assertEquals(sampleTaskList.getSize(), 4);
    }

    @Test
    public void deleteTask_success() {
        sampleTaskList.deleteTask(2);
        assertEquals(sampleTaskList.getSize(), 2);
    }

    @Test
    public void markTask_correctStatusIcon() {
        sampleTaskList.markTask(2);
        assertEquals(sampleTaskList.getTask(2).getStatusIcon(), "[X]");
    }
}
