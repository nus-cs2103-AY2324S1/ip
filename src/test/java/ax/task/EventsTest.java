package ax.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventsTest {

    @Test
    void setDone() {
        Events temp = new Events("time to grind", "2023-01-01", "2024-01-01");
        temp.setDone(true);
        assertEquals(temp.toString(), "[E] [✅] time to grind (from: 2023-01-01 to: 2024-01-01)");
        temp.setDone(false);
        assertEquals(temp.toString(), "[E] [ ] time to grind (from: 2023-01-01 to: 2024-01-01)");
    }

    @Test
    void testToString() {
        Events temp = new Events("time to grind", "2023-01-01", "2024-01-01");
        assertEquals(temp.toString(), "[E] [ ] time to grind (from: 2023-01-01 to: 2024-01-01)");
    }
}