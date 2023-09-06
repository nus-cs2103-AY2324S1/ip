package tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDosTest {
    @Test
    public void testStringConversion() {
        Task actual = new ToDos("eat cereal", false);
        assertEquals("T | 0 | eat cereal", actual.toString());
    }
}
