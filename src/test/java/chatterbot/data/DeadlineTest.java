package chatterbot.data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {

    @Test
    public void deadline_description_addedToList() {
        // entered deadline will be added successfully
        assertEquals("[D][ ] this assignment (by: Oct 12 2019)", new Deadline("this assignment","2019-10-12").toString());

        // entered deadline will be added successfully
        assertEquals("[D][ ] chores (by: Oct 12 2019)", new Deadline("chores", "2019-10-12").toString());
    }

    @Test
    public void deadline_empty_exceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Deadline("", "");
        });
    }
}