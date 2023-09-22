package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeadlineTest {

    @Test
    public void create_withouthDateTime_throwsException() {
        assertThrows(DukeException.class, () -> {
            Deadline deadline = Deadline.create("test");
        });
    }

    @Test
    public void create_emptyDeadline_throwsException() {
        assertThrows(DukeException.class, () -> {
            Deadline deadline = Deadline.create("");
        });
    }
}

