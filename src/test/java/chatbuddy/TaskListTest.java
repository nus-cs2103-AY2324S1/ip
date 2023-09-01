package chatbuddy;

import chatbuddy.task.ToDo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void getTaskStringsToSave() {
        // set up task list
        TaskList tasks = new TaskList();
        ToDo taskOne = new ToDo("task1");
        ToDo taskTwo = new ToDo("task2");
        tasks.addTask(taskOne);
        tasks.addTask(taskTwo);
        taskTwo.markAsDone();

        ArrayList<String> taskStrings = tasks.getTaskStringsToSave();
        assertEquals(2, taskStrings.size());
        assertEquals("T | 0 | task1", taskStrings.get(0));
        assertEquals("T | 1 | task2", taskStrings.get(1));
    }
}
