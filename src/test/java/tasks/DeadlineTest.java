package taskmanager;

import org.junit.jupiter.api.Test;
import tasks.Deadline;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DeadlineTest {

    @Test
    void testEvents()  {
        Deadline newdeadline = new Deadline("TestEvent /by 23/08/2023 1800", false);
        Deadline newdeadline1 = new Deadline("TestEvent /by 23/08/2023 1801", false);
        Deadline newdeadline2 = new Deadline("TestEvent /by 23/09/2023 1800", false);
        Deadline newdeadline3 = new Deadline("TestEvent3 /by 23/08/2023 1800", false);
        Deadline newdeadline4 = new Deadline("TestEvent /by 23/08/2023 1800", false);

        assertTrue(newdeadline.equals(newdeadline));
        assertFalse(newdeadline.equals(newdeadline1));
        assertFalse(newdeadline.equals(newdeadline2));
        assertFalse(newdeadline.equals(newdeadline3));
        assertTrue(newdeadline.equals(newdeadline4));
    }

}
