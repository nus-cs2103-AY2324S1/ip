package tasks;

import org.junit.jupiter.api.Test;
import parsers.DateTimeParser;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toString_success() {
        LocalDateTime dateTime = DateTimeParser.parseDateTime("2019-10-10 1800");
        Deadline newDeadline = new Deadline(dateTime, "potato");
        assertEquals("[D][ ] potato(by: Oct 10 2019 6PM)", newDeadline.toString());
    }

    @Test
    public void snooze_success() {
        LocalDateTime dateTime = DateTimeParser.parseDateTime("2019-10-10 1800");
        Deadline newDeadline = new Deadline(dateTime, "potato");
        String expected = "I have snoozed your deadline! It is now due by Oct 11 2019 6PM\n";
        assertEquals(expected, newDeadline.snooze());
    }

    @Test
    public void toStringWithDateTime_success() {
        LocalDateTime dateTime = DateTimeParser.parseDateTime("2019-10-10 1800");
        Deadline newDeadline = new Deadline(dateTime, "potato");
        String expected = "[D][ ] potato DATETIME Oct 10 2019 6PM";
        assertEquals(expected, newDeadline.toStringWithDateTime());
    }

}
