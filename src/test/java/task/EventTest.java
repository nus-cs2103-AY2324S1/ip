package task;

import Duke.exception.DukeException;
import Duke.task.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testEventConstructor() throws DukeException {
        assertEquals(new Event("a/from 2020-02-10 10:15/to 2020-02-10 10:20").toString(), "[E][ ] a (from: 2020-02-10,10:15 to: 2020-02-10,10:20)");
    }

    @Test
    public void testEventToSaveFormat() throws DukeException {
        assertEquals(new Event("a/from 2020-02-10 10:15/to 2020-02-10 10:20").toSaveFormat(), "event:2020-02-10 10:15|2020-02-10 10:20|a|false");
    }
}