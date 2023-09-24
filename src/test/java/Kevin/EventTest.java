package Kevin;

import Forgotten.Priority;
import Forgotten.Task.Event;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testStringConversion() {
        Assertions.assertEquals("[E][ ] project meeting (from: Dec 1 2021 to: Dec 2 2021) [P: MEDIUM]",
                new Event("project meeting",
                        LocalDate.parse("2021-12-01"),
                        LocalDate.parse("2021-12-02"), false, Priority.MEDIUM).toString());
    }
}
