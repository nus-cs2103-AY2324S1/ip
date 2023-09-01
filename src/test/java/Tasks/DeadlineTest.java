package Tasks;

import Exceptions.InvalidTimeFormatException;
import Helpers.Ui;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {

    public static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    @Test
    public void testDeadlineMarkDone_Success(){
        Deadline deadline = new Deadline("finish homework", false,
                LocalDateTime.parse("2023-09-01 23:59", dateTimeFormat));
        assertEquals(deadline.markedAsDone().toString(),"[D][x] finish homework (by: 2023-09-01 23:59)");
    }

    @Test
    public void testDeadlineUnmarkDone_Success(){
        Deadline deadline = new Deadline("finish homework", false,
                LocalDateTime.parse("2023-09-01 23:59", dateTimeFormat));
        assertEquals(deadline.markedAsUndone().toString(),"[D][ ] finish homework (by: 2023-09-01 23:59)");
    }


    @Test
    public void testInvalidDeadline_Exception(){
        Ui ui = new Ui();
        try {
            Deadline deadline = new Deadline("finish homework", false,
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
