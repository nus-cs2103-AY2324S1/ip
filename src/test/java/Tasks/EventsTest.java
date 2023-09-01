package Tasks;

import Exceptions.InvalidTimeFormatException;
import Helpers.Ui;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventsTest {
    public static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    @Test
    public void testEventMarkDone_Success(){
        Events event = new Events("internship talk", false,
                LocalDateTime.parse("2023-09-01 14:00", dateTimeFormat),
                LocalDateTime.parse("2023-09-01 16:00", dateTimeFormat));
        assertEquals(event.markedAsDone().toString(),
                "[E][x] internship talk (from: 2023-09-01 14:00 to: 2023-09-01 16:00)");
    }

    @Test
    public void testEventUnmarkDone_Success(){
        Events event = new Events("internship talk", false,
                LocalDateTime.parse("2023-09-01 14:00", dateTimeFormat),
                LocalDateTime.parse("2023-09-01 16:00", dateTimeFormat));
        assertEquals(event.markedAsUndone().toString(),
                "[E][ ] internship talk (from: 2023-09-01 14:00 to: 2023-09-01 16:00)");
    }


    @Test
    public void testInvalidEvent_Exception(){
        Ui ui = new Ui();
        try {
            Events event = new Events("internship talk", false,
                    LocalDateTime.parse("invalid date", dateTimeFormat),
                    LocalDateTime.parse("invalid date", dateTimeFormat));
            fail();
        } catch (Exception e) {
            assertEquals("Macho! The input time format of a " + "invalid date"
                            + " is wrong, macho! Please enter in format " +
                            "yyyy-MM-dd HH:mm, macho!\n"
                            + "____________________________________________________________",
                    new InvalidTimeFormatException("invalid date", ui.getDivider()).getMessage());
        }


    }
}
