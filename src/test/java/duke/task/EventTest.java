package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void stringToFile_fullFormat_success() {
        assertEquals("E | 0 | Guitar Practice | 2pm | 6pm",
                new Event("Guitar Practice", "2pm", "6pm")
                        .stringToFile());

        assertEquals("E | 0 | Movie Activity | 7pm | 11pm",
                new Event("Movie Activity", "7pm", "11pm")
                        .stringToFile());
    }

    @Test
    public void testStringConversion() {
        assertEquals("[E][ ] Hackathon (from: 1/9 to: 3/9)",
                new Event("Hackathon", "1/9", "3/9")
                        .toString());
    }
}
