package alyssa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

/**
 * This class tests the method(s) in the Deadline class.
 */
public class DeadlineTest {

    /**
     * Tests if getBy works correctly. eg 2023-08-30 is stored as LocalDate, and shown as Aug 30 2023.
     */
    @Test
    public void getBy_success() {
        Deadline deadline = new Deadline("Finish CS2103T iP", LocalDate.parse("2023-08-30"));
        assertEquals("Aug 30 2023", deadline.getBy());
    }
}
