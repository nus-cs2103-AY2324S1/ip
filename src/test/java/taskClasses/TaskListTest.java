package taskClasses;

import duke.exception.InvalidDateTimeException;
import duke.taskClasses.TaskList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {

    private TaskList tasks;

    @BeforeEach
    public void setUp() {
        tasks = new TaskList();
    }

    @Test
    public void testAddToDoWithDescriptionOnly() {
        tasks.addToDoToList(false, "Study Java");
        assertEquals(1, tasks.getTasksCount());
        assertEquals("[T][ ] Study Java", tasks.getStatusAndDescription(1));
    }

    @Test
    public void testAddToDoMarkedAsDone() {
        tasks.addToDoToList(true, "Read a novel");
        assertEquals(1, tasks.getTasksCount());
        assertEquals("[T][X] Read a novel", tasks.getStatusAndDescription(1));
    }

    @Test
    public void testAddMultipleToDos() {
        tasks.addToDoToList(true, "Task 1");
        tasks.addToDoToList(false, "Task 2");
        assertEquals(2, tasks.getTasksCount());
        assertEquals("[T][X] Task 1", tasks.getStatusAndDescription(1));
        assertEquals("[T][ ] Task 2", tasks.getStatusAndDescription(2));
    }

    @Test
    public void testAddEventWithDates() throws InvalidDateTimeException {
        tasks.addEventToList(false, "Duke project meeting", "2023-09-01", "2023-09-02");
        assertEquals(1, tasks.getTasksCount());
        assertEquals("[E][ ] Duke project meeting (from: Sep 1 2023 to: Sep 2 2023)", tasks.getStatusAndDescription(1));
    }

    @Test
    public void testAddEventWithStartDateAfterEndDate() {
        assertThrows(InvalidDateTimeException.class, () -> tasks.addEventToList(false, "Duke project meeting", "2023-09-40", "2023-09-02"));
    }

    @Test
    public void testAddEventMarkedAsDone() throws InvalidDateTimeException {
        tasks.addEventToList(true, "Graduation", "2023-10-01", "2023-10-02");
        assertEquals(1, tasks.getTasksCount());
        assertEquals("[E][X] Graduation (from: Oct 1 2023 to: Oct 2 2023)", tasks.getStatusAndDescription(1));
    }

}
