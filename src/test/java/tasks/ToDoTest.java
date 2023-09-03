package tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ToDoTest {

    @Test
    public void toDoString() {
        assertEquals("[T][]die", new ToDo("die").toString());
    }


}
