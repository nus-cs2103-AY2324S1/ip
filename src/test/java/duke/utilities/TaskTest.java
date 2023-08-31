package duke.utilities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void test1() {
        Task task = new Task("homework", Type.DEADLINE,  " (by: Aug 03 2023)");
        task.markAsDone();
        assertEquals(task.isDone(), true);
        task.markAsNotDone();
        assertEquals(task.isDone(), false);
        task.markAsDone();
        String stringRepresentation = task.convertToString();
        assertEquals("[D][X] homework (by: Aug 03 2023)", stringRepresentation);
    }
}
