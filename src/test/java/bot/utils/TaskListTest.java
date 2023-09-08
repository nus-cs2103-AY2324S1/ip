package bot.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import bot.utils.tasks.Task;
import org.junit.jupiter.api.Test;

import bot.exceptions.InvalidIndexException;
import bot.exceptions.InvalidTaskException;

public class TaskListTest {

    @Test
    public void remove_validIndex_success() throws InvalidTaskException, InvalidIndexException {
        ArrayList<Task> arr = new ArrayList<>();
        Task task = Task.makeTask("todo one");
        arr.add(task);
        TaskList tasks = new TaskList(arr);

        assertEquals(task, tasks.remove(1));
    }

    @Test
    public void remove_multipleIndex_success() throws InvalidTaskException, InvalidIndexException {
        ArrayList<Task> arr = new ArrayList<>();
        Task task1 = Task.makeTask("todo one");
        Task task2 = Task.makeTask("todo 2");
        arr.add(task1);
        arr.add(task2);
        TaskList tasks = new TaskList(arr);

        assertEquals(task2, tasks.remove(2));

        assertEquals(task1, tasks.remove(1));
    }

    @Test
    public void remove_negativeIndex_throwsException() throws InvalidTaskException {
        ArrayList<Task> arr = new ArrayList<>();
        Task task = Task.makeTask("todo one");
        arr.add(task);
        TaskList tasks = new TaskList(arr);
        try {
            tasks.remove(-1);
            fail();
        } catch (InvalidIndexException e) {
            assertEquals("Sorry, that index doesn't exist. Please key in a valid index.",
                    e.getMessage());
        }
    }

    @Test
    public void remove_bigNumber_throwsException() throws InvalidTaskException {
        ArrayList<Task> arr = new ArrayList<>();
        Task task = Task.makeTask("todo one");
        arr.add(task);
        TaskList tasks = new TaskList(arr);
        try {
            tasks.remove(100);
            fail();
        } catch (InvalidIndexException e) {
            assertEquals("Sorry, that index doesn't exist. Please key in a valid index.",
                    e.getMessage());
        }
    }
}
