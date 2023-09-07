package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    void eventTask_encodesCorrectly() throws Exception {
        assertEquals("|E| | | project meeting (from: Jun 08 2033 to: Dec 08 2234)",
                new Event("project meeting", "2033-06-08", "2234-12-08").toString());
    }
}
