package seedu.duke;

import duke.DukeException;
import duke.task.EventTask;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class EventTest {

    @Test
    public void mark_success() {
        try {
            EventTask newEvent = new EventTask("test", LocalDate.parse("2020-10-10"), LocalDate.parse("2020-12-10"));
            newEvent.mark();
            assert newEvent.getStatusIcon().equals("\u2713");
        } catch (DukeException e) {
            assert false;
        }
    }

    @Test
    public void unmark_success() {
        try {
            EventTask newEvent = new EventTask("test", LocalDate.parse("2020-10-10"), LocalDate.parse("2020-12-10"));
            newEvent.unmark();
            assert newEvent.getStatusIcon().equals("\u2718");
        } catch (DukeException e) {
            assert false;
        }
    }
}
