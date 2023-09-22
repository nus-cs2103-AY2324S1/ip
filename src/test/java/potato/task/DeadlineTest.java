package potato.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void toStringTest() {
        try {
            Deadline d = new Deadline("birthday!", "2023-08-18", false);
            assertEquals("[D][ ] birthday! (by: Aug 18 2023)", d.toString());
        } catch (Exception e) {
            System.out.println("Make sure you input is correct!");
        }
    }
}
