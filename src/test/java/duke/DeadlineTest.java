package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class DeadlineTest {
    @Test
    public void verifyDeadlineTest() {
        Deadline tsk = new Deadline("return book", "2019-10-12 1800");
        assertEquals(tsk.getDeadline(), "Oct 12 2019");
    }

    @Test
    public void completedTest() {
        Deadline tsk = new Deadline("return book", "by Sunday");
        tsk.setCompleted();
        assertEquals(tsk.getCheckbox(), "[X] ");
    }
}
