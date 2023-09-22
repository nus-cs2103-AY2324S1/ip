package atlas.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class EventTest {
    final LocalDateTime testStartTime = LocalDateTime.parse("2023-08-25T00:00:00");
    final LocalDateTime testEndTime = LocalDateTime.parse("2023-08-25T23:59:59");
    final LocalDate testReminderStartDate = LocalDate.parse("2023-09-14");

    @Test
    public void toString_default() {
        Event testEvent = new Event("Test Event", testStartTime, testEndTime);
        assertEquals(testEvent.toString(), "[E][  ] Test Event (from: 25-08-2023 0000 to: 25-08-2023 2359)");
    }

    @Test
    public void toString_checkAndUncheck() {
        Event testEvent = new Event("Test Event", testStartTime, testEndTime);
        testEvent.markDone();
        assertEquals(testEvent.toString(), "[E][X] Test Event (from: 25-08-2023 0000 to: 25-08-2023 2359)");
        testEvent.markNotDone();
        assertEquals(testEvent.toString(), "[E][  ] Test Event (from: 25-08-2023 0000 to: 25-08-2023 2359)");

    }

    @Test
    public void generateSaveString_unchecked_noReminder() {
        Event testEvent = new Event("Test Event", testStartTime, testEndTime);
        testEvent.markNotDone();
        assertEquals(testEvent.generateSaveString(), "E | false | Test Event /from 25-08-2023 0000 "
                + "/to 25-08-2023 2359");
    }

    @Test
    public void generateSaveString_checked_noReminder() {
        Event testEvent = new Event("Test Event", testStartTime, testEndTime);
        testEvent.markDone();
        assertEquals(testEvent.generateSaveString(), "E | true | Test Event /from 25-08-2023 0000 "
                + "/to 25-08-2023 2359");
    }

    @Test
    public void generateSaveString_unchecked_reminder() {
        Event testEvent = new Event("Test Event", testStartTime, testEndTime, testReminderStartDate);
        testEvent.markNotDone();
        assertEquals(testEvent.generateSaveString(),
                "E | false | Test Event /from 25-08-2023 0000 /to 25-08-2023 2359 /remind 14-09-2023");
    }

    @Test
    public void generateSaveString_checked_reminder() {
        Event testEvent = new Event("Test Event", testStartTime, testEndTime, testReminderStartDate);
        testEvent.markDone();
        assertEquals(testEvent.generateSaveString(),
                "E | true | Test Event /from 25-08-2023 0000 /to 25-08-2023 2359 /remind 14-09-2023");
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

    @Test
    public void shouldSendReminder_unchecked() {
        LocalDate pastDate = LocalDate.now().minusDays(7);
        Event testEventPast = new Event("Test Event with Past Reminder Start Date",
                testStartTime, testEndTime, pastDate);
        testEventPast.markNotDone();
        assertTrue(testEventPast.shouldSendReminder());

        LocalDate today = LocalDate.now();
        Event testEventToday = new Event("Test Event with Present Reminder Start Date",
                testStartTime, testEndTime, today);
        testEventToday.markNotDone();
        assertTrue(testEventToday.shouldSendReminder());

        LocalDate futureDate = LocalDate.now().plusDays(7);
        Event testEventFuture = new Event("Test Event with Future Reminder Start Date",
                testStartTime, testEndTime, futureDate);
        testEventFuture.markNotDone();
        assertFalse(testEventFuture.shouldSendReminder());
    }

    @Test
    public void shouldSendReminder_checked() {
        LocalDate pastDate = LocalDate.now().minusDays(7);
        Event testEventPast = new Event("Test Event with Past Reminder Start Date",
                testStartTime, testEndTime, pastDate);
        testEventPast.markDone();
        assertFalse(testEventPast.shouldSendReminder());

        LocalDate today = LocalDate.now();
        Event testEventToday = new Event("Test Event with Present Reminder Start Date",
                testStartTime, testEndTime, today);
        testEventToday.markDone();
        assertFalse(testEventToday.shouldSendReminder());

        LocalDate futureDate = LocalDate.now().plusDays(7);
        Event testEventFuture = new Event("Test Event with Future Reminder Start Date",
                testStartTime, testEndTime, futureDate);
        testEventFuture.markDone();
        assertFalse(testEventFuture.shouldSendReminder());
    }
}
