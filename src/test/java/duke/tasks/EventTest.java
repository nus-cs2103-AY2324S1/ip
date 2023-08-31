package duke.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {
    final LocalDateTime testStartTime = LocalDateTime.parse("2023-08-25T00:00:00");
    final LocalDateTime testEndTime = LocalDateTime.parse("2023-08-25T23:59:59");

    @Test
    public void toString_default() {
        Event testEvent = new Event("Test Event", testStartTime, testEndTime);
        assertEquals(testEvent.toString(), "[E][ ] Test Event (from: 25-08-2023 0000 to: 25-08-2023 2359)");
    }

    @Test
    public void toString_checkAndUncheck() {
        Event testEvent = new Event("Test Event", testStartTime, testEndTime);
        testEvent.markAsDone();
        assertEquals(testEvent.toString(), "[E][X] Test Event (from: 25-08-2023 0000 to: 25-08-2023 2359)");
        testEvent.markAsNotDone();
        assertEquals(testEvent.toString(), "[E][ ] Test Event (from: 25-08-2023 0000 to: 25-08-2023 2359)");

    }

    @Test
    public void generateSaveString_unchecked() {
        Event testEvent = new Event("Test Event", testStartTime, testEndTime);
        assertEquals(testEvent.generateSaveString(), "E | false | Test Event /from 25-08-2023 0000 "
                + "/to 25-08-2023 2359");
    }

    @Test
    public void generateSaveString_checked() {
        Event testEvent = new Event("Test Event", testStartTime, testEndTime);
        testEvent.markAsDone();
        assertEquals(testEvent.generateSaveString(), "E | true | Test Event /from 25-08-2023 0000 "
                + "/to 25-08-2023 2359");
    }

    @Test
    public void isOccurringOnDate() {
        Event testEvent = new Event("Test Event", testStartTime, testEndTime);
        LocalDate testDate1 = LocalDate.parse("2023-08-24");
        LocalDate testDate2 = LocalDate.parse("2023-08-25");
        LocalDate testDate3 = LocalDate.parse("2023-08-26");
        assertFalse(testEvent.isOccurringOnDate(testDate1));
        assertTrue(testEvent.isOccurringOnDate(testDate2));
        assertFalse(testEvent.isOccurringOnDate(testDate3));
    }
}
