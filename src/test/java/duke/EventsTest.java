package duke;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventsTest {

    @Test
    public void testEvents_initialization() {
        Events event = new Events("Meeting", "2023/09/21 1000", "2023/09/21 1200");
        assertEquals("[E][ ] Meeting (from: 09-21-2023 10:00, to: 09-21-2023 12:00)", event.toString());
    }

    @Test
    public void testEvents_withStatusAndNotes() {
        Events event = new Events("Meeting", "2023/09/21 1000", "2023/09/21 1200", true, "Room 202");
        assertEquals("[E][X] Meeting (from: 09-21-2023 10:00, to: 09-21-2023 12:00)", event.toString());
        assertEquals("Room 202", event.getNotes());
    }

    @Test
    public void testGetSavingFormat() {
        Events event = new Events("Meeting", "2023/09/21 1000", "2023/09/21 1200");
        assertEquals("[E] | [ ] | Meeting | 2023/09/21 1000 | 2023/09/21 1200 | ", event.getSavingFormat());
    }

    @Test
    public void testToTask_fromSavedData() {
        String[] savedData = {"[E]", "[X]", "Meeting", "2023/09/21 1000", "2023/09/21 1200", "Room 202"};
        Events event = Events.toTask(savedData);
        assertEquals("[E][X] Meeting (from: 09-21-2023 10:00, to: 09-21-2023 12:00)", event.toString());
        assertEquals("Room 202", event.getNotes());
    }
}
