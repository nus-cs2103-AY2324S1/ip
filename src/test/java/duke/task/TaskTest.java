package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    void testToWriteStringTodo() {
        ToDo sampleToDo = new ToDo("do econs");
        assertEquals("T | 0 | do econs", sampleToDo.toWriteString());
    }

    @Test
    public void testToWriteStringEvent() {
        Event sampleEvent = new Event("read book", "2020-03-19 1800", "2020-03-20 1800");
        assertEquals("E | 0 | read book | 2020-03-19 1800 | 2020-03-20 1800", sampleEvent.toWriteString());
    }

    @Test
    public void testStatusIcon() {
        Deadline sampleDeadline = new Deadline("homework", "2020-03-19 1800", true);
        assertEquals("X", sampleDeadline.getStatusIcon());
    }
}
