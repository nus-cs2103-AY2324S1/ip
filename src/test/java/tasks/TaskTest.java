package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void testStringConversion() {
        Task actual = new Task("eat cereal", false, null);
        assertEquals("0 | eat cereal", actual.toString());
    }

    @Test
    public void hasWordTest() {
        Task actual = new Task("buy milk", false, null);
        assertEquals(true, actual.hasWord("milk"));
        assertEquals(false, actual.hasWord("cereal"));
    }

    @Test
    public void hasUpcomingReminderTest() {
        Task actualDatePassed = new Task("buy milk", false, "23/9/2023 1500");
        assertEquals(true, actualDatePassed.hasUpcomingReminder());
        Task actualDateNotPassed = new Task("buy milk", false, "23/9/2030 1500");
        assertEquals(false, actualDateNotPassed.hasUpcomingReminder());
    }

    @Test
    public void testProcessReminderDate() {
        Task actual = new Task("buy cereal", false, null);
        assertEquals("22/9/2023 1500", actual.processReminderDate("2", "24/9/2023 1500"));
        assertEquals("30/8/2023 1500", actual.processReminderDate("2", "1/9/2023 1500"));
        assertEquals("30/8/2023 1500", actual.processReminderDate("6", "5/9/2023 1500"));
    }
}
