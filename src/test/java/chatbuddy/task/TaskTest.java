package chatbuddy.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class TaskTest {
    private Task todo = new ToDo("read book");
    private Task deadline = new Deadline("return book",
            LocalDate.parse("23/09/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy"))
    );
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private Task event = new Event(
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
