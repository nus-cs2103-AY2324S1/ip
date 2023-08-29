package seedu.duke;

import duke.DukeException;
import duke.task.EventTask;
import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void event_create_success() throws DukeException {
        EventTask newEvent = new EventTask("test", "2020-10-10", "2020-12-10");
        assert newEvent.getDescription().equals("test");
        assert newEvent.getFrom().equals("Oct 10 2020");
        assert newEvent.getTo().equals("Dec 10 2020");
    }

    @Test
    public void event_wrongDateFormat_exceptionThrown() {
        try {
            EventTask newEvent = new EventTask("test", "2020-13-10", "2020-14-10");
            assert false;
        } catch (DukeException e) {
            assert e.getMessage().equals("Please enter a valid date in the format: yyyy-mm-dd");
        }
    }
}
