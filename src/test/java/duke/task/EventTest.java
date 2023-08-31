package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.Keyword;
import duke.Storage;
import duke.Time;
import duke.exception.DukeException;


public class EventTest {

    @Test
    public void toStringTest() {
        try {
            Event e = new Event("project meeting", Time.parseDateTime("1/12/2023 18:00"),
                    Time.parseDateTime("2/12/2023 20:00"));
            assertEquals("[E][ ] project meeting (from: 1 Dec 2023, 6:00PM to: 2 Dec 2023, 8:00PM)",
                    e.toString());
            e.mark(true);
            assertEquals("[E][X] project meeting (from: 1 Dec 2023, 6:00PM to: 2 Dec 2023, 8:00PM)",
                    e.toString());
            e.mark(false);
            assertEquals("[E][ ] project meeting (from: 1 Dec 2023, 6:00PM to: 2 Dec 2023, 8:00PM)",
                    e.toString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void fileFormatTest() {
        try {
            Event e = new Event("return book", Time.parseDateTime("1/12/2023 18:00"),
                    Time.parseDateTime("2/12/2023 20:00"));
            String expectedUnmark = "E" + Storage.SEPARATOR + "0" + Storage.SEPARATOR + "return book"
                    + Storage.SEPARATOR + "1/12/2023 18:00" + Storage.SEPARATOR + "2/12/2023 20:00";
            String expectedMark = "E" + Storage.SEPARATOR + "1" + Storage.SEPARATOR + "return book"
                    + Storage.SEPARATOR + "1/12/2023 18:00" + Storage.SEPARATOR + "2/12/2023 20:00";
            assertEquals(expectedUnmark, e.fileFormat());
            e.mark(true);
            assertEquals(expectedMark, e.fileFormat());
            e.mark(false);
            assertEquals(expectedUnmark, e.fileFormat());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void onDateTest() {
        try {
            Event e = new Event("return book", Time.parseDateTime("1/12/2023 18:00"),
                    Time.parseDateTime("2/12/2023 18:00"));
            assertFalse(e.onDate(Keyword.DEADLINE, LocalDate.parse("2023-12-02")));
            assertTrue(e.onDate(Keyword.EVENT, LocalDate.parse("2023-12-02")));
            assertTrue(e.onDate(Keyword.EVENT, LocalDate.parse("2023-12-01")));
            assertFalse(e.onDate(Keyword.EVENT, LocalDate.parse("2023-12-03")));
            assertFalse(e.onDate(Keyword.EVENT, LocalDate.parse("2023-11-30")));
        } catch (DukeException e) {
            fail();
        }
    }
}
