package taskmanager;

import org.junit.jupiter.api.Test;
import parser.DateTime;
import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void testEvents()  {

        DateTime dateTime = new DateTime();
        String formattedDate = dateTime.formatDateTime("23/08/2023 1800");
        String formattedDate1 = dateTime.formatDateTime("23/08/2023 1801");
        String formattedDate2 = dateTime.formatDateTime("23/09/2023 1800");

        Event newevent = new Event("TestEvent", formattedDate, formattedDate1);
        Event newevent1 = new Event("TestEvent1", formattedDate, formattedDate1);
        Event newevent2 = new Event("TestEvent", formattedDate, formattedDate2);
        Event newevent3 = new Event("TestEvent", formattedDate1, formattedDate1);
        Event newevent4 = new Event("TestEvent", formattedDate, formattedDate1);



        assertTrue(newevent.equals(newevent));
        assertFalse(newevent.equals(newevent1));
        assertFalse(newevent.equals(newevent2));
        assertFalse(newevent.equals(newevent3));
        assertTrue(newevent.equals(newevent4));

    }

}