package dialogix.main;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
