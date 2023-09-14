package taskmanager;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import parser.DateTime;

class DeadlineTest {

    @Test
    void testEvents() {

        DateTime dateTime = new DateTime();
        String formattedDate = dateTime.formatDateTime("23/08/2023 1800");
        String formattedDate1 = dateTime.formatDateTime("23/08/2023 1801");
        String formattedDate2 = dateTime.formatDateTime("23/09/2023 1800");

        Deadline newdeadline = new Deadline("TestEvent", formattedDate);
        Deadline newdeadline1 = new Deadline("TestEvent", formattedDate1);
        Deadline newdeadline2 = new Deadline("TestEvent", formattedDate2);
        Deadline newdeadline3 = new Deadline("TestEvent3", formattedDate);
        Deadline newdeadline4 = new Deadline("TestEvent", formattedDate);




        assertTrue(newdeadline.equals(newdeadline));
        assertFalse(newdeadline.equals(newdeadline1));
        assertFalse(newdeadline.equals(newdeadline2));
        assertFalse(newdeadline.equals(newdeadline3));
        assertTrue(newdeadline.equals(newdeadline4));
    }
}
