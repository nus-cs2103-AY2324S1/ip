package duke.parser;

import duke.data.exception.DukeException;
import duke.data.exception.DukeExceptionType;
import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.task.ToDo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskDecoderTest {
    private TaskDecoder taskDecoder;

    @BeforeEach
    public void init() {
        taskDecoder = new TaskDecoder();
    }

    @Test
    public void decode_toDo_success() {
        String encodedLine = "T | 0 | Hang Laundry";
        Task expected = new ToDo("Hang Laundry");
        try {
            Task actual = taskDecoder.decode(encodedLine);
            assertEquals(expected, actual);
        } catch (DukeException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void decode_deadline_success() {
        String encodedLine = "D | 0 | Budget Proposal | 05-09-2023 23:59";
        try {
            Task expected = new Deadline("Budget Proposal",
                    LocalDateTime.parse("05-09-2023 23:59", Parser.DATE_TIME_FORMAT));
            Task actual = taskDecoder.decode(encodedLine);
            assertEquals(expected, actual);
        } catch (DukeException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void decode_event_success() {
        String encodedLine = "E | 0 | Marathon | 12-09-2023 04:00 | 12-09-2023 12:00";
        try {
            Task expected = new Event("Marathon",
                    LocalDateTime.parse("12-09-2023 04:00", Parser.DATE_TIME_FORMAT),
                    LocalDateTime.parse("12-09-2023 12:00", Parser.DATE_TIME_FORMAT));
            Task actual = taskDecoder.decode(encodedLine);
            assertEquals(expected, actual);
        } catch (DukeException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void decode_unknownTaskType_throwsException() {
        String encodedLine = "deadline false Budget Proposal 05-09-2023 23:59";
        Exception exception = assertThrows(DukeException.class, () -> taskDecoder.decode(encodedLine));
        assertEquals(DukeExceptionType.UNKNOWN_TASK_TYPE.getMessage(), exception.getMessage());
    }
}
