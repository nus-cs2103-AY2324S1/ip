package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ToDoTest {
    @Test
    public void testToString() {
        Task task = new ToDo("eat");
        String actualOutput = task.toString();
        String expectedOutput = "[T][ ] eat";
        assertEquals(expectedOutput, actualOutput);
    }
}
