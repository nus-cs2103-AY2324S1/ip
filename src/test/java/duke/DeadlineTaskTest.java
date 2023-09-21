package duke;

import duke.task.DeadlineTask;
import duke.task.TaskPriority;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTaskTest {

    @Test
    public void deadlineTaskTest1() {
        DeadlineTask dl = new DeadlineTask("test1", LocalDate.parse("2019-10-15"), false, TaskPriority.LOW);
        assertEquals( "[D][ ][L] test1 (by: Oct 15 2019)", dl.toString());
    }

    @Test
    public void deadlineTaskTest2() {
        DeadlineTask dl = new DeadlineTask("test2", LocalDate.parse("2020-12-01"), true, TaskPriority.LOW);
        assertEquals( "[D][X][L] test2 (by: Dec 01 2020)", dl.toString());
    }

    @Test
    public void deadlineTaskTest3() {
        DeadlineTask dl = new DeadlineTask("test3", LocalDate.parse("2019-10-15"), false, TaskPriority.MEDIUM);
        assertEquals( "[D][ ][M] test3 (by: Oct 15 2019)", dl.toString());
    }

    @Test
    public void deadlineTaskTest4() {
        DeadlineTask dl = new DeadlineTask("test4", LocalDate.parse("2020-12-01"), true, TaskPriority.MEDIUM);
        assertEquals( "[D][X][M] test4 (by: Dec 01 2020)", dl.toString());
    }

    @Test
    public void deadlineTaskTest5() {
        DeadlineTask dl = new DeadlineTask("test5", LocalDate.parse("2019-10-15"), false, TaskPriority.HIGH);
        assertEquals( "[D][ ][H] test5 (by: Oct 15 2019)", dl.toString());
    }

    @Test
    public void deadlineTaskTest6() {
        DeadlineTask dl = new DeadlineTask("test6", LocalDate.parse("2020-12-01"), true, TaskPriority.HIGH);
        assertEquals( "[D][X][H] test6 (by: Dec 01 2020)", dl.toString());
    }
}
