package duke.data;

import duke.data.exception.DukeException;
import duke.data.task.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {
    /** Tests add feature of TaskList */
    @Test
    public void test_add() {
        TaskList taskList = new TaskList();
        Todo newToDo = new Todo("go running");
        taskList.addTask(newToDo);
        assertEquals(taskList.countTasks(), 1);
        assertEquals(taskList.getTask(0), newToDo);
    }

    /** Tests delete feature of TaskList */
    @Test
    public void test_delete() throws DukeException {
        TaskList taskList = new TaskList();
        Todo newToDo = new Todo("go running");
        Todo newToDo2 = new Todo("go dancing");
        taskList.addTask(newToDo);
        taskList.addTask(newToDo2);
        assertEquals(taskList.countTasks(), 2);
        taskList.deleteTask(1);
        assertEquals(taskList.countTasks(), 1);
    }

    /** Tests marked feature of TaskList */
    @Test
    public void test_mark() throws DukeException {
        TaskList taskList = new TaskList();
        Todo newToDo = new Todo("go running");
        taskList.addTask(newToDo);
        assertEquals(taskList.countTasks(), 1);
        assertEquals(taskList.getTask(0).getDone(), false);
        taskList.markTask(newToDo);
        assertEquals(taskList.getTask(0).getDone(), true);
    }

    /** Tests unmarked feature of TaskList */
    @Test
    public void test_unmark() throws DukeException {
        TaskList taskList = new TaskList();
        Todo newToDo = new Todo("go running");
        taskList.addTask(newToDo);
        assertEquals(taskList.countTasks(), 1);
        assertEquals(taskList.getTask(0).getDone(), false);
        taskList.markTask(newToDo);
        assertEquals(taskList.getTask(0).getDone(), true);
        taskList.unmarkTask(newToDo);
        assertEquals(taskList.getTask(0).getDone(), false);
    }

    /** Tests exceptions thrown by TaskList */
    @Test
    public void test_exceptions() throws DukeException {
        TaskList taskList = new TaskList();
        Todo newToDo = new Todo("go running");
        taskList.addTask(newToDo);

        assertThrows(DukeException.class, () -> taskList.unmarkTask(newToDo));
        taskList.markTask(newToDo);
        assertThrows(DukeException.class, () -> taskList.markTask(newToDo));
        assertThrows(DukeException.class, () -> taskList.deleteTask(2));
    }
}
