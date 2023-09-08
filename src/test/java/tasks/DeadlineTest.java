package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testStringConversion() {
        Task actual = new Deadline("eat cereal", "6/9/2023 1523", false);
        assertEquals("D | 0 | eat cereal | 06 Sep 2023 3:23PM", actual.toString());
    }
}
