package glen.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import glen.task.Todo;

public class TaskListTest {

    @Test
    void addTodo_shouldAddNewTodoToList() {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("test/tasks.txt");

        String result = taskList.addTodo(storage, "Test Todo");
        assertEquals("_____________________________________________________\n" +
                "Got it. I've added this task:\n" +
                "  [T][ ] Test Todo\n" +
                "Now you have 1 tasks in the list.\n" +
                "_____________________________________________________\n", result);

        // Validate the list has the new Todo
        assertEquals(1, taskList.size());
        assertTrue(taskList.lst().contains("Test Todo"));

        // reset test/tasks.txt to an empty file.
        storage.updateTask(0, null);
    }

    @Test
    void toggle_shouldToggleTaskStatus() {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("test/tasks.txt");
        taskList.add(new Todo("Test Todo", false));

        String result = taskList.toggle(storage, "mark", 0);
        assertEquals("_____________________________________________________\n" +
                "Nice! I've marked this task as done:\n" +
                "  [X] Test Todo\n" +
                "_____________________________________________________\n", result);

        // Validate the status is toggled
        assertTrue(taskList.lst().contains("[X] Test Todo"));

        // reset test/tasks.txt to an empty file.
        storage.updateTask(0, null);
    }

}
