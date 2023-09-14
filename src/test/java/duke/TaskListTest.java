package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import command.InvalidCommandException;

public class TaskListTest {
    private static final String DATETIME_INPUT_FORMAT = "yyyy-MM-dd HH:mm";
    public static final DateTimeFormatter DATE_TIME_INPUT_FORMATTER =
            DateTimeFormatter.ofPattern(DATETIME_INPUT_FORMAT);
    @Test
    public void remove_indexWithinRange_success() throws Exception {
        TaskList tasks = new TaskList();

        Deadline deadline = new Deadline("chemistry homework",
                LocalDateTime.parse("2023-08-15 18:00", DATE_TIME_INPUT_FORMATTER));
        ToDo todo = new ToDo("read book");
        Event event = new Event("marathon", LocalDateTime.parse("2023-08-16 05:00", DATE_TIME_INPUT_FORMATTER),
                LocalDateTime.parse("2023-08-16 07:00", DATE_TIME_INPUT_FORMATTER));

        tasks.add(deadline);
        tasks.add(todo);
        tasks.add(event);

        assertEquals(todo, tasks.remove(1));

        assertEquals(deadline, tasks.remove(0));

        assertEquals(event, tasks.remove(0));
    }

    @Test
    public void remove_indexOutOfRange_exceptionThrown() {
        TaskList tasks = new TaskList();

        Deadline deadline = new Deadline("chemistry homework",
                LocalDateTime.parse("2023-08-15 18:00", DATE_TIME_INPUT_FORMATTER));
        ToDo todo = new ToDo("read book");
        Event event = new Event("marathon", LocalDateTime.parse("2023-08-16 05:00", DATE_TIME_INPUT_FORMATTER),
                LocalDateTime.parse("2023-08-16 07:00", DATE_TIME_INPUT_FORMATTER));

        tasks.add(deadline);
        tasks.add(todo);
        tasks.add(event);

        try {
            assertEquals(todo, tasks.remove(5));
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("☹ OOPS!!! The task index in invalid", e.getMessage());
        }

        try {
            assertEquals(todo, tasks.remove(3));
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("☹ OOPS!!! The task index in invalid", e.getMessage());
        }

        try {
            assertEquals(todo, tasks.remove(-1));
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("☹ OOPS!!! The task index in invalid", e.getMessage());
        }
    }
}
