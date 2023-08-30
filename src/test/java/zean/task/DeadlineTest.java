package zean.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import zean.exception.DukeException;

public class DeadlineTest {
    @Test
    public void invalidDateTest1() {
        Exception exception = assertThrows(DukeException.class, () ->
                new Deadline("invalid date", "2023-02-29"));
        assertEquals("\tThe date is invalid!", exception.getMessage());
    }

    @Test
    public void invalidDateTest2() {
        Exception exception = assertThrows(DukeException.class, () ->
                new Deadline("invalid date", "2023-13-29"));
        assertEquals("\tThe date is invalid!", exception.getMessage());
    }

    @Test
    public void validDeadlineFormatTest1() {
        Deadline deadline = new Deadline("test", "2023-09-29");
        assertEquals("Sep 29 2023", deadline.getDeadline());
    }

    @Test
    public void validDeadlineFormatTest2() {
        Deadline deadline = new Deadline("test", "2024-02-29");
        assertEquals("Feb 29 2024", deadline.getDeadline());
    }
}
