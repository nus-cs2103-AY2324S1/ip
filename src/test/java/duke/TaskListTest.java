package duke;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
public class TaskListTest {
    private ArrayList<Task> tasklist = new ArrayList<>();
    private TaskList tasks = new TaskList(tasklist);
    @Test
    public void addTask_todo_success() throws DukeException {
        tasks.addTask("T", "todo borrow book");

        // Perform assertions or test other functionality here
        assertEquals(1, tasks.getSize()); // Example assertion
    }
    @Test
    public void addTask_todo_exceptionThrown() throws DukeException {
        try {
            tasks.addTask("T", "todo");
        } catch (DukeException e) {
            assertEquals("OOPS!!! The description of a todo cannot be empty.", e.getMessage());
        }
    }
    @Test
    public void addTask_deadline_success() throws DukeException {
        tasks.addTask("D", "deadline return book /by 2/12/2019 1800");

        // Perform assertions or test other functionality here
        assertEquals(1, tasks.getSize()); // Example assertion
    }
    @Test
    public void addTask_deadline_exceptionThrown() {
        try {
            tasks.addTask("D", "deadline return book /by 2/12/2019");
        } catch (DukeException e) {
            assertEquals("put in a time pls", e.getMessage());
        }
    }
    @Test
    public void addTask_event_success() throws DukeException {
        tasks.addTask("E", "event project meeting /from Mon 2pm /to 4pm");

        // Perform assertions or test other functionality here
        assertEquals(1, tasks.getSize()); // Example assertion
    }
    @Test
    public void addTask_event_exceptionThrown() {
        try {
            tasks.addTask("E", "event project meeting /rom Mon 2pm /to 4pm");
        } catch (DukeException e) {
            assertEquals("You need to add a /from and /to for events", e.getMessage());
        }
    }
    @Test
    public void deleteTask_delete1_success() throws DukeException {
        tasks.addTask("T", "todo borrow book");
        tasks.deleteTask("delete 1");
        assertEquals(0, tasks.getSize());
    }
    @Test
    public void deleteTask_delete2_exceptionThrown() {
        try {
            tasks.addTask("T", "todo borrow book");
            tasks.deleteTask("delete 2");
        } catch (DukeException e) {
            assertEquals("You are trying to delete a Task that does not exist", e.getMessage());
        }
    }
    @Test
    public void markDescription_mark1_success() throws DukeException {
        tasks.addTask("T", "todo borrow book");
        tasks.markTask("mark 1");
        assertEquals("X", tasks.getTaskByIndex(0).getStatusIcon());
    }
    @Test
    public void markDescription_mark2_exceptionThrown() {
        try {
            tasks.addTask("T", "todo borrow book");
            tasks.markTask("mark 2");
        } catch (DukeException e) {
            assertEquals("You are trying to access a Task that does not exist!", e.getMessage());
        }
    }
    @Test
    public void displayList_todo_success() throws DukeException {
        tasks.addTask("T", "todo borrow book");

        // Perform assertions or test other functionality here
        assertEquals("Here are the tasks in your list:\n1. [T][ ] borrow book\n", tasks.displayList());
    }
    @Test
    public void testUndo() throws DukeException, IOException, ClassNotFoundException {
        Duke duke = new Duke();
        tasks.addTask("T", "todo borrow book");
        tasks.undo(duke.getStorage());
        // Perform assertions or test other functionality here
        assertEquals("Here are the tasks in your list:\n", tasks.displayList());
    }
}
