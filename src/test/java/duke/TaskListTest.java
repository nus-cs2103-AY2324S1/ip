package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void adderTest() {
        TaskList taskListTest = new TaskList();
        Todo todo = new Todo("CS2103T homework");
        LocalDateTime date = LocalDateTime.parse("2023-12-12T12:00");
        LocalDateTime start = LocalDateTime.parse("2024-01-01T12:00");
        LocalDateTime end = LocalDateTime.parse("2024-01-01T14:00");
        Deadline deadline = new Deadline("CS2100 Quiz", date);
        Event event = new Event("CS2101 Presentation", start, end );
        taskListTest.add(todo);
        taskListTest.add(deadline);
        taskListTest.add(event);
        assertEquals(taskListTest.get(0), todo);
        assertEquals(taskListTest.get(1), deadline);
        assertEquals(taskListTest.get(2), event);
    }
    @Test
    public void deleteTest() {
        TaskList taskListTest = new TaskList();
        Todo todo = new Todo("CS2103T homework");
        taskListTest.add(todo);
        assertEquals(true, taskListTest.contains(todo));
        taskListTest.remove(todo);
        assertEquals(false, taskListTest.contains(todo));
    }
}
