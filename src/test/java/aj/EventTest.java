package aj;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void fromDt_correctInput_stringReturned() {
        Event testTask = new Event("project meeting", false, "Mon 2pm", "4pm");
        assertEquals(testTask.getFromDt(), "Mon 2pm");
    }

    @Test
    public void toDt_correctInput_stringReturned() {
        Event testTask = new Event("project meeting", false, "Mon 2pm", "4pm");
        assertEquals(testTask.getToDt(), "4pm");
    }

    @Test
    public void toString_noInput_stringPrinted() {
        Event testTask = new Event("project meeting", false, "Mon 2pm", "4pm");
        assertEquals(testTask.toString(), "[E][ ] project meeting (from: Mon 2pm to: 4pm)");
    }
}
