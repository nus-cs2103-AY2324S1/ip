package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
public class DeadlineTest {


    @Test
    public void testDeadlineToString_notDone_success() {
        Deadline deadline = new Deadline("Submit Assignment", "2023-12-01 2359");
        String expectedOutput = "[D][ ] Submit Assignment (by: Dec 01 2023 2359)";
        assertEquals(expectedOutput, deadline.toString());
    }

    @Test
    public void testMarkAsDone_success() {
        Deadline deadline = new Deadline("Submit Assignment", "2023-12-01 2359");
        deadline.markAsDone();
        assertTrue(deadline.isDone);
        assertEquals("[D][X] Submit Assignment (by: Dec 01 2023 2359)", deadline.toString());
    }

    @Test
    public void testDeadlineToFile_notDone_success() {
        Deadline deadline = new Deadline("Submit Assignment", "2023-12-01 2359");
        String expectedOutput = "D | 0 | Submit Assignment | 2023-12-01T23:59";
        assertEquals(expectedOutput, deadline.toFile());
    }

    @Test
    public void testParseDateTime_alternateFormat_success() {
        Deadline deadline = new Deadline("Submit Assignment", "1/12/2022 2359");
        String expectedOutput = "[D][ ] Submit Assignment (by: Dec 01 2022 2359)";
        assertEquals(expectedOutput, deadline.toString());
    }
}
