package bee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void eventTest() {
        Event event = new Event("Test", "2021-09-22", "2021-09-23");
        assertEquals("[E][ ] Test (from: 2021-09-22 to: 2021-09-23)", event.toString());
    }

    @Test
    public void eventTest2() {
        Event event = new Event("Test", "2021-09-22", "2021-09-23", true);
        assertEquals("[E][X] Test (from: 2021-09-22 to: 2021-09-23)", event.toString());
    }

    @Test
    public void eventTest3() {
        Event event = new Event("Test", "2021-09-22", "2021-09-23", false);
        assertEquals("[E][ ] Test (from: 2021-09-22 to: 2021-09-23)", event.toString());
    }
}
