package sana;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void formatTask_todoTask_formattedTodoTaskReturned() {
        Task task = new Todo("borrow book", false);
        String actual = task.formatTask();
        String expected = "T | 0 | borrow book";
        assertEquals(expected, actual);
    }

    @Test
    public void formatTask_deadlineTask_formattedDeadlineTaskReturned() {
        Task task = new Deadline("read book", LocalDate.parse("2023-12-30"), true);
        String actual = task.formatTask();
        String expected = "D | 1 | read book | 2023-12-30";
        assertEquals(expected, actual);
    }

    @Test
    public void formatTask_eventTask_formattedEventTaskReturned() {
        Task task = new Event("return book", LocalDate.parse("2023-12-30"), LocalDate.parse("2023-12-31"), true);
        String actual = task.formatTask();
        String expected = "E | 1 | return book | 2023-12-30 | 2023-12-31";
        assertEquals(expected, actual);
    }
}
