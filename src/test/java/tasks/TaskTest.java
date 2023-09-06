package tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testStringConversion() {
        Task actual = new Task("eat cereal", false);
        assertEquals("0 | eat cereal", actual.toString());
    }
}
