package roo.task;

import roo.RooException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {
    @Test
    public void constructor_validInput1_success() {
        try {
            Event event = new Event("valid input 1", "30-08-2023 23:59", "31-08-2023 23:59");
            assertEquals("[E][ ] valid input 1 from: 30 Aug 2023 11:59 PM to: 31 Aug 2023 11:59 PM",
                    event.toString());
        } catch (RooException exception) {
            fail();
        }
    }

    @Test
    public void constructor_validInput2_success() {
        try {
            Event event = new Event("valid input 2", "30 Aug 2023 11:59 PM", "31 Aug 2023 11:59 PM");
            assertEquals("[E][ ] valid input 2 from: 30 Aug 2023 11:59 PM to: 31 Aug 2023 11:59 PM",
                    event.toString());
        } catch (RooException exception) {
            fail();
        }
    }

    @Test
    public void constructor_invalidInput1_fail() {
        try {
            Event event = new Event("", "30-08-2023 23:59", "31-08-2023 23:59");
            fail();
        } catch (RooException exception) {
            assertEquals("Description is EMPTY!!!\n", exception.getMessage());
        }
    }

    @Test
    public void constructor_noStartDate_fail() {
        try {
            Event event = new Event("no start date", "", "31-08-2023 23:59");
            fail();
        } catch (RooException exception) {
            assertEquals("Missing time!!!\n", exception.getMessage());
        }
    }

    @Test
    public void constructor_noEndDate_fail() {
        try {
            Event event = new Event("no end date", "30-08-2023 23:59", "");
            fail();
        } catch (RooException exception) {
            assertEquals("Missing time!!!\n", exception.getMessage());
        }
    }

    @Test
    public void getDate_validDate1_success() {
        try {
            Event event = new Event("valid date 1", "30-08-2023 21:00", "31-08-2023 23:59");
            assertEquals(LocalDateTime.parse("30-08-2023 21:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")),
                    event.getDate());
        } catch (RooException exception) {
            fail();
        }
    }

    @Test
    public void getDate_validDate2_success() {
        try {
            Event event = new Event("valid date 2", "30 Aug 2023 09:00 PM", "31 Aug 2023 11:59 PM");
            assertEquals(LocalDateTime.parse("30-08-2023 21:00", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")),
                    event.getDate());
        } catch (RooException exception) {
            fail();
        }
    }

}
