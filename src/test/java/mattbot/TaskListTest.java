package mattbot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import mattbot.task.Event;
import mattbot.task.Task;


public class TaskListTest {
    private LocalDateTime lDT1 = LocalDateTime.of(2023, 9, 13, 22, 34);
    @Test
    public void taskList_getTask_success() {
        Task t = new Event("Test Event", false, lDT1, lDT1);
        TaskList tL = new TaskList();
        tL.addTask(t);
        assertEquals(tL.getTask(1), t);
    }

    @Test public void taskList_size_success() {
        // Throws DateTimeParseException
        Task t = new Event("Test Event", false, lDT1, lDT1);
        TaskList tL = new TaskList();
        tL.addTask(t);
        assertEquals(tL.size(), 1);
    }
}
