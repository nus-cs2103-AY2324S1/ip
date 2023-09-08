package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void testStringConversion() {
        Task actual = new Task("eat cereal", false);
        assertEquals("0 | eat cereal", actual.toString());
    }
}
