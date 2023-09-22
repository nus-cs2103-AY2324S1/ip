package echobot.utilities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    private Task task = new Task("homework", Type.DEADLINE, " (by: Aug 03 2023)");

    @Test
    public void test1() {
        task.markAsDone();
        String stringRepresentation = task.convertToString();
        assertEquals(task.isDone(), true);
        assertEquals("[D][X] homework\n\t (by: Aug 03 2023)", stringRepresentation);
    }

    @Test
    public void test2() {
        task.markAsNotDone();
        String stringRepresentation = task.convertToString();
        assertEquals(task.isDone(), false);
        assertEquals("[D][  ] homework\n\t (by: Aug 03 2023)", stringRepresentation);
    }
}
