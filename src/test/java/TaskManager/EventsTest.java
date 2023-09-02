package taskmanager;

import org.junit.jupiter.api.Test;
import parser.DateTime;
import static org.junit.jupiter.api.Assertions.*;

class EventsTest {

    @Test
    void testEvents()  {

        DateTime dateTime = new DateTime();
        String formattedDate = dateTime.formatDateTime("23/08/2023 1800");
        String formattedDate1 = dateTime.formatDateTime("23/08/2023 1801");
        String formattedDate2 = dateTime.formatDateTime("23/09/2023 1800");

        Events newevent = new Events("TestEvent", formattedDate, formattedDate1);
        Events newevent1 = new Events("TestEvent1", formattedDate, formattedDate1);
        Events newevent2 = new Events("TestEvent", formattedDate, formattedDate2);
        Events newevent3 = new Events("TestEvent", formattedDate1, formattedDate1);
        Events newevent4 = new Events("TestEvent", formattedDate, formattedDate1);



        assertTrue(newevent.equals(newevent));
        assertFalse(newevent.equals(newevent1));
        assertFalse(newevent.equals(newevent2));
        assertFalse(newevent.equals(newevent3));
        assertTrue(newevent.equals(newevent4));

    }

}