package taskmanager;

import parser.DateTime;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeadlinesTest {

    @Test
    void testEvents()  {

        DateTime dateTime = new DateTime();
        String formattedDate = dateTime.formatDateTime("23/08/2023 1800");
        String formattedDate1 = dateTime.formatDateTime("23/08/2023 1801");
        String formattedDate2 = dateTime.formatDateTime("23/09/2023 1800");

        Deadlines newdeadline = new Deadlines("TestEvent", formattedDate);
        Deadlines newdeadline1 = new Deadlines("TestEvent", formattedDate1);
        Deadlines newdeadline2 = new Deadlines("TestEvent", formattedDate2);
        Deadlines newdeadline3 = new Deadlines("TestEvent3", formattedDate);
        Deadlines newdeadline4 = new Deadlines("TestEvent", formattedDate);




        assertTrue(newdeadline.equals(newdeadline));
        assertFalse(newdeadline.equals(newdeadline1));
        assertFalse(newdeadline.equals(newdeadline2));
        assertFalse(newdeadline.equals(newdeadline3));
        assertTrue(newdeadline.equals(newdeadline4));



    }

}