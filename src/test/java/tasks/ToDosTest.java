package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDosTest {
    @Test
    public void testStringConversion() {
        Task actual = new ToDos("eat cereal", false);
        assertEquals("T | 0 | eat cereal", actual.toString());
    }
}
