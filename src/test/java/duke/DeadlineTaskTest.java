package duke;

import duke.task.DeadlineTask;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTaskTest {

    @Test
    public void deadlineTaskTest1() {
        DeadlineTask dl = new DeadlineTask("test1", LocalDate.parse("2019-10-15"), false);
        assertEquals( "[D][ ] test1 (by: Oct 15 2019)", dl.toString());
    }

    @Test
    public void deadlineTaskTest2() {
        DeadlineTask dl = new DeadlineTask("test2", LocalDate.parse("2020-12-01"), true);
        assertEquals( "[D][X] test2 (by: Dec 01 2020)", dl.toString());
    }
}
