package duke.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
        @Test
        public void testStringConverstion() {
                assertEquals(
                                "| D |   | study cs2103 (by: Dec 01 2023)",
                                new Deadlines(
                                                "study cs2103", "2023-12-01").toString());
                assertEquals(
                                "| D |   | study cs2100 (by: Nov 30 2023)",
                                new Deadlines(
                                                "study cs2100", "30/11/2023").toString());
        }
}
