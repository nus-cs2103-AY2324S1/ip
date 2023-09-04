package duke;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
public class TaskListTest {
    private ArrayList<Task> tasklist = new ArrayList<>();
    private TaskList tasks = new TaskList(tasklist);
    @Test
    public void addTask_todo_success() throws DukeException {
        tasks.addTask("T", "borrow book");

        // Perform assertions or test other functionality here
        assertEquals(1, tasks.getSize()); // Example assertion
    }
    @Test
    public void addTask_deadline_success() throws DukeException {
        tasks.addTask("D", "return book /by 2/12/2019 1800");

        // Perform assertions or test other functionality here
        assertEquals(1, tasks.getSize()); // Example assertion
    }
    @Test
    public void addTask_deadline_exceptionThrown() {
        try {
            tasks.addTask("D", "return book /by 2/12/2019");
        } catch (DukeException e) {
            assertEquals("put in a time pls", e.getMessage());
        }
    }
    @Test
    public void deleteTask_delete1_success() throws DukeException {
        tasks.addTask("T", "borrow book");
        tasks.deleteTask("delete 1");
        assertEquals(0, tasks.getSize());
    }
    @Test
    public void deleteTask_delete2_exceptionThrown() {
        try {
            tasks.addTask("T", "borrow book");
            tasks.deleteTask("delete 2");
        } catch (DukeException e) {
            assertEquals("You are trying to delete a Task that does not exist", e.getMessage());
        }
    }
    @Test
    public void markDescription_mark1_success() throws DukeException {
        tasks.addTask("T", "borrow book");
        tasks.markDescription("mark 1");
        assertEquals("X", tasks.getTask(0).getStatusIcon());
    }
    @Test
    public void markDescription_mark2_exceptionThrown() {
        try {
            tasks.addTask("T", "borrow book");
            tasks.markDescription("mark 2");
        } catch (DukeException e) {
            assertEquals("You are trying to access a Task that does not exist!", e.getMessage());
        }
    }
}
