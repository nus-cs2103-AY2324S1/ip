package atlas.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class TodoTest {
    final LocalDate testReminderStartDate = LocalDate.parse("2023-09-14");

    @Test
    public void toString_default() {
        Todo testTodo = new Todo("Test Todo");
        assertEquals(testTodo.toString(), "[T][ ] Test Todo");
    }

    @Test
    public void toString_checkAndUncheck() {
        Todo testTodo = new Todo("Test Todo");
        testTodo.markDone();
        assertEquals(testTodo.toString(), "[T][X] Test Todo");
        testTodo.markNotDone();
        assertEquals(testTodo.toString(), "[T][ ] Test Todo");
    }

    @Test
    public void generateSaveString_unchecked_noReminder() {
        Todo testTodo = new Todo("Test Todo");
        testTodo.markNotDone();
        assertEquals(testTodo.generateSaveString(), "T | false | Test Todo");
    }

    @Test
    public void generateSaveString_checked_noReminder() {
        Todo testTodo = new Todo("Test Todo");
        testTodo.markDone();
        assertEquals(testTodo.generateSaveString(), "T | true | Test Todo");
    }

    @Test
    public void generateSaveString_unchecked_reminder() {
        Todo testTodo = new Todo("Test Todo", testReminderStartDate);
        testTodo.markNotDone();
        assertEquals(testTodo.generateSaveString(), "T | false | Test Todo /remind 14-09-2023");
    }

    @Test
    public void generateSaveString_checked_reminder() {
        Todo testTodo = new Todo("Test Todo", testReminderStartDate);
        testTodo.markDone();
        assertEquals(testTodo.generateSaveString(), "T | true | Test Todo /remind 14-09-2023");
    }

    @Test
    public void isOccurringOnDate_false() {
        Todo testTodo = new Todo("Test Todo");
        LocalDate testDate1 = LocalDate.parse("2023-08-25");
        LocalDate testDate2 = LocalDate.parse("2099-12-31");
        assertFalse(testTodo.isOccurringOnDate(testDate1));
        assertFalse(testTodo.isOccurringOnDate(testDate2));
    }

    @Test
    public void shouldSendReminder_unchecked() {
        LocalDate pastDate = LocalDate.now().minusDays(7);
        Todo testTodoPast = new Todo("Test Todo with Past Reminder Start Date", pastDate);
        testTodoPast.markNotDone();
        assertTrue(testTodoPast.shouldSendReminder());

        LocalDate today = LocalDate.now();
        Todo testTodoToday = new Todo("Test Todo with Present Reminder Start Date", today);
        testTodoToday.markNotDone();
        assertTrue(testTodoToday.shouldSendReminder());

        LocalDate futureDate = LocalDate.now().plusDays(7);
        Todo testTodoFuture = new Todo("Test Todo with Future Reminder Start Date", futureDate);
        testTodoFuture.markNotDone();
        assertFalse(testTodoFuture.shouldSendReminder());
    }

    @Test
    public void shouldSendReminder_checked() {
        LocalDate pastDate = LocalDate.now().minusDays(7);
        Todo testTodoPast = new Todo("Test Todo with Past Reminder Start Date", pastDate);
        testTodoPast.markDone();
        assertFalse(testTodoPast.shouldSendReminder());

        LocalDate today = LocalDate.now();
        Todo testTodoToday = new Todo("Test Todo with Present Reminder Start Date", today);
        testTodoToday.markDone();
        assertFalse(testTodoToday.shouldSendReminder());

        LocalDate futureDate = LocalDate.now().plusDays(7);
        Todo testTodoFuture = new Todo("Test Todo with Future Reminder Start Date", futureDate);
        testTodoFuture.markDone();
        assertFalse(testTodoFuture.shouldSendReminder());
    }
}
