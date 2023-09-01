package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class DeadlineTest {

    @Test
    public void testToStringForSave() {
        assertEquals("D | 0 | submit lab | 19/08/2023 1900",
                new Deadline("submit lab", "19/08/2023 1900").toStringForSave());
    }

    @Test
    public void testToString() {
        assertEquals("[D][ ] submit lab (by: 2023-Aug-19 19:00)",
                new Deadline("submit lab", "19/08/2023 1900").toString());
    }
}
