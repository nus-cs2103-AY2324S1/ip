package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;


public class TaskListTest {

    @Test
    public void test_storeTaskToList() {
        TaskList list = new TaskList(new ArrayList<>());
        list.store(new ToDo("testing", false));
        assertEquals(1, list.length());
    }

    @Test
    public void test_deleteTaskFromList() {
        List<Task> list = new ArrayList<>();
        list.add(new ToDo("testing", false));
        TaskList taskList = new TaskList(list);
        taskList.delete(0);
        assertEquals(0, taskList.length());
    }

    @Test
    public void test_findTask() {
        List<Task> list = new ArrayList<>();
        list.add(new ToDo("testing", false));
        list.add(new ToDo("done", false));
        TaskList taskList = new TaskList(list);
        assertEquals("Here are the matching tasks in your list:\n1. [T][ ] done\n",
                taskList.find("done"));
    }
}
