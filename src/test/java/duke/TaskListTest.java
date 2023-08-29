package duke;

import exception.DukeException;
import org.junit.jupiter.api.Test;
import task.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {
    @Test
    public void getTasks_zeroBasedIndex_Test(){
        TaskList testObj = new TaskList();
        Task taskObj = new Task("buy book");
        testObj.addTask(taskObj);
        assertEquals(taskObj, testObj.getTasks(0));
    }

    @Test
    public void removeTask_returnRemovedTask_Test() throws DukeException {
        TaskList testObj = new TaskList();
        Task taskObj = new Task("buy book");
        testObj.addTask(taskObj);
        assertEquals(taskObj, testObj.removeTask(0));
    }

    @Test
    public void removeTask_returnDukeExceptionOutOfBoundIndex_Test() {
        TaskList testObj = new TaskList();
        Task taskObj = new Task("buy book");
        testObj.addTask(taskObj);

        DukeException e = assertThrows(DukeException.class, () -> testObj.removeTask(1));
        assertEquals("☹ OOPS!!! The number input does not exist.", e.getMessage());
    }

}
