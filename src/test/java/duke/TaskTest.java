package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void toStringTest(){
        Task unmarkedTask = new Todo("Clean", true);
        Task markedTask = new Todo("Cook", false);
        assertEquals("[T][X] Clean", unmarkedTask.toString());
        assertEquals("[T][ ] Cook", markedTask.toString());
    }
}
