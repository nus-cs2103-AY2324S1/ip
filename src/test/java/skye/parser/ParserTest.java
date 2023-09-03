package skye.parser;

import skye.data.exception.DukeException;
import skye.data.exception.DukeExceptionType;
import skye.data.task.Deadline;
import skye.data.task.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    private Parser parser;

    @BeforeEach
    public void init() {
        parser = new Parser();
    }

    @Test
    public void parseArgsAsDeadline_validFormat_success() {
        String args = "Lab Report /by 05-09-2023 23:59";
        Deadline expected =
                new Deadline("Lab Report",
                        LocalDateTime.parse("05-09-2023 23:59", Parser.DATE_TIME_FORMAT));
        try {
            assertEquals(expected.toString(), parser.parseArgsAsDeadline(args).toString());
        } catch (DukeException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void parseArgsAsDeadline_invalidDateTimeFormat_exceptionThrown() {
        String args = "Lab Report /by 5-9-2023 23:59";
        assertThrows(DateTimeParseException.class, () -> parser.parseArgsAsDeadline(args));
    }

    @Test
    public void parseArgsAsEvent_validFormat_success() {
        String args = "Presidential Elections 2023 /from 01-09-2023 08:00 /to 01-09-2023 20:00";
        Event expected =
                new Event("Presidential Elections 2023",
                        LocalDateTime.parse("01-09-2023 08:00", Parser.DATE_TIME_FORMAT),
                        LocalDateTime.parse("01-09-2023 20:00", Parser.DATE_TIME_FORMAT));
        try {
            assertEquals(expected, parser.parseArgsAsEvent(args));
        } catch (DukeException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void parseArgsAsEvent_invalidEventFormat_exceptionThrown() {
        String args = "Career Fair /to 31-08-2023 18:00 /from 29-08-2023: 10:00 ";
        Exception exception = assertThrows(DukeException.class, () -> parser.parseArgsAsEvent(args));
        assertEquals(DukeExceptionType.INVALID_EVENT_FORMAT.getMessage(), exception.getMessage());
    }

}
