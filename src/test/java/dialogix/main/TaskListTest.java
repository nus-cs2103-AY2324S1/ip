package dialogix.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;

import dialogix.exception.DialogixException;
import dialogix.task.Deadline;
import dialogix.task.Event;
import dialogix.task.Task;
import dialogix.task.Todo;

class TaskListTest {
    @Test
    void defaultTaskConstructorTest() throws DialogixException {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.size(), "Initial size of list should be 0");

        Todo test = new Todo("todo");
        taskList.add(test);
        assertEquals(test, taskList.get(0), "Test insert todo into list");

        ArrayList<Task> filteredTasks = taskList.find("todo");
        assertEquals(1, filteredTasks.size(), "List of filtered tasks should be 1");

        filteredTasks = taskList.find("hello");
        assertEquals(0, filteredTasks.size(), "List of filtered tasks should be 0");

        taskList.delete(0);
        assertEquals(0, taskList.size(), "Size of list after deletion should be 0");
    }

    @Test
    void overloadedTaskConstructorTest() throws DialogixException {
        ArrayList<Task> tasks = new ArrayList<>();

        Todo todo = new Todo("test");
        Deadline deadline = new Deadline("deadline", new Date(0));

        tasks.add(todo);
        tasks.add(deadline);

        TaskList taskList = new TaskList(tasks);
        assertEquals(2, taskList.size(), "Initial size of list with provided values should be 2");

        Event event = new Event("event", "eventTime");
        taskList.add(event);
        assertEquals(3, taskList.size(), "Size of list after addition should be 3");
        assertEquals(todo, taskList.get(0), "Test get todo from list");
        assertEquals(deadline, taskList.get(1), "Test get deadline from list");
        assertEquals(event, taskList.get(2), "Test get event from list");

        taskList.delete(0);
        assertEquals(2, taskList.size(), "Size of list after deletion should be 2");
        assertEquals(deadline, taskList.get(0), "Test get deadline from list");
        assertEquals(event, taskList.get(1), "Test get event from list");
    }

    @Test
    void invalidDeleteTest() throws DialogixException {
        TaskList taskList = new TaskList();

        // Attempting to delete an item from an empty list should throw an exception
        assertThrows(DialogixException.class, () -> taskList.delete(0),
                "Deleting from an empty list should throw an exception");

        Todo todo = new Todo("todo");
        taskList.add(todo);

        // Attempting to delete an item with an out-of-range index should throw an exception
        assertThrows(DialogixException.class, () -> taskList.delete(1),
                "Deleting with an out-of-range index should throw an exception");

        // Deleting an item should reduce the size of the list
        taskList.delete(0);
        assertEquals(0, taskList.size(), "Size of list after valid deletion should be 0");
    }

    @Test
    void invalidGetTest() {
        TaskList taskList = new TaskList();

        // Attempting to get an item from an empty list should throw an exception
        assertThrows(DialogixException.class, () -> taskList.get(0),
                "Getting from an empty list should throw an exception");

        Todo todo = new Todo("todo");
        taskList.add(todo);

        // Attempting to get an item with an out-of-range index should throw an exception
        assertThrows(DialogixException.class, () -> taskList.get(1),
                "Getting with an out-of-range index should throw an exception");

        // Getting an item should return the correct item
        assertEquals(todo, taskList.get(0), "Getting a valid item from the list should return the item");
    }

    @Test
    void invalidAddNullTaskTest() {
        TaskList taskList = new TaskList();

        // Attempting to add a null task should throw an exception
        assertThrows(DialogixException.class, () -> taskList.add(null), "Adding a null task should throw an exception");

        Todo todo = new Todo("todo");
        taskList.add(todo);

        // Adding a valid task should increase the size of the list
        assertEquals(1, taskList.size(), "Size of list after valid addition should be 1");
    }
}
