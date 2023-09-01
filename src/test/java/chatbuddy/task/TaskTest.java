package chatbuddy.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    Task todo = new ToDo("read book");
    Task deadline = new Deadline("return book",
            LocalDate.parse("23/09/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy"))
    );
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    Task event = new Event(
            "go out",
            LocalDateTime.parse("01/09/2023 1000", dateTimeFormatter),
            LocalDateTime.parse("01/09/2023 1732", dateTimeFormatter)
    );

    @Test
    public void markAsDone() {
        todo.markAsDone();
        deadline.markAsDone();
        event.markAsDone();

        assertEquals(true, todo.isDone);
        assertEquals(true, deadline.isDone);
        assertEquals(true, event.isDone);
    }

    @Test
    public void markAsNotDone() {
        todo.markAsNotDone();
        deadline.markAsNotDone();
        event.markAsNotDone();

        assertEquals(false, todo.isDone);
        assertEquals(false, deadline.isDone);
        assertEquals(false, event.isDone);
    }
}
