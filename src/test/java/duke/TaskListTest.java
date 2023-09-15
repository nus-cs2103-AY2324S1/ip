package duke;

import duke.task.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    private TaskList tasks;

    @BeforeEach
    public void init() {
        tasks = new TaskList();
        tasks.add(new Todo("Task 1"));
        tasks.add(new Todo("Task 2"));
        tasks.add(new Todo("Task 3"));
    }

    @Test
    public void getSize_correctValue() {
        assertEquals(3, tasks.size());
    }

    @Test
    public void removeTask_indexInRange_success() {
        try {
            assertEquals("[T][ ] Task 2", tasks.remove(2).toString());
        } catch (DukeException e) {
            // should not throw
            fail();
        }
    }

    @Test
    public void removeTask_indexOutOfBounds_exceptionThrown() {
        try {
            tasks.remove(5);
            fail();
        } catch (DukeException e) {
            assertEquals(Ui.LINE + Messages.MESSAGE_INVALID_INDEX + Ui.LINE, e.getMessage());
        }
    }

    @Test
    public void getSaveString_correctOutput() {
        assertEquals("T | 0 | Task 1\n" +
                "T | 0 | Task 2\n" +
                "T | 0 | Task 3\n",
                tasks.getSaveString());
    }

    @Test
    public void markAsDone_indexInRange_success() {
        try {
            assertEquals("[T][X] Task 2", tasks.markAsDone(2).toString());
        } catch (DukeException e) {
            // should not throw
            fail();
        }
    }

    @Test
    public void markAsDone_indexOutOfBounds_exceptionThrown() {
        try {
            tasks.markAsDone(5);
            fail();
        } catch (DukeException e) {
            assertEquals(Ui.LINE + Messages.MESSAGE_INVALID_INDEX + Ui.LINE, e.getMessage());
        }
    }

    @Test
    public void markAsUndone_indexInRange_success() {
        try {
            tasks.markAsDone(2);
            assertEquals("[T][ ] Task 2", tasks.markAsUndone(2).toString());
        } catch (DukeException e) {
            // should not throw
            fail();
        }
    }

    @Test
    public void markAsUndone_indexOutOfBounds_exceptionThrown() {
        try {
            tasks.markAsUndone(5);
            fail();
        } catch (DukeException e) {
            assertEquals(Ui.LINE + Messages.MESSAGE_INVALID_INDEX + Ui.LINE, e.getMessage());
        }
    }
}
