package duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TasklistTest {
    public void taskListTest(){
        Tasklist tasks = new Tasklist(new ArrayList<>());
        Task task = new Todo("Cook", true);
        tasks.addTask(task);
        Task obtainedTask = tasks.getTask(0);
        assertTrue(obtainedTask.isDone());
        assertEquals("Cook", obtainedTask.getName());
    }
}
