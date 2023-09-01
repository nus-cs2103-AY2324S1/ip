package duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlinesTest {

    private Deadlines deadline;

    @BeforeEach
    public void setUp() {
        deadline = new Deadlines("Finish homework", "2023/09/01 1600");
    }

    @Test
    public void testGetSavingFormat_NotCompleted() {
        String expected = "[D] | [ ] | Finish homework | 2023/09/01 1600";
        assertEquals(expected, deadline.getSavingFormat());
    }

    @Test
    public void testToString_NotCompleted() {
        // Assuming DateTime's toString outputs the string in the format: "2023-09-01 16:00"
        String expected = "[D][ ] Finish homework (by: 09-1-2023 16:00)";
        assertEquals(expected, deadline.toString());
    }
}