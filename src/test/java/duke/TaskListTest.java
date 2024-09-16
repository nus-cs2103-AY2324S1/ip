package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import exception.DukeException;
import task.Task;

public class TaskListTest {
    @Test
    public void getTasks_zeroBasedIndex_test() {
        TaskList testObj = new TaskList();
        Task taskObj = new Task("buy book");
        testObj.addTask(taskObj);
        assertEquals(taskObj, testObj.getTasks(0));
    }

    @Test
    public void removeTask_returnRemovedTask_test() throws DukeException {
        TaskList testObj = new TaskList();
        Task taskObj = new Task("buy book");
        testObj.addTask(taskObj);
        assertEquals(taskObj, testObj.removeTask(0));
    }

    @Test
    public void removeTask_returnDukeExceptionOutOfBoundIndex_test() {
        TaskList testObj = new TaskList();
        Task taskObj = new Task("buy book");
        testObj.addTask(taskObj);

        DukeException e = assertThrows(DukeException.class, () -> testObj.removeTask(1));
        assertEquals("☹ OOPS!!! The number input does not exist.", e.getMessage());
    }

}
