package duke;

import static duke.Storage.storageToTask;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.junit.jupiter.api.Test;

/**
 * Tests Storage class.
 */
public class StorageTest {

    /**
     * Tests storageToTask function with a ToDo not done.
     */
    @Test
    public void storageToTask_toDoNotDone() {
        String input = "T | 0 | test";
        ToDo expected = new ToDo("test", false);
        assertEquals(expected.toString(), storageToTask(input).toString());
    }

    /**
     * Tests storageToTask function with a ToDo done.
     */
    @Test
    public void storageToTask_toDoDone() {
        String input = "T | 1 | test";
        ToDo expected = new ToDo("test", true);
        assertEquals(expected.toString(), storageToTask(input).toString());
    }

    /**
     * Tests storageToTask function with a Deadline not done.
     */
    @Test
    public void storageToTask_deadlineNotDone() {
        String d = "2022-02-02";
        LocalDate date = LocalDate.parse(d);
        String input = "D | 0 | test | "
                + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH));
        Deadline expected = new Deadline("test", false, date);
        assertEquals(expected.toString(), storageToTask(input).toString());
    }

    /**
     * Tests storageToTask function with a Deadline done.
     */
    @Test
    public void storageToTask_deadlineDone() {
        String d = "2022-02-02";
        LocalDate date = LocalDate.parse(d);
        String input = "D | 1 | test | "
                + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH));
        Deadline expected = new Deadline("test", true, date);
        assertEquals(expected.toString(), storageToTask(input).toString());
    }

    /**
     * Tests storageToTask function with an Event not done.
     */
    @Test
    public void storageToTask_eventNotDone() {
        String s = "2022-02-02";
        LocalDate start = LocalDate.parse(s);
        String e = "2023-02-02";
        LocalDate end = LocalDate.parse(s);
        String input = "E | 0 | test | "
                + start.format(DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH))
                + " | "
                + end.format(DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH));
        Event expected = new Event("test", false, start, end);
        assertEquals(expected.toString(), storageToTask(input).toString());
    }

    /**
     * Tests storageToTask function with an Event done.
     */
    @Test
    public void storageToTask_eventDone() {
        String s = "2022-02-02";
        LocalDate start = LocalDate.parse(s);
        String e = "2023-02-02";
        LocalDate end = LocalDate.parse(s);
        String input = "E | 1 | test | "
                + start.format(DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH))
                + " | "
                + end.format(DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH));
        Event expected = new Event("test", true, start, end);
        assertEquals(expected.toString(), storageToTask(input).toString());
    }

    /**
     * Tests storageToTask function with a FixedDurationTask not done.
     */
    @Test
    public void storageToTask_fixedDurationTaskNotDone() {
        String d = "one hour";
        String input = "F | 0 | test | " + d;
        FixedDurationTask expected = new FixedDurationTask("test", false, d);
        assertEquals(expected.toString(), storageToTask(input).toString());
    }

    /**
     * Tests storageToTask function with a FixedDurationTask done.
     */
    @Test
    public void storageToTask_fixedDurationTaskDone() {
        String d = "one hour";
        String input = "F | 1 | test | " + d;
        FixedDurationTask expected = new FixedDurationTask("test", true, d);
        assertEquals(expected.toString(), storageToTask(input).toString());
    }
}
