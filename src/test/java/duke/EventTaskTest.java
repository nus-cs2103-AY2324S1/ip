package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.EventTask;

public class EventTaskTest {
    @Test
    public void testToString() {
        assertEquals("[E][ ] return book (from: Sep 6 2019 to: Sep 6 2019)",
                new EventTask(false, "return book", "2019-09-06", "2019-09-06").toString());
        assertEquals("[E][X] return book (from: Sep 6 2019 to: Sep 6 2019)",
                new EventTask(true, "return book", "2019-09-06", "2019-09-06").toString());
    }

    @Test
    public void testMarkAsDone() {
        EventTask eventTask = new EventTask(false, "return book", "2019-09-06", "2019-09-06");
        EventTask eventTask2 = new EventTask(true, "return book", "2019-09-06", "2019-09-06");
        eventTask.markAsDone();
        assertEquals(eventTask2.toString(), eventTask.toString());
    }
}
