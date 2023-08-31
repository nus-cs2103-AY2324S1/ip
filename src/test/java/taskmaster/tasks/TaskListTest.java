package taskmaster.tasks;

import org.junit.jupiter.api.Test;
import taskmaster.exceptions.DukeException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void toggleMark_invalidInput_throwsException() throws DukeException {
        ArrayList<Task> testList = new ArrayList<Task>();
        TaskList taskList = new TaskList();
        taskList.addTask(TaskList.TaskType.TODO, "read book", "unmarked");
        assertThrows(DukeException.class, () -> taskList.toggleMark(TaskList.MarkStatus.MARK, 3));
        assertDoesNotThrow(() -> taskList.toggleMark(TaskList.MarkStatus.MARK, 0));
    }

    @Test
    public void toggleMark_validInput_correctString() throws DukeException {
        ArrayList<Task> testList = new ArrayList<Task>();
        TaskList taskList = new TaskList();
        taskList.addTask(TaskList.TaskType.TODO, "read book", "unmarked");
        assertDoesNotThrow(() -> taskList.toggleMark(TaskList.MarkStatus.MARK, 0));
        String expected = "[T][X] read book";
        String task = TaskList.list.get(0).toString();
        assertEquals(expected ,task);
    }

    @Test
    public void deleteTask_invalidInput_throwsException() throws DukeException {
        ArrayList<Task> testList = new ArrayList<Task>();
        TaskList taskList = new TaskList();
        taskList.addTask(TaskList.TaskType.TODO, "read book", "unmarked");
        assertThrows(DukeException.class, () -> taskList.deleteTask(3));
        assertDoesNotThrow(() -> taskList.deleteTask(0));
    }

    @Test
    public void deleteTask_validInput_throwsNullPointerException() throws DukeException {
        ArrayList<Task> testList = new ArrayList<Task>();
        TaskList taskList = new TaskList();
        taskList.addTask(TaskList.TaskType.TODO, "read book", "unmarked");
        assertDoesNotThrow(() -> taskList.deleteTask(0));
        assertThrows(IndexOutOfBoundsException.class, () -> TaskList.list.get(0));
    }
}
