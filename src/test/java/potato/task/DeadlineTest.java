package potato.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void toStringTest() {
        try {
            Deadline d = new Deadline("birthday!", "2023-08-18", false, "NIL");
            assertEquals("[D][ ][NIL] birthday! (by: Aug 18 2023)", d.toString());
        } catch (Exception e) {
            System.out.println("Make sure you input is correct!");
        }
    }
}
