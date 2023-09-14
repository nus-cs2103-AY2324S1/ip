package ducky.task;

import com.ducky.task.EventTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTaskTest {

    EventTask eventTask1 = new EventTask("test1", "now", "later");
    EventTask eventTask2 = new EventTask("test2", "tomorrow", "never");

    @BeforeEach
    public void setEventTask2ToCompleted() {
        eventTask2.setComplete();
    }


    @Test
    public void eventTask_toString_correctResultReturned() {
        assertEquals(
                eventTask1.toString(),
                "[E][ ] test1 (from: now to: later)"
        );
        assertEquals(
                eventTask2.toString(),
                "[E][X] test2 (from: tomorrow to: never)"
        );
    }

    @Test
    public void eventTask_getSaveFormat_correctResultReturned() {
        assertEquals(
                eventTask1.getSaveFormat(),
                "E | 0 | test1 | now | later"
        );
        assertEquals(
                eventTask2.getSaveFormat(),
                "E | 1 | test2 | tomorrow | never"
        );
    }
}
