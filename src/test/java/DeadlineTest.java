import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Deadline;

public class DeadlineTest {

    @Test
    public void testStringConversion() {
        assertEquals("[D][ ] Complete iP (by: Sep 05 2023 2:00PM)", new Deadline("Complete iP", "2023-09-05 1400").toString());
    }
}