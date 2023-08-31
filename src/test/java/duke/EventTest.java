package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventTest {

    @Test
    public void cannotCreateEventWithoutDateTime() {
        assertThrows(DukeException.class, () -> {
            Event event = Event.create("event");
        });
    }

    @Test
    public void cannotCreateEmptyEvent() {
        assertThrows(DukeException.class, () -> {
            Event event = Event.create("");
        });
    }
}

