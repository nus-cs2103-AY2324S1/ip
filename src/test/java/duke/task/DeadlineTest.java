package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void toString_correctFormat() {
        Deadline deadline = new Deadline("Read a book", "2023-09-19 1800");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
        String expectedOutput = "[D][ ] Read a book (by: Sep 19 2023, 6:00PM)";
        assertEquals(expectedOutput, deadline.toString());
    }

    @Test
    public void toFileString_correctFormat() {
        Deadline deadline = new Deadline("Read a book", "2023-09-19 1800");
        String expectedOutput = "D | 0 | Read a book | 2023-09-19 1800";
        assertEquals(expectedOutput, deadline.toFileString());
    }

    @Test
    public void getDueDate_correctDate() {
        Deadline deadline = new Deadline("Read a book", "2023-09-19 1800");
        LocalDateTime expectedDate = LocalDateTime.parse("2023-09-19T18:00");
        assertEquals(expectedDate, deadline.getDueDate());
    }
}
