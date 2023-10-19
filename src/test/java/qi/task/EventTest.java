package qi.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testStringDescription() {
        assertEquals("[E][ ] book festival (from: Monday 2:00 pm to: 4:00 pm)",
                new Event("book festival", "Monday 2:00 pm", "4:00 pm").toString());
    }
}
