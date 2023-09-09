package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;

public class DeadlinesTest {

    @Test
    public void stringRepresentation_validDayDeadline_repTest() {
        Deadline task = new Deadline("Testname1", "Tuesday");
        assertEquals("[D][ ] Testname1 (by: Tuesday)", task.toString());
    }

    @Test
    public void stringRepresentation_validDateDeadline_repTest() {
        Deadline task = new Deadline("Testname1", "2/12/2019 1800");
        assertEquals("[D][ ] Testname1 (by: 2 Dec 2019)", task.toString());
    }
}
