package duke.task;

import duke.Keyword;
import duke.Storage;
import duke.Time;
import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

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
                    + Storage.SEPARATOR + "2/12/2023 18:00";
            String expectedMark = "D" + Storage.SEPARATOR + "1" + Storage.SEPARATOR + "return book"
                    + Storage.SEPARATOR + "2/12/2023 18:00";
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
