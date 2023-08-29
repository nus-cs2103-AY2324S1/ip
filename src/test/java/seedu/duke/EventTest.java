package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {

    @Test
    public void testToStringForSave() {
        assertEquals("E | 0 | NUS GreyHats | 19/09/2023 1800 | 21/09/2023 1800",
                new Event("NUS GreyHats", "19/09/2023 1800", "21/09/2023 1800").toStringForSave());
    }

    @Test
    public void testToString() {
        assertEquals("[E][ ] NUS GreyHats (from: 2023-Sep-19 18:00 to: 2023-Sep-21 18:00)",
                new Event("NUS GreyHats", "19/09/2023 1800", "21/09/2023 1800").toString());
    }
}
