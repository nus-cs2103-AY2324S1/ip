package duke;

import duke.task.EventTask;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTaskTest {

    @Test
    public void EventTaskTest1() {
        EventTask et = new EventTask("test1", LocalDate.parse("2019-10-15"), false);
        assertEquals( "[E][ ] test1 (at: Oct 15 2019)", et.toString());
    }

    @Test
    public void EventTaskTest2() {
        EventTask et = new EventTask("test2", LocalDate.parse("2020-12-01"), true);
        assertEquals( "[E][X] test2 (at: Dec 01 2020)", et.toString());
    }
}
