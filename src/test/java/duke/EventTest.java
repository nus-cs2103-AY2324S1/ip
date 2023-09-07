package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tasks.Event;

public class EventTest {
    @Test
    public void eventTest() {
        Event dl = new Event("test", "2019-10-15", "2019-10-16");
        assertEquals("Oct 15 2019 to Oct 16 2019", dl.getFromTo());
    }
}
