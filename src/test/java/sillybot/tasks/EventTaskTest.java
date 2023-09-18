package sillybot.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTaskTest {
    @Test
    void eventTask_encodesCorrectly_1() {
        assertEquals("[E] [ ] project meeting (from: Jun 08 2033 to: Dec 08 2234)",
                new EventTask("project meeting", "2033-06-08", "2234-12-08").toString());
    }

    @Test
    void eventTask_encodesCorrectly_2() {
        assertEquals("[E] [ ] attend Google Developers meeting (from: Dec 09 2008 to: Dec 10 2008)",
                new EventTask("attend Google Developers meeting", "2008-12-09", "2008-12-10").toString());
    }
}
