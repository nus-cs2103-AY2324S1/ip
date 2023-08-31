package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    void testToString() {
        Deadline deadline = new Deadline("sleep", "2020.09.18");
        assertEquals("[D] [ ] sleep (by:18 Sep 2020)",deadline.toString());
    }

    @Test
    void toFileString() {
        Deadline deadline = new Deadline("eat", "2020-09-18", "Y");
        assertEquals("D|Y|eat|18 Sep 2020",deadline.toFileString());
    }
}