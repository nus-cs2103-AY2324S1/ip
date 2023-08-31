package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeadlineTest {

    @Test
    public void cannotCreateDeadlineWithoutDateTime() {
        assertThrows(DukeException.class, () -> {
            Deadline deadline = Deadline.create("test");
        });
    }

    @Test
    public void cannotCreateEmptyDeadline() {
        assertThrows(DukeException.class, () -> {
            Deadline deadline = Deadline.create("");
        });
    }
}

