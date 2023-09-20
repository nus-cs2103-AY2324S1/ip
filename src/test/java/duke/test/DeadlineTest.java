package duke.test;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testString() {
        Deadline testDeadline = new Deadline("deadline return book /by Sunday");
        assertEquals(testDeadline.toString(), "[D][ ] return book (by: Sunday)");
    }

    @Test
    public void testFormat() {
        Deadline testDeadline = new Deadline("deadline return book /by 2012-12-12");
        assertEquals(testDeadline.writtenFormat(), "D | 0 | return book | Dec 12 2012");
    }

    @Test
    public void testMark() {
        try {
            Deadline testDeadline = new Deadline("deadline return book /by 2012-12-12");
            testDeadline.markDone();
            assertEquals(testDeadline.toString(), "[D][X] return book (by: Dec 12 2012)");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

}
