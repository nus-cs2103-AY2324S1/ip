package dot.tasks;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void event_boundaryAndNomralDates_success() {
        Event testEvent1 = new Event("Test Event 1", "24/05/2021 0000", "25/05/2021 0000");
        Event testEvent2 = new Event("Test Event 2", "24/05/2021 0000", "25/05/2021 2359");
        Event testEvent3 = new Event("Test Event 3", "24/05/2021 0000", "25/05/2021 1235");
        Event testEvent4 = new Event("Test Event 4", "25/05/2021 0000", "25/05/2021 0000");
        Event testEvent5 = new Event("Test Event 5", "23/05/2021 2359", "23/05/2021 2359");
        Event testEvent6 = new Event("Test Event 6", "23/05/2021 2359", "24/05/2021 0000");
        LocalDateTime startOfDay = LocalDateTime.of(2021, 5, 24, 0, 0);
        LocalDateTime endOfDay = LocalDateTime.of(2021, 5, 24, 23, 59);

        Assertions.assertTrue(testEvent1.isOnDate(startOfDay, endOfDay));
        Assertions.assertTrue(testEvent2.isOnDate(startOfDay, endOfDay));
        Assertions.assertTrue(testEvent3.isOnDate(startOfDay, endOfDay));
        Assertions.assertFalse(testEvent4.isOnDate(startOfDay, endOfDay));
        Assertions.assertFalse(testEvent5.isOnDate(startOfDay, endOfDay));
        Assertions.assertTrue(testEvent6.isOnDate(startOfDay, endOfDay));
    }

}
