package duke;

import duke.task.EventTask;
import duke.task.TaskPriority;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTaskTest {

    @Test
    public void EventTaskTest1() {
        EventTask et = new EventTask("test1", LocalDate.parse("2019-10-15"), false, TaskPriority.LOW);
        assertEquals( "[E][ ][L] test1 (at: Oct 15 2019)", et.toString());
    }

    @Test
    public void EventTaskTest2() {
        EventTask et = new EventTask("test2", LocalDate.parse("2020-12-01"), true, TaskPriority.HIGH);
        assertEquals( "[E][X][H] test2 (at: Dec 01 2020)", et.toString());
    }
}
