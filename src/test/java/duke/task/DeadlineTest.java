package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    void testGetDueDate() {
        Deadline deadline = new Deadline("test deadline getting date", LocalDate.parse("2023-08-30"));
        assertEquals(deadline.getDueDate(), LocalDate.parse("2023-08-30"));
    }

    @Test
    void testToString() {
        Deadline deadline = new Deadline("test deadline", LocalDate.parse("2023-08-30"));
        assertEquals(deadline.toString(), "[D][ ] test deadline (by: Aug 30 2023)");
    }

//    @Test
//    void handleDeadlineTask_excessInputs_exceptionThrown() {
//
//    }

//    @Test
//    void handleDeadlineTask_invalidDateInput_exceptionThrown() {
//
//    }
}
