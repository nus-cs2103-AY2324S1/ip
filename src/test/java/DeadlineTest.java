import Exceptions.DukeException;
import Tasks.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
    @Test
    public void printString_markAndUnmark_success() throws DukeException {
        Deadline deadline = new Deadline("xxx /by 2/4/2023");
        assertEquals("[D][ ] xxx (by: 2 Apr 2023)", deadline.toString());
        deadline.markAsDone();
        assertEquals("[D][X] xxx (by: 2 Apr 2023)", deadline.toString());
        deadline.unmarkAsDone();
        assertEquals("[D][ ] xxx (by: 2 Apr 2023)", deadline.toString());
    }

    @Test
    public void createDeadline_invalidDeadline_exceptionThrown(){
        try {
            Deadline deadline = new Deadline("xxx /by yyy");
            assertEquals("[D][ ] xxx (by: yyy)", deadline.toString());
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid Date format. Here are some example dates:\n" +
                    "6/3/2023, 16/12/2024", e.getMessage());
        }
    }
}
