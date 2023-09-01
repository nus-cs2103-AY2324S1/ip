package duke.test;

import duke.tasks.Event;
import duke.exceptions.DukeException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventTest {
    @Test
    public void testString() {
        Event event = new Event("event holiday /from Monday /to Tuesday");
        assertEquals(event.toString(), "[E][ ] holiday (from: Monday to: Tuesday)");
    }

    @Test
    public void testDoubleMark() {
        Event event =  new Event("event holiday /from Monday /to Tuesday");
        try {
            event.markDone();
            assertThrows(DukeException.class, () -> event.markDone());
        } catch (DukeException e) {
        }
    }
}
