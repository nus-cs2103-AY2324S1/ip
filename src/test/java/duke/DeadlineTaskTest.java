package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.DeadlineTask;

public class DeadlineTaskTest {
    @Test
    public void testToString() {
        assertEquals("[D][ ] return book (by: Sep 6 2019)",
                new DeadlineTask(false, "return book", "2019-09-06").toString());
        assertEquals("[D][X] return book (by: Sep 6 2019)",
                new DeadlineTask(true, "return book", "2019-09-06").toString());
    }

    @Test
    public void testMarkAsDone() {
        DeadlineTask deadlineTask = new DeadlineTask(false, "return book", "2019-09-06");
        DeadlineTask deadlineTask2 = new DeadlineTask(true, "return book", "2019-09-06");
        deadlineTask.markAsDone();
        assertEquals(deadlineTask2.toString(), deadlineTask.toString());
    }
}
