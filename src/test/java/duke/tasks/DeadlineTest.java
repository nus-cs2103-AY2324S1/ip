package duke.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {
    final LocalDateTime testDateTime = LocalDateTime.parse("2023-08-25T00:00:00");

    @Test
    public void toString_default() {
        Deadline testDeadline = new Deadline("Test Deadline", testDateTime);
        assertEquals(testDeadline.toString(), "[D][ ] Test Deadline (by: 25-08-2023 0000)");
    }

    @Test
    public void toString_checkAndUncheck() {
        Deadline testDeadline = new Deadline("Test Deadline", testDateTime);
        testDeadline.markDone();
        assertEquals(testDeadline.toString(), "[D][X] Test Deadline (by: 25-08-2023 0000)");
        testDeadline.markNotDone();
        assertEquals(testDeadline.toString(), "[D][ ] Test Deadline (by: 25-08-2023 0000)");
    }

    @Test
    public void generateSaveString_unchecked() {
        Deadline testDeadline = new Deadline("Test Deadline", testDateTime);
        assertEquals(testDeadline.generateSaveString(), "D | false | Test Deadline /by 25-08-2023 0000");
    }

    @Test
    public void generateSaveString_checked() {
        Deadline testDeadline = new Deadline("Test Deadline", testDateTime);
        testDeadline.markDone();
        assertEquals(testDeadline.generateSaveString(), "D | true | Test Deadline /by 25-08-2023 0000");
    }

    @Test
    public void isOccurringOnDate() {
        Deadline testDeadline = new Deadline("Test Deadline", testDateTime);
        LocalDate testDate1 = LocalDate.parse("2023-08-24");
        LocalDate testDate2 = LocalDate.parse("2023-08-25");
        LocalDate testDate3 = LocalDate.parse("2023-08-26");
        assertFalse(testDeadline.isOccurringOnDate(testDate1));
        assertTrue(testDeadline.isOccurringOnDate(testDate2));
        assertFalse(testDeadline.isOccurringOnDate(testDate3));
    }
}
