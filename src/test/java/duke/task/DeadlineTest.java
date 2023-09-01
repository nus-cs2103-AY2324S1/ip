package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void newDeadline_success() {
        // a new Deadline is created correctly.
        Deadline deadline = new Deadline("something", "1111-11-11", false);
        assertEquals("[D][ ] something By: 1111-11-11", deadline.toString());
    }

    @Test
    public void showAltForm_stringRep_success() {
        Deadline deadline = new Deadline("something", "1111-11-11", false);
        assertEquals("[D][ ] something By: Nov 11 1111", deadline.displayableForm());
    }

    @Test
    public void toggleDoneStatus_success() {
        // the done status of a deadline can be toggled successfully.
        Deadline deadline = new Deadline("something", "1111-11-11", false);
        deadline.setDone();
        assertEquals("[D][X] something By: 1111-11-11", deadline.toString());
        deadline.setNotDone();
        assertEquals("[D][ ] something By: 1111-11-11", deadline.toString());
    }
}
