package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;



public class DeadlineTest {
    @Test
    public void testToStringWithoutTime() {
        Deadline deadline = new Deadline("Submit report", LocalDate.parse("2023-08-30"), null);
        String expectedString = "[D][ ] Submit report (by: Aug 30 2023)";

        assertEquals(expectedString, deadline.toString());
    }

    @Test
    public void testToStringWithTime() {
        Deadline deadline = new Deadline("Assignment",
                LocalDate.parse("2023-08-25"), LocalTime.parse("14:00"));
        String expectedString = "[D][ ] Assignment (by: Aug 25 2023 02:00 PM)";

        assertEquals(expectedString, deadline.toString());
    }

    @Test
    public void testSaveWithoutTime() {
        Deadline deadline = new Deadline("Meeting", LocalDate.parse("2023-08-15"), null);
        String expectedSaveString = "D|0|Meeting|2023-08-15";

        assertEquals(expectedSaveString, deadline.save());
    }

    @Test
    public void testSaveWithTime() {
        Deadline deadline = new Deadline("Project",
                LocalDate.parse("2023-09-01"), LocalTime.parse("09:30"));
        String expectedSaveString = "D|0|Project|2023-09-01 0930";

        assertEquals(expectedSaveString, deadline.save());
    }
}
