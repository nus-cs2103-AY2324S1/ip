package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void toDoTest1() {
        try {
            ToDo todoTask = new ToDo("water plants");
            assertEquals(todoTask.getTaskAsString(), "[T][ ] water plants");
        } catch (Exception e) {
            fail();
        }
    }

}
