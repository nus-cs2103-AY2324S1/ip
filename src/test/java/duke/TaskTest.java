package duke; //same package as the class being tested

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Class for testing the Task Class
 */
public class TaskTest {
    @Test
    public void outputTest() {
        Task task = new Task("Sample Task");
        assertEquals("[ ] Sample Task", task.toString());
    }

    @Test
    public void saveStringTest() {
        Task task = new Task("Sample Task");
        assertEquals("0 | Sample Task", task.toSaveString());
    }

}
