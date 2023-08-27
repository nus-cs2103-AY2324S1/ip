package taskclasses;

import main.java.taskclasses.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testFormatToFile() {
        LocalDate fromDate = LocalDate.of(2023, 9, 1);
        LocalDate toDate = LocalDate.of(2023, 9, 3);
        Event event = new Event("Conference", fromDate, toDate);

        String formattedFromDate = fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String formattedToDate = toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String expected = "E | 0 | Conference | " + formattedFromDate + " - " + formattedToDate;
        assertEquals(expected, event.formatToFile());
    }

    @Test
    public void testToString() {
        LocalDate fromDate = LocalDate.of(2023, 9, 1);
        LocalDate toDate = LocalDate.of(2023, 9, 3);
        Event event = new Event("Party", true, fromDate, toDate);

        String formattedFromDate = fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String formattedToDate = toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String expected = "[E][X] Party (from: " + formattedFromDate + " to: " + formattedToDate + ")";
        assertEquals(expected, event.toString());
    }
}
