package duke;  //same package as the class being tested

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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