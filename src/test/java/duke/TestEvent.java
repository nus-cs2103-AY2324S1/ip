package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestEvent {
    @Test
    public void event_testSaveString() throws DukeInvalidDateException {
        assertEquals(new Event("Test event", "2023-12-18 00:00", "2023-12-19 23:59").saveString(),
                "E| |Test event|2023-12-18 00:00|2023-12-19 23:59");
    }

    @Test
    public void event_testIsCompletedSaveString() throws DukeInvalidDateException {
        Event completedEvent = new Event("Test event", "2023-12-18 00:00", "2023-12-19 23:59");
        completedEvent.setIsCompleted(true);
        assertEquals(completedEvent.saveString(),
                "E|X|Test event|2023-12-18 00:00|2023-12-19 23:59");
    }

    @Test
    public void event_testWrongDatetimeFormat() {
        assertThrows(
                DukeInvalidDateException.class,
                ()->{
                    new Event("Test event", "sadad", "2023-12-19 23:59");
                }
        );
    }
}
