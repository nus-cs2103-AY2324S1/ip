package chatbuddy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import chatbuddy.task.ToDo;

public class TaskListTest {
    @Test
    public void getTaskStringsToSave() {
        // set up task list
        TaskList tasks = new TaskList();
        ToDo taskOne = new ToDo("task1");
        ToDo taskTwo = new ToDo("task2");
        tasks.addTask(taskOne);
        taskOne.updateTag("urgent");
        tasks.addTask(taskTwo);
        taskTwo.markAsDone();

        ArrayList<String> taskStrings = tasks.getTaskStringsToSave();
        assertEquals(2, taskStrings.size());
        assertEquals("T | 0 | task1 | urgent", taskStrings.get(0));
        assertEquals("T | 1 | task2 | ", taskStrings.get(1));
    }
}
