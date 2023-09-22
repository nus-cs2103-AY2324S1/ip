package potato.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toStringTest() {
        try {
            Todo t = new Todo("feed cat", false);
            assertEquals("[T][ ] feed cat", t.toString());
        } catch (Exception e) {
            System.out.println("Make sure you input is correct!");
        }
    }
}
