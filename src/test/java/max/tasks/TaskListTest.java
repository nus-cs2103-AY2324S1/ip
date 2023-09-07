package max.tasks;

import max.exception.MaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
public class TaskListTest {
    @Test
    public void delete_taskNumberLargerThanMax_exceptionThrown() {
        try {
            TaskList tasks = new TaskList();
            tasks.delete(1);
        } catch (MaxException e) {
            assertEquals("     Seems like that number is out of range. Check again!", e.getMessage());
        }
    }

    @Test
    public void mark_negativeTaskNumber_exceptionThrown() {
        try {
            TaskList tasks = new TaskList();
            tasks.mark(-1);
        } catch (MaxException e) {
            assertEquals("     Seems like that number is out of range. Check again!", e.getMessage());
        }
    }
}
