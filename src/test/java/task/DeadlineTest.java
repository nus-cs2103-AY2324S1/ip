package task;

import Duke.exception.EmptyTaskDescException;
import Duke.exception.InvalidTaskFormatException;
import Duke.task.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void TestDeadlineConstructor() throws EmptyTaskDescException, InvalidTaskFormatException {
        assertEquals(new Deadline("a/a").toString(), "[D][ ] a(a)");
    }

    @Test
    public void TestDeadlineToSaveFormat() throws EmptyTaskDescException, InvalidTaskFormatException {
        assertEquals(new Deadline("a/a").toSaveFormat(), "deadline:a|a|false");
    }
}