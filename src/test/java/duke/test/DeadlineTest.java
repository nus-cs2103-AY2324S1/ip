package duke.test;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tools.Parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    private Parser parser = new Parser();
    @Test
    public void testString() {
        Deadline testDeadline = new Deadline(this.parser.parseDeadline("deadline return book /by Sunday"));
        assertEquals(testDeadline.toString(), "Make sure you've either inputted a valid day or in yyyy-mm-dd format (e.g. 2019-10-15)");
    }

    @Test
    public void testFormat() {
        Deadline testDeadline = new Deadline(this.parser.parseDeadline("deadline return book /by 2012-12-12"));
        assertEquals(testDeadline.writtenFormat(), "D | 0 | return book | Dec 12 2012");
    }

    @Test
    public void testMark() {
        try {
            Deadline testDeadline = new Deadline(this.parser.parseDeadline("deadline return book /by 2012-12-12"));
            testDeadline.markDone();
            assertEquals(testDeadline.toString(), "[D][X] return book (by: Dec 12 2012)");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

}
