package alyssa;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
