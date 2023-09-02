package tasks;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
public class TaskListTest {

    @Test
    public void markIndexNotExist_success() {
        try {
            TaskList tasklist = new TaskList();
            tasklist.handleMark("mark 1");
        } catch (exceptions.DukeException e) {
            assertEquals("This Task index does not exist!", e.getMessage());
        }
    }

    @Test
    public void deleteIndexNotExist_success() {
        try {
            TaskList tasklist = new TaskList();
            tasklist.handleMark("delete 1");
        } catch (exceptions.DukeException e) {
            assertEquals("This Task index does not exist!", e.getMessage());
        }
    }

}
