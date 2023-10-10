package taskmanager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import functional.DukeException;
import tasks.Deadline;

class DeadlineTest {

    @Test
    void testEvents() throws DukeException {
        Deadline newdeadline = new Deadline("deadline TestEvent /by 23/08/2023 1800", false);
        Deadline newdeadline1 = new Deadline("deadline TestEvent /by 23/08/2023 1801", false);
        Deadline newdeadline2 = new Deadline("deadline TestEvent /by 23/09/2023 1800", false);
        Deadline newdeadline3 = new Deadline("deadline TestEvent3 /by 23/08/2023 1800", false);
        Deadline newdeadline4 = new Deadline("deadline TestEvent /by 23/08/2023 1800", false);

        assertTrue(newdeadline.equals(newdeadline));
        assertFalse(newdeadline.equals(newdeadline1));
        assertFalse(newdeadline.equals(newdeadline2));
        assertFalse(newdeadline.equals(newdeadline3));
        assertEquals(newdeadline.toString(), newdeadline4.toString());
    }

}
