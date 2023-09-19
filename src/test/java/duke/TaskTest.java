package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void parseTask_stringWithDeadline_correctOutput() {
        assertEquals("[D][ ] return book (by: Oct 15 2020)",
                Task.parseTask("D | 0 | return book | 2020-10-15").toString());
    }

    @Test
    public void parseTask_stringWithEvent_correctOutput() {
        assertEquals("[E][ ] project meeting (from: Aug 6th to: Aug 8th)",
                Task.parseTask("E | 0 | project meeting | Aug 6th | Aug 8th").toString());
    }

    @Test
    public void parseTask_stringWithToDo_correctOutput() {
        assertEquals("[T][ ] read book", Task.parseTask("T | 0 | read book").toString());
    }
}
