package duke;
import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.Todo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void markTask_inputMoreThanSize_exceptionThrown() {
        assertEquals(true, true);
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
}
