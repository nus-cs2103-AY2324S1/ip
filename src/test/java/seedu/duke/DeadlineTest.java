package seedu.duke;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.task.EventTask;

public class DeadlineTest {
    @Test
    public void mark_success() {
        EventTask newEvent = new EventTask("test", LocalDate.parse("2020-10-10"), LocalDate.parse("2020-12-10"));
        newEvent.mark();
        assert newEvent.getStatusIcon().equals("\u2713");
    }

    @Test
    public void unmark_success() {
        EventTask newEvent = new EventTask("test", LocalDate.parse("2020-10-10"), LocalDate.parse("2020-12-10"));
        newEvent.unmark();
        assert newEvent.getStatusIcon().equals("\u2718");
    }
}
