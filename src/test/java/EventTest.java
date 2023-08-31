import Exceptions.DukeException;
import Tasks.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {
    @Test
    public void printString_markAndUnmark_success() throws DukeException {
        Event event = new Event("xxx /from 16/12/2024 6:30 PM /to 16/12/2024 8:30 PM");
        assertEquals("[E][ ] xxx (from 16 Dec 2024 6:30 PM to 16 Dec 2024 8:30 PM)", event.toString());
        event.markAsDone();
        assertEquals("[E][X] xxx (from 16 Dec 2024 6:30 PM to 16 Dec 2024 8:30 PM)", event.toString());
        event.unmarkAsDone();
        assertEquals("[E][ ] xxx (from 16 Dec 2024 6:30 PM to 16 Dec 2024 8:30 PM)", event.toString());
    }

    @Test
    public void createEvent_invalidEvent_exceptionThrown(){
        try {
            Event event = new Event("xxx /from yy /to zz");
            assertEquals("[D][ ] xxx (from yy to zz)", event.toString());
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid DateTime format. Here are some example dates:\n" +
                    "6/3/2023 5:30 AM, 16/12/2024 6:30PM", e.getMessage());
        }
    }
}
