package dialogix.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

class EventTest {
    @Test
    void eventTestWithString() {
        Event test = new Event("event", "eventTime");
        assertEquals("[E][X] event (at: eventTime)", test.toString(), "toString() method works");

        test.markAsDone();
        assertEquals("[E][O] event (at: eventTime)", test.toString(), "markAsDone() method works");
    }

    @Test
    void eventTestWithDate() {
        Event test = new Event("event", new Date(0));
        assertTrue(test.toString().contains("[E][X] event"), "toString() method works");

        test.markAsDone();
        assertTrue(test.toString().contains("[E][O] event"), "toString() method works");
    }

    @Test
    void eventTestOutputFormatWithString() {
        Event test = new Event("event", "eventTime");
        assertEquals("E | 0 | event | eventTime", test.getOutputFormat(), "getOutputFormat() method works");

        test.markAsDone();
        assertEquals("E | 1 | event | eventTime", test.getOutputFormat(), "markAsDone() method works");
    }

    @Test
    void eventTestOutputFormatWithDate() {
        Event test = new Event("event", new Date(0));
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        String formattedDate = dateFormatter.format(new Date(0));
        assertEquals("E | 0 | event | " + formattedDate, test.getOutputFormat(), "getOutputFormat() method works");

        test.markAsDone();
        assertEquals("E | 1 | event | " + formattedDate, test.getOutputFormat(), "markAsDone() method works");
    }
}
