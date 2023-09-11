package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class DeadlineTest {

    @Test
    public void toStorageString_stringRepresentation_success() {
        Deadline deadline = new Deadline("read book", "23 Aug 2020 2359");
        assertEquals("D | 0 | read book | 23 Aug 2020 2359", deadline.toStorageString());
    }

    @Test
    public void toString_stringRepresentation_success() {
        Deadline deadline = new Deadline("read book", "23 Aug 2020 2359");
        assertEquals("[D][ ] read book (by: 23-59-2020 23:59)", deadline.toString());
    }

    @Test
    public void createDeadlineFromCommand_createDeadlineObject_success() throws Exception {
        Deadline deadline = Deadline.createDeadlineFromCommand("deadline read book /by 23 Aug 2020 2359");
        assertEquals("[D][ ] read book (by: 23-59-2020 23:59)", deadline.toString());
    }

    @Test
    public void createDeadlineFromCommand_illegalArgument_exceptionThrown() {
        try {
            Deadline deadline = Deadline.createDeadlineFromCommand("deadline");
        } catch (Exception e) {
            assertEquals("The description of a deadline cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void createDeadlineFromCommand_illegalArgument_exceptionThrown2() {
        try {
            Deadline deadline = Deadline.createDeadlineFromCommand("deadline read book");
        } catch (Exception e) {
            assertEquals("The deadline command must contain a /by.", e.getMessage());
        }
    }

    @Test
    public void createDeadlineFromCommand_illegalArgument_exceptionThrown3() {
        try {
            Deadline deadline = Deadline.createDeadlineFromCommand("deadline read book /by ");
        } catch (Exception e) {
            assertEquals("The deadline command must contain a description after /by.", e.getMessage());
        }
    }

    @Test
    public void createDeadlineFromStorage_createDeadlineObject_success() {
        Deadline deadline = Deadline.createDeadlineFromStorage("D | 0 | read book | 23 Aug 2020 2359");
        assertEquals("[D][ ] read book (by: 23-59-2020 23:59)", deadline.toString());
    }

    @Test
    public void createDeadlineFromStorage_createDoneDeadlineObject_success() {
        Deadline deadline = Deadline.createDeadlineFromStorage("D | 1 | read book | 23 Aug 2020 2359");
        assertEquals("[D][X] read book (by: 23-59-2020 23:59)", deadline.toString());
    }

}
