package bouncybob.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlinesTest {

    @Test
    public void testGetSymbol() {
        Deadlines deadline = new Deadlines("Test Task", "2023-08-30 1234");
        assertEquals("D", deadline.getSymbol());
    }

    @Test
    public void testGetDescription() {
        Deadlines deadline = new Deadlines("Test Task", "2023-08-30 1234");
        assertEquals("Test Task (by: Aug 30 2023)", deadline.getDescription());
    }

    @Test
    public void testToFileFormat() {
        Deadlines deadline = new Deadlines("Test Task", "2023-08-30 1234");
        assertEquals("D | 0 | Test Task | 2023-08-30 1234", deadline.toFileFormat());
    }
}
