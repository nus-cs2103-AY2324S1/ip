package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
public class TaskTest {

    @Test
    public void toString_correctInput() {
        assertEquals("[T][ ] test", new TodoTask("test").toString());
        assertEquals("[T][X] test1",
                new TodoTask("test1", true).toString());
        assertEquals("[D][ ] test2 (by: Sun 11:59PM, Oct 2023)",
                new DeadlineTask(LocalDateTime.parse("2023-10-01T23:59"), "test2").toString());
        assertEquals("[E][X] test3 (from: Sun 03:03AM, May 2023 to: Sun 03:05AM, May 2023)",
                new EventTask(LocalDateTime.parse("2023-05-07T03:03"),
                        LocalDateTime.parse("2023-05-07T03:05"),
                        "test3", true).toString());
    }

    @Test
    public void getStored_correctInput() {
        assertEquals("TODO#test#0", new TodoTask("test").getStored());
        assertEquals("TODO#test1#1",
                new TodoTask("test1", true).getStored());
        assertEquals("DEADLINE#test2#0#2023-10-01T23:59",
                new DeadlineTask(LocalDateTime.parse("2023-10-01T23:59"), "test2").getStored());
        assertEquals("EVENT#test3#1#2023-05-07T03:03#2023-05-07T03:05",
                new EventTask(LocalDateTime.parse("2023-05-07T03:03"),
                        LocalDateTime.parse("2023-05-07T03:05"),
                        "test3", true).getStored());
    }
}
