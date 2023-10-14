package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTaskTest {

    @Test
    public void testTodoTaskFormat_validInput_expectedOutcome() {
        Task task = new TodoTask("A todo task", 0);
        assertEquals("T|0|A todo task", task.getTextFormattedString());
    }

    @Test
    public void testTodoTaskSaveFormat_validInput_expectedOutcome() {
        Task task = new TodoTask("A todo task", 0);
        assertEquals("[T][ ] A todo task", task.toString());
    }

}
