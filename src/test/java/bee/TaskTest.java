package bee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void taskTest() {
        Task task = new Task("Test");
        assertEquals("[ ] Test", task.toString());
    }

    @Test
    public void taskTest2() {
        Task task = new Task("Test", true);
        assertEquals("[X] Test", task.toString());
    }

    @Test
    public void taskTest3() {
        Task task = new Task("Test", false);
        assertEquals("[ ] Test", task.toString());
    }
}
