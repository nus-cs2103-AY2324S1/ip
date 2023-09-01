package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;


class EventTest {

    @Test
    void getFromAndToTest() {
        LocalDate fromDate = LocalDate.now();
        LocalDate toDate = fromDate.plusDays(2);
        Event event = new Event("Sample Event", fromDate, toDate);

        assertEquals(fromDate, event.getFrom());
        assertEquals(toDate, event.getTo());
    }

    @Test
    void toStringTest() {
        LocalDate fromDate = LocalDate.now();
        LocalDate toDate = fromDate.plusDays(2);
        Event event = new Event("Sample Event", fromDate, toDate);

        assertEquals("[E][ ] Sample Event (from: "
                + fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")", event.toString());

        event.markAsDone();
        assertEquals("[E][X] Sample Event (from: "
                + fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")", event.toString());
    }
}