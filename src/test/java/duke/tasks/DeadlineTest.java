package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DeadlineTest {
    @Test
    public void storedStringTest() {
        Deadline deadline1 = new Deadline("Homework", "midnight");
        Deadline deadline2 = new Deadline("Final", "05/24/2024");

        deadline2.markDone();

        //The format should look as follows:
        assertEquals("D | 0 | Homework | midnight", deadline1.storedString());
        //and the date should be formatted in dd/MM/yyyy
        assertEquals("D | 1 | Final | 24/05/2024", deadline2.storedString());

        deadline1.markDone();
        deadline2.markUndone();

        //when you swap the marks, the binary signs should change
        assertEquals("D | 1 | Homework | midnight", deadline1.storedString());
        assertEquals("D | 0 | Final | 24/05/2024", deadline2.storedString());
    }

    @Test
    public void toStringTest() {
        Deadline deadline1 = new Deadline("Homework", "midnight");
        Deadline deadline2 = new Deadline("Final", "05/24/2024");

        deadline2.markDone();

        //toString() should behave similarly to storeString()
        assertEquals("[D][ ] Homework (by: midnight)", deadline1.toString());
        //with no spaces between the two sets of brackets
        assertNotEquals("[D] [ ] Homework | midnight", deadline1.toString());

        //and the date should be formatted in dd/MM/yyyy
        assertEquals("[D][X] Final (by: 24/05/2024)", deadline2.toString());

        deadline1.markDone();
        deadline2.markUndone();

        //when you swap the marks, the "X" should become " " and vice versa
        assertEquals("[D][X] Homework (by: midnight)", deadline1.toString());
        assertEquals("[D][ ] Final (by: 24/05/2024)", deadline2.toString());
    }
}
