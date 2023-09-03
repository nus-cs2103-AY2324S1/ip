package skye.data;

import skye.data.exception.DukeException;
import skye.data.exception.DukeExceptionType;
import skye.data.task.Task;
import skye.data.task.ToDo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    TaskList taskList;
    @BeforeEach
    public void init() {
        taskList = new TaskList();
        taskList.addTask(new ToDo("Write Report"));
        taskList.addTask(new ToDo("Run 3km"));
    }

    @Test
    public void markTask_numberOutOfRange_exceptionThrown() {
        Exception exception = assertThrows(DukeException.class, () -> taskList.markTask(3));
        assertEquals(DukeExceptionType.INVALID_RANGE.getMessage(), exception.getMessage());
    }

    @Test
    public void markTask_numberWithinRange_success() {
        try {
            Task task = taskList.markTask(1);
            assertTrue(task.isDone());
        } catch (DukeException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void markTask_taskAlreadyMarked_success() {
        Exception exception = assertThrows(DukeException.class, () -> {
            taskList.markTask(1);
            taskList.markTask(1);
        });
        assertEquals(DukeExceptionType.TASK_ALREADY_MARKED.getMessage(), exception.getMessage());
    }

    @Test
    public void unmarkTask_numberOutOfRange_exceptionThrown() {
        Exception exception = assertThrows(DukeException.class, () -> taskList.unmarkTask(0));
        assertEquals(DukeExceptionType.INVALID_RANGE.getMessage(), exception.getMessage());
    }

    @Test
    public void unmarkTask_numberWithinRange_success() {
        try {
            taskList.markTask(2);
            Task task = taskList.unmarkTask(2);
            assertFalse(task.isDone());
        } catch (DukeException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void unmarkTask_taskAlreadyUnmarked_success() {
        Exception exception = assertThrows(DukeException.class, () -> {
            taskList.markTask(2);
            taskList.unmarkTask(2);
            taskList.unmarkTask(2);
        });
        assertEquals(DukeExceptionType.TASK_ALREADY_UNMARKED.getMessage(), exception.getMessage());
    }
}
