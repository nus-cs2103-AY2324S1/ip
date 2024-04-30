package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test 
    public void deadlineTest() {
        Deadline testDeadline = new Deadline("deadline", "2019-10-15 1800");
        assertEquals("[D][ ] deadline (by: Oct 15 2019 18:00)", 
        testDeadline.toString(), "toString() method works");

        testDeadline.changeStatus();
        assertEquals("[D][X] deadline (by: Oct 15 2019 18:00)", 
        testDeadline.toString(), "changeStatus() method works");
    }

    @Test
    public void updateDeadlineTest() {
        Deadline testDeadline = new Deadline("deadline", "2019-10-15 1800");
        testDeadline.update("dEadline");
        testDeadline.updateDate(testDeadline.convertToDateTime("2019-10-15 1700"), false);
        assertEquals("[D][ ] dEadline (by: Oct 15 2019 17:00)", 
        testDeadline.toString(), "updating method works");
    }
}
