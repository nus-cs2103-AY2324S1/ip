package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void toStringTest(){
        assertEquals("[D][ ] Test deadline (by: Aug 31 2023, 18:00)", (new Deadline("Test deadline",
                "31-08-2023 18:00", false)).toString());

        assertEquals("[D][X] Test deadline (by: Aug 31 2023, 18:00)", (new Deadline("Test deadline",
                "31-08-2023 18:00", true)).toString());
    }

    @Test
    public void markAsDone() {
        Deadline deadline = new Deadline("Test deadline",
                "31-08-2023 18:00", false);
        deadline.markTask();
        assertEquals("[D][X] Test deadline (by: Aug 31 2023, 18:00)", deadline.toString());
    }

    @Test
    public void markUndone() {
        Deadline deadline = new Deadline("Test deadline",
                "31-08-2023 18:00", false);
        deadline.markTask();
        deadline.unmarkTask();
        assertEquals("[D][ ] Test deadline (by: Aug 31 2023, 18:00)", deadline.toString());
    }
}
