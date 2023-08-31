package roo.task;

import roo.RooException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
    @Test
    public void constructor_validInput1_success() {
        try {
            Deadline deadline = new Deadline("valid input 1", "30-08-2023 23:59");
            assertEquals("[D][ ] valid input 1 by: 30 Aug 2023 11:59 PM", deadline.toString());
        } catch (RooException exception) {
            fail();
        }
    }

    @Test
    public void constructor_validInput2_success() {
        try {
            Deadline deadline = new Deadline("valid input 2", "30 Aug 2023 09:00 PM");
            assertEquals("[D][ ] valid input 2 by: 30 Aug 2023 09:00 PM", deadline.toString());
        } catch (RooException exception) {
            fail();
        }
    }

    @Test
    public void constructor_invalidInput_fail() {
        try {
            Deadline deadline = new Deadline("", "30/08/2023 2359");
            fail();
        } catch (RooException exception) {
            assertEquals("Description is EMPTY!!!\n", exception.getMessage());
        }
    }

    @Test
    public void getDate_validDate1_success() {
        try {
            Deadline deadline = new Deadline("valid date 1", "30-08-2023 21:00");
            assertEquals(LocalDateTime.parse("30-08-2023 21:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")),
                    deadline.getDate());
        } catch (RooException exception) {
            fail();
        }
    }

    @Test
    public void getDate_validDate2_success() {
        try {
            Deadline deadline = new Deadline("valid date 2", "30 Aug 2023 09:00 PM");
            assertEquals(LocalDateTime.parse("30-08-2023 21:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")),
                    deadline.getDate());
        } catch (RooException exception) {
            fail();
        }
    }

}
