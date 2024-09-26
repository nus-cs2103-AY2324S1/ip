package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void verifyTimeTest() {
        Event tsk = new Event("taylor swift concert", "12pm", "6pm");
        assertEquals(tsk.getDetails(), "(from: 12pm to: 6pm)");
    }

    @Test
    public void completedTest() {
        Event tsk = new Event("taylor swift concert", "12pm", "6pm");
        tsk.setCompleted();
        assertEquals(tsk.getCheckbox(), "[X] ");
    }
}
