package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testString() {
        assertEquals("[E][ ] Read book (from: abc to: abc)", new Event("Read book", "abc", "abc").toString());
    }

}
