package duke.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
        @Test
        public void toString_testStringConverstion_correctResult() {
                assertEquals(
                                "| E |   | study cs2103 (from: Aug 14 2023 to: Dec 01 2023)",
                                new Events(
                                                "study cs2103", "2023-08-14", "2023-12-01").toString());
                assertEquals(
                                "| E |   | play computer games (from: Aug 25 2020 to: Aug 26 2020)",
                                new Events(
                                                "play computer games", "25/08/2020", "26/08/2020").toString());
        }
}
