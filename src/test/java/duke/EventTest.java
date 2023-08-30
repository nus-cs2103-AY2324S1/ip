package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class EventTest {
    @Test
    public void event_toString_test() {
        String expected = "[E][ ] project meeting (from: Aug 08 2023 3PM to: 4PM)";
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyyMMdd HHmm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HHmm");
        String from = "20230808 1500";
        String to = "1600";
        LocalDateTime dateTimeFrom = LocalDateTime.parse(from, formatter1);
        LocalTime dateTimeTo = LocalTime.parse(to, formatter2);
        String actual = new Event("project meeting ", dateTimeFrom, dateTimeTo, false).toString();

        assertEquals(expected, actual);
    }
}
