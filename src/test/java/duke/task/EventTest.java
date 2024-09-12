package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;

public class EventTest extends TaskTest {

    @Test
    public void toString_withCorrectFormat_success() {
        LocalDate startDate = LocalDate.of(2023, 2, 14);
        LocalDate endDate = LocalDate.of(2023, 2, 15);
        Event event = new Event("Dating with you", startDate, endDate);
        assertEquals("[E][ ] Dating with you (from: Feb 14 2023 to: Feb 15 2023)", event.toString());
    }

    @Test
    public void saveString_withCorrectFormat_success() {
        LocalDate startDate = LocalDate.of(2023, 2, 14);
        LocalDate endDate = LocalDate.of(2023, 2, 15);
        Event event = new Event("Dating with you", startDate, endDate);
        assertEquals("0/Dating with you/2023-02-14/2023-02-15", event.saveString());
    }
}
