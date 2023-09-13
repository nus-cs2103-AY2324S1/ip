package dude.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void createDeadlineTest(){
        String description = "Homework";
        ToDo todo = new ToDo(description);
        String expected = "[T][ ] Homework";
        assertEquals(expected, todo.toString());
    }
}
