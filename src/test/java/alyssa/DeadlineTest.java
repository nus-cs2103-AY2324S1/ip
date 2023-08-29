package alyssa;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class tests the method(s) in the Deadline class.
 */
public class DeadlineTest {

    @Test
    public void getBy_success() {
        Deadline deadline = new Deadline("Finish CS2103T iP", LocalDate.parse("2023-08-30"));
        assertEquals("Aug 30 2023", deadline.getBy());
    }
}
