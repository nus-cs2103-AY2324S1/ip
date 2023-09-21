package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
public class TaskTest {
    @Test
    public void todoToString() {
        Todo task = new Todo("read a book");
        assertEquals(task.printTask(), "[T][ ] read a book");

    }

    @Test
    public void deadlineToString() {
        Deadline task = new Deadline("walk", "2002-12-01 12:12");
        assertEquals(task.printTask(), "[D][ ] walk (by: Dec 01 2002 12:12)");
    }

    @Test
    public void eventToString() {
        Event task = new Event("run 2.4km", "2022-12-12 12:00", "2022-12-12 14:00");
        assertEquals(task.printTask(), "[E][ ] run 2.4km (from: Dec 12 2022 12:00 to: 14:00)");
    }
}
