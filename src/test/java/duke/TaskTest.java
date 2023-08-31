package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void toTaskListEntry_todoUndone_correctEntry() {
        Task t = new ToDo("read book");
        assertEquals("T | 0 | read book", t.toTaskListEntry());
    }

    @Test
    public void toTaskListEntry_todoDone_correctEntry() {
        Task t = new ToDo("go for a run");
        t.markAsDone();
        assertEquals("T | 1 | go for a run", t.toTaskListEntry());
    }

    @Test
    public void toTaskListEntry_deadlineUndone_correctEntry() {
        Task t = new Deadline("CS2100 lecture", "Aug 31 2023");
        assertEquals("D | 0 | CS2100 lecture | Aug 31 2023", t.toTaskListEntry());
    }

    @Test
    public void toTaskListEntry_deadlineDone_correctEntry() {
        Task t = new Deadline("CS2100 lecture", "Aug 31 2023");
        t.markAsDone();
        assertEquals("D | 1 | CS2100 lecture | Aug 31 2023", t.toTaskListEntry());
    }

    @Test
    public void toTaskListEntry_eventUndone_correctEntry() {
        Task t = new Event("dinner", "Aug 31 6pm", "Aug 31 8pm");
        assertEquals("E | 0 | dinner | Aug 31 6pm | Aug 31 8pm", t.toTaskListEntry());
    }

    @Test
    public void toTaskListEntry_eventDone_correctEntry() {
        Task t = new Event("dinner", "Aug 31 6pm", "Aug 31 8pm");
        t.markAsDone();
        assertEquals("E | 1 | dinner | Aug 31 6pm | Aug 31 8pm", t.toTaskListEntry());
    }
}
