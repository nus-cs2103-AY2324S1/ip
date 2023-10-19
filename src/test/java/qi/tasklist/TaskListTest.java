package qi.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import qi.qiexception.QiException;
import qi.task.Task;
import qi.task.Todo;


public class TaskListTest {
    @Test
    public void testShowTask() {
        TaskList list = new TaskList();
        list.addTask(new Todo("eat"));
        assertEquals("[T][ ] eat", list.showTask(1));
    }

    @Test
    public void testDeleteValidTask() {
        TaskList list = new TaskList();
        list.addTask(new Todo("eat"));
        Task code = new Todo("code");
        list.addTask(code);
        try {
            assertEquals(code, list.deleteTask(2));
        } catch (QiException e) {
            assertEquals(e.getMessage(), "Please specify a valid task ID!");
        }
    }

    @Test
    public void deleteInvalidTask_exceptionThrown() {
        TaskList list = new TaskList();
        list.addTask(new Todo("eat"));
        try {
            list.deleteTask(2);
        } catch (QiException e) {
            assertEquals(e.getMessage(), "Please specify a valid task ID!");
        }
    }

    @Test
    public void markInvalidTask_exceptionThrown() {
        TaskList list = new TaskList();
        list.addTask(new Todo("eat"));
        try {
            list.mark(2, true);
        } catch (QiException e) {
            assertEquals(e.getMessage(), "Please specify a valid task ID!");
        }
    }
}

