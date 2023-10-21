package task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EventTest {
    public EventTest() {
    }

    @Test
    public void toStringTest() {
        Event test = new Event("camp /from 4/7/2003 1800 /to 7/7/2003 2000");
        Assertions.assertEquals("[E] [ ] camp | FROM: 04 July 2003, 6:00 PM TO: 07 July 2003, 8:00 PM |", test.toString());
    }

    @Test
    public void toFileString() {
        Event test = new Event("camp /from 4/7/2003 1800 /to 7/7/2003 2000");
        Assertions.assertEquals("D | 0 | camp | 04 July 2003, 6:00 PM - 07 July 2003, 8:00 PM", test.toFileString());
    }
}