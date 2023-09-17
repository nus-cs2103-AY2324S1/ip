package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.util.Keyword;
import duke.util.Storage;
import duke.util.Time;

/**
 * JUnit test class for Deadline.
 */
public class DeadlineTest {

    @Test
    public void toStringTest() {
        try {
            Deadline d = new Deadline("return book", Time.parseDateTime("2/12/2023 18:00"));
            assertEquals("[D][ ] return book (by: 2 Dec 2023, 6:00PM)", d.toString());
            d.mark(true);
            assertEquals("[D][X] return book (by: 2 Dec 2023, 6:00PM)", d.toString());
            d.mark(false);
            assertEquals("[D][ ] return book (by: 2 Dec 2023, 6:00PM)", d.toString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void fileFormatTest() {
        try {
            Deadline d = new Deadline("return book", Time.parseDateTime("2/12/2023 18:00"));
            String expectedUnmark = "D" + Storage.SEPARATOR + "0" + Storage.SEPARATOR + "return book"
                    + Storage.SEPARATOR + "2/12/2023 18:00\n";
            String expectedMark = "D" + Storage.SEPARATOR + "1" + Storage.SEPARATOR + "return book"
                    + Storage.SEPARATOR + "2/12/2023 18:00\n";
            assertEquals(expectedUnmark, d.fileFormat());
            d.mark(true);
            assertEquals(expectedMark, d.fileFormat());
            d.mark(false);
            assertEquals(expectedUnmark, d.fileFormat());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void onDateTest() {
        try {
            Deadline d = new Deadline("return book", Time.parseDateTime("2/12/2023 18:00"));
            assertFalse(d.onDate(Keyword.EVENT, LocalDate.parse("2023-12-02")));
            assertTrue(d.onDate(Keyword.DEADLINE, LocalDate.parse("2023-12-02")));
            assertTrue(d.onDate(Keyword.DEADLINE, LocalDate.parse("2023-12-01")));
            assertFalse(d.onDate(Keyword.DEADLINE, LocalDate.parse("2023-12-03")));
        } catch (DukeException e) {
            fail();
        }
    }
}
