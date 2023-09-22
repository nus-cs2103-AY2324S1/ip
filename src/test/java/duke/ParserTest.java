package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;

/**
 * Tests the {@code Parser} class.
 */
public class ParserTest {
    /**
     * Tests whether the {@code parseDate} appropriately parses all allowed date time formats.
     */
    @Test
    public void parseDate() {
        LocalDateTime dateTimeMidnight = LocalDateTime.of(2023, 9, 5, 0, 0);
        LocalDateTime dateTimeNoon = LocalDateTime.of(2023, 9, 5, 12, 0);

        assertEquals(dateTimeMidnight, Parser.parseDate("5/9/2023"));
        assertEquals(dateTimeMidnight, Parser.parseDate("5/09/2023"));
        assertEquals(dateTimeMidnight, Parser.parseDate("05/9/2023"));
        assertEquals(dateTimeMidnight, Parser.parseDate("05/09/2023"));
        assertEquals(dateTimeMidnight, Parser.parseDate("2023-9-5"));
        assertEquals(dateTimeMidnight, Parser.parseDate("2023-9-05"));
        assertEquals(dateTimeMidnight, Parser.parseDate("2023-09-5"));
        assertEquals(dateTimeMidnight, Parser.parseDate("2023-09-05"));

        assertEquals(dateTimeNoon, Parser.parseDate("5/9/2023 1200"));
        assertEquals(dateTimeNoon, Parser.parseDate("5/09/2023 1200"));
        assertEquals(dateTimeNoon, Parser.parseDate("05/9/2023 1200"));
        assertEquals(dateTimeNoon, Parser.parseDate("05/09/2023 1200"));
        assertEquals(dateTimeNoon, Parser.parseDate("2023-9-5 1200"));
        assertEquals(dateTimeNoon, Parser.parseDate("2023-9-05 1200"));
        assertEquals(dateTimeNoon, Parser.parseDate("2023-09-5 1200"));
        assertEquals(dateTimeNoon, Parser.parseDate("2023-09-05 1200"));
    }

    /**
     * Tests whether the {@code createTask} appropriately parses user input when creating tasks.
     */
    @Test
    public void createTask() {
        ToDo expectedTodo = new ToDo("Re-watch CS2103T lecture");
        Deadline expectedDeadline = new Deadline("Finish CS2103T quiz",
                LocalDateTime.of(2023, 9, 30, 12, 0));
        Event expectedEvent1 = new Event("CS2103T project meeting", LocalDateTime.of(2023,
                9, 30, 14, 30), LocalDateTime.of(2023, 9, 30, 16, 30));
        Event expectedEvent2 = new Event("Level supper", LocalDateTime.of(2023,
                9, 30, 0, 0), LocalDateTime.of(2023, 9, 30, 0, 30));
        assertEquals(expectedTodo, Parser.createTask(ToDo.TASK_TYPE, "Re-watch CS2103T lecture"));
        assertEquals(expectedDeadline, Parser.createTask(Deadline.TASK_TYPE, "Finish CS2103T quiz"
                + " /by 2023-9-30 1200"));
        assertEquals(expectedEvent1, Parser.createTask(Event.TASK_TYPE, "CS2103T project "
                + "meeting /from 2023-09-30 1430 /to 2023-9-30 " + "1630"));
        assertEquals(expectedEvent2, Parser.createTask(Event.TASK_TYPE, "Level supper /from "
                + "2023-9-30 0000 /to 2023-09-30 0030"));
    }

    /**
     * Tests whether the {@code createTask} appropriately parses tasks from the storage file.
     */
    @Test
    public void parse() {
        ToDo expectedTodo = new ToDo("Re-watch CS2103T lecture");
        Deadline expectedDeadline = new Deadline("Finish CS2103T quiz", true,
                LocalDateTime.of(2023, 9, 30, 12, 0));
        Event expectedEvent1 = new Event("CS2103T project meeting", true, LocalDateTime.of(2023,
                9, 30, 14, 30), LocalDateTime.of(2023, 9, 30, 16, 30));
        Event expectedEvent2 = new Event("Level supper", LocalDateTime.of(2023,
                9, 30, 0, 0), LocalDateTime.of(2023, 9, 30, 0, 30));
        assertEquals(expectedTodo, Parser.parse(ToDo.TASK_CODE, "0 | Re-watch CS2103T lecture"));
        assertEquals(expectedDeadline, Parser.parse(Deadline.TASK_CODE, "1 | Finish CS2103T quiz "
                + "| 2023-09-30 1200"));
        assertEquals(expectedEvent1, Parser.parse(Event.TASK_CODE, "1 | CS2103T project meeting |"
                + " 2023-09-30 1430-2023-09-30 " + "1630"));
        assertEquals(expectedEvent2, Parser.parse(Event.TASK_CODE, "0 | Level supper | 2023-09-30"
                + " 0000-2023-09-30 0030"));
    }

    /**
     * Tests whether the {@code parseDate} appropriately throws exception for invalid formats.
     */
    @Test
    public void parseDate_invalidFormat_exceptionThrown() {
        String dateTimeString = "2023-09-05, 1200";
        LocalDateTime dateTimeNoon = LocalDateTime.of(2023, 9, 5, 12, 0);

        try {
            assertEquals(dateTimeNoon, Parser.parseDate(dateTimeString));
            fail();
        } catch (DateTimeParseException e) {
            assertEquals(String.format(Messages.INVALID_DATE_TIME_FORMAT, dateTimeString),
                    e.getMessage());
        }
    }
}
