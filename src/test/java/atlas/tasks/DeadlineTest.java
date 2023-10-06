package atlas.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    final LocalDateTime testDateTime = LocalDateTime.parse("2023-08-25T00:00:00");
    final LocalDate testReminderStartDate = LocalDate.parse("2023-09-14");

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
    public void generateSaveString_unchecked_noReminder() {
        Deadline testDeadline = new Deadline("Test Deadline", testDateTime);
        testDeadline.markNotDone();
        assertEquals(testDeadline.generateSaveString(), "D | false | Test Deadline /by 25-08-2023 0000");
    }

    @Test
    public void generateSaveString_checked_noReminder() {
        Deadline testDeadline = new Deadline("Test Deadline", testDateTime);
        testDeadline.markDone();
        assertEquals(testDeadline.generateSaveString(), "D | true | Test Deadline /by 25-08-2023 0000");
    }

    @Test
    public void generateSaveString_unchecked_reminder() {
        Deadline testDeadline = new Deadline("Test Deadline", testDateTime, testReminderStartDate);
        testDeadline.markNotDone();
        assertEquals(testDeadline.generateSaveString(),
                "D | false | Test Deadline /by 25-08-2023 0000 /remind 14-09-2023");
    }

    @Test
    public void generateSaveString_checked_reminder() {
        Deadline testDeadline = new Deadline("Test Deadline", testDateTime, testReminderStartDate);
        testDeadline.markDone();
        assertEquals(testDeadline.generateSaveString(),
                "D | true | Test Deadline /by 25-08-2023 0000 /remind 14-09-2023");
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

    @Test
    public void shouldSendReminder_unchecked() {
        LocalDate pastDate = LocalDate.now().minusDays(7);
        Deadline testDeadlinePast = new Deadline("Test Deadline with Past Reminder Start Date",
                testDateTime, pastDate);
        testDeadlinePast.markNotDone();
        assertTrue(testDeadlinePast.shouldSendReminder());

        LocalDate today = LocalDate.now();
        Deadline testDeadlineToday = new Deadline("Test Deadline with Present Reminder Start Date",
                testDateTime, today);
        testDeadlineToday.markNotDone();
        assertTrue(testDeadlineToday.shouldSendReminder());

        LocalDate futureDate = LocalDate.now().plusDays(7);
        Deadline testDeadlineFuture = new Deadline("Test Deadline with Future Reminder Start Date",
                testDateTime, futureDate);
        testDeadlineFuture.markNotDone();
        assertFalse(testDeadlineFuture.shouldSendReminder());
    }

    @Test
    public void shouldSendReminder_checked() {
        LocalDate pastDate = LocalDate.now().minusDays(7);
        Deadline testDeadlinePast = new Deadline("Test Deadline with Past Reminder Start Date",
                testDateTime, pastDate);
        testDeadlinePast.markDone();
        assertFalse(testDeadlinePast.shouldSendReminder());

        LocalDate today = LocalDate.now();
        Deadline testDeadlineToday = new Deadline("Test Deadline with Present Reminder Start Date",
                testDateTime, today);
        testDeadlineToday.markDone();
        assertFalse(testDeadlineToday.shouldSendReminder());

        LocalDate futureDate = LocalDate.now().plusDays(7);
        Deadline testDeadlineFuture = new Deadline("Test Deadline with Future Reminder Start Date",
                testDateTime, futureDate);
        testDeadlineFuture.markDone();
        assertFalse(testDeadlineFuture.shouldSendReminder());
    }
}
