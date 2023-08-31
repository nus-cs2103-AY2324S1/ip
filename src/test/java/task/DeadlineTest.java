package task;

import Duke.exception.DukeException;
import Duke.task.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void TestDeadlineConstructor() throws DukeException {
        assertEquals(new Deadline("a/by 2020-02-10 10:15").toString(), "[D][ ] a (by: 2020-02-10,10:15)");
    }

    @Test
    public void TestDeadlineToSaveFormat() throws DukeException {
        assertEquals(new Deadline("a/by 2020-02-10 10:15").toSaveFormat(), "deadline:2020-02-10,10:15|a|false");
    }
}