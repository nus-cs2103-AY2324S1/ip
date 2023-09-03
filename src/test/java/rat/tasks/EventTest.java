package rat.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void testEvent_create() {
        Event eTest = new Event("01/01/2023 00:00", "01/01/2023 01:00", "test");
        assertEquals("[E][ ] test (from: Sun, 1 Jan 2023 00:00 to: Sun, 1 Jan 2023 01:00)", eTest.toString());
    }

    @Test
    public void testEvent_formatForFile() {
        Event eTest = new Event("01/01/2023 00:00", "01/01/2023 01:00", "test");
        assertEquals("E, 0, test, 2023-01-01T00:00, 2023-01-01T01:00", eTest.formatForFile());
    }

    @Test
    public void testEvent_markDone() {
        Event eTest = new Event("01/01/2023 00:00", "01/01/2023 01:00", "test");
        eTest.markDone();
        assertEquals("[E][X] test (from: Sun, 1 Jan 2023 00:00 to: Sun, 1 Jan 2023 01:00)", eTest.toString());
    }

    @Test
    public void testEvent_unmarkDone() {
        Event eTest = new Event("01/01/2023 00:00", "01/01/2023 01:00", "test");
        eTest.markDone();
        eTest.unmarkDone();
        assertEquals("[E][ ] test (from: Sun, 1 Jan 2023 00:00 to: Sun, 1 Jan 2023 01:00)", eTest.toString());
    }
}
