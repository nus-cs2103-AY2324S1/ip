package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Test class for the TaskList class.
 * Contains test cases for the markTask method.
 */
//CHECKSTYLE.OFF: MethodName
public class TaskListTest {
    /**
     * Test marking a task with an input index greater than the list size.
     * Verifies that an exception is thrown.
     */
    @Test
    public void markTask_inputMoreThanSize_exceptionThrown() {
        List<Task> temp = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            temp.add(new Todo("foo", false));
        }
        TaskList test = new TaskList(temp);
        try {
            test.markTask("mark 6");
        } catch (DukeException e) {
            assertEquals("You have provided a number out of index of the stored tasks", e.getMessage());
        }
    }

    /**
     * Test marking a task with a negative input index.
     * Verifies that an exception is thrown.
     */
    @Test
    public void markTask_inputLessThanZero_exceptionThrown() {
        List<Task> temp = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            temp.add(new Todo("foo", false));
        }
        TaskList test = new TaskList(temp);
        try {
            test.markTask("mark -1");
        } catch (DukeException e) {
            assertEquals("You have provided a number out of index of the stored tasks", e.getMessage());
        }
    }

    /**
     * Test marking a task with a non-integer input index.
     * Verifies that the task's marked status is not changed.
     */
    @Test
    public void markTask_inputNonInteger_success() {
        List<Task> temp = new ArrayList<>();
        Task todo = new Todo("foo", true);
        temp.add(todo);
        TaskList test = new TaskList(temp);
        try {
            test.markTask("mark test");
            assertEquals(true, todo.getIsMarked());
        } catch (DukeException e) {
            assertEquals("You have provided a number out of index of the stored tasks", e.getMessage());
        }
    }

    /**
     * Test marking a task with an input index within the valid range.
     * Verifies that the task's marked status is changed to true.
     */
    @Test
    public void markTask_inputWithinRange_success() {
        List<Task> temp = new ArrayList<>();
        Task todo = new Todo("foo", false);
        temp.add(todo);
        TaskList test = new TaskList(temp);
        try {
            test.markTask("mark 0");
            assertEquals(true, todo.getIsMarked());
        } catch (DukeException e) {
            assertEquals("You have provided a number out of index of the stored tasks", e.getMessage());
        }
    }

    /**
     * Test marking an already marked task.
     * Verifies that the task's marked status remains true.
     */
    @Test
    public void markTask_markAlreadyMarkedTask_success() {
        List<Task> temp = new ArrayList<>();
        Task todo = new Todo("foo", true);
        temp.add(todo);
        TaskList test = new TaskList(temp);
        try {
            test.markTask("mark 0");
            assertEquals(true, todo.getIsMarked());
        } catch (DukeException e) {
            assertEquals("You have provided a number out of index of the stored tasks", e.getMessage());
        }
    }
    //CHECKSTYLE.ON: MethodName
}
