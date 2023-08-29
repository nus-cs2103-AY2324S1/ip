package seedu.duke;

import duke.DukeException;
import duke.task.DeadlineTask;
import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void deadline_create_success() throws DukeException {
        DeadlineTask newDeadline = new DeadlineTask("test", "2020-10-10");
        assert newDeadline.getDescription().equals("test");
        assert newDeadline.getBy().equals("Oct 10 2020");
    }

    @Test
    public void deadline_wrongDateFormat_exceptionThrown() {
        try {
            DeadlineTask newDeadline = new DeadlineTask("test", "2020-10-10 10:10:10");
            assert false;
        } catch (DukeException e) {
            assert e.getMessage().equals("Please enter a valid date in the format: yyyy-mm-dd");
        }
    }
}
