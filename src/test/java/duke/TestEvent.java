package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestEvent {
    @Test
    public void testEventSaveString() throws DukeInvalidDateException {
        assertEquals(new Event("Test event", "2023-12-18 00:00", "2023-12-19 23:59").saveString(),
                "E| |Test event|2023-12-18 00:00|2023-12-19 23:59");
    }

    @Test
    public void testEventIsCompletedSaveString() throws DukeInvalidDateException {
        Event completedEvent = new Event("Test event", "2023-12-18 00:00", "2023-12-19 23:59");
        completedEvent.setIsCompleted(true);
        assertEquals(completedEvent.saveString(),
                "E|X|Test event|2023-12-18 00:00|2023-12-19 23:59");
    }

    @Test
    public void testEventWithWrongDatetimeFormat() {
        assertThrows(
                DukeInvalidDateException.class,
                ()->{
                    new Event("Test event", "sadad", "2023-12-19 23:59");
                }
        );
    }
}
