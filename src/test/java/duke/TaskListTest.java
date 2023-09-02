package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    @Test
    public void testAddTask() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        Launcher.TaskList taskList = new Launcher.TaskList(tasks);
        ToDo todo = new ToDo("Buy groceries");
        taskList.add(todo);

        assertEquals(1, taskList.num());
    }

//    @Test
//    public void testDeleteTask() {
//        ArrayList<Task> tasks = new ArrayList<Task>();
//        TaskList taskList = new TaskList(tasks);
//        ToDo todo = new ToDo("Buy groceries");
//        taskList.add(todo);
//
//        int initialSize = taskList.num();
//        taskList.delete("delete 1", 1, null);
//
//        assertEquals(initialSize - 1, taskList.num());
//    }

    @Test
    public void testListTasksEmpty() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        Launcher.TaskList taskList = new Launcher.TaskList(tasks);
        ArrayList<String> expectedMessages = new ArrayList<>();
        expectedMessages.add("List is empty");

        ArrayList<String> actualMessages = taskList.listTasks();

        assertEquals(expectedMessages, actualMessages);
    }

    @Test
    public void testListTasksNonEmpty() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        Launcher.TaskList taskList = new Launcher.TaskList(tasks);
        taskList.add(new ToDo("Buy groceries"));
        taskList.add(new Deadline("Read book", LocalDate.parse("2023-08-20"), null));

        ArrayList<String> expectedMessages = new ArrayList<>();
        expectedMessages.add("1. [T][ ] Buy groceries");
        expectedMessages.add("2. [D][ ] Read book (by: Aug 20 2023)");

        ArrayList<String> actualMessages = taskList.listTasks();

        assertEquals(expectedMessages, actualMessages);
    }

}
