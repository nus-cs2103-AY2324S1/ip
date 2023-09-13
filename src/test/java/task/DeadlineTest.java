package task;

import duke.exception.DukeException;
import duke.task.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testDeadlineConstructor() throws DukeException {
        assertEquals(new Deadline("a/by 2020-02-10 10:15").toString(), "[D][ ] a (by: 2020-02-10,10:15)");
    }

    @Test
    public void testDeadlineToSaveFormat() throws DukeException {
        assertEquals(new Deadline("a/by 2020-02-10 10:15").toSaveFormat(), "deadline:2020-02-10,10:15|a|false");
    }
}