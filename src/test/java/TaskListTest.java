import duke.TaskList;
import exceptions.EmptyDateException;
import exceptions.EmptyTaskException;
import exceptions.OutOfRangeException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    @Test
    public void firstTaskIndex_markTask() throws EmptyTaskException, OutOfRangeException, IOException {
        // Create a TaskList instance and add some tasks to it
        TaskList taskList = new TaskList();
        taskList.makeToDo("Task 1");
        taskList.makeToDo("Task 2");
        taskList.makeToDo("Task 3");
        taskList.makeToDo("Task 4");
        // Mark the first task as done
        taskList.markTask("mark 1");

        assertTrue(taskList.getTask(0).isDone());
        assertFalse(taskList.getTask(1).isDone());
        assertFalse(taskList.getTask(2).isDone());
        assertFalse(taskList.getTask(3).isDone());
    }

    @Test
    public void lastTaskIndex_markTask() throws EmptyTaskException, OutOfRangeException, IOException {
        // Create a TaskList instance and add some tasks to it
        TaskList taskList = new TaskList();
        taskList.makeToDo("Task 1");
        taskList.makeToDo("Task 2");
        taskList.makeToDo("Task 3");
        taskList.makeToDo("Task 4");
        // Mark the first task as done
        taskList.markTask("mark 4");

        assertFalse(taskList.getTask(0).isDone());
        assertFalse(taskList.getTask(1).isDone());
        assertFalse(taskList.getTask(2).isDone());
        assertTrue(taskList.getTask(3).isDone());
    }

    @Test
    public void middleTaskIndex_markTask() throws EmptyTaskException, OutOfRangeException, IOException {
        // Create a TaskList instance and add some tasks to it
        TaskList taskList = new TaskList();
        taskList.makeToDo("Task 1");
        taskList.makeToDo("Task 2");
        taskList.makeToDo("Task 3");
        taskList.makeToDo("Task 4");
        // Mark the first task as done
        taskList.markTask("mark 2");

        assertFalse(taskList.getTask(0).isDone());
        assertTrue(taskList.getTask(1).isDone());
        assertFalse(taskList.getTask(2).isDone());
        assertFalse(taskList.getTask(3).isDone());
    }

    @Test
    public void tooLargeTaskIndex_markTask() {
        // Create a TaskList instance and add some tasks to it
        TaskList taskList = new TaskList();
        taskList.makeToDo("Task 1");
        taskList.makeToDo("Task 2");
        taskList.makeToDo("Task 3");
        // Mark the second task as done
        assertThrows(OutOfRangeException.class, () -> taskList.markTask("mark 4"));
        assertThrows(OutOfRangeException.class, () -> taskList.markTask("mark 100"));
    }

    @Test
    public void tooSmallTaskIndex_markTask() {
        // Create a TaskList instance and add some tasks to it
        TaskList taskList = new TaskList();
        taskList.makeToDo("Task 1");
        taskList.makeToDo("Task 2");
        taskList.makeToDo("Task 3");
        // Mark the second task as done
        assertThrows(OutOfRangeException.class, () -> taskList.markTask("mark 0"));
        assertThrows(OutOfRangeException.class, () -> taskList.markTask("mark -57"));
    }
}
