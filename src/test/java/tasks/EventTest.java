import org.junit.jupiter.api.Test;
import tasks.Event;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void toStringTest() {
        Event test = new Event("test123", "May 2020", "July 2021");
        assertEquals("[E][ ] test123 (from: May 2020 to: July 2021)", test.toString());
    }
}