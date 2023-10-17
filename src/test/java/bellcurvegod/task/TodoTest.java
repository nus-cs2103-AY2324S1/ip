package bellcurvegod.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void generateTodoFromInput_emptyDescription_exceptionThrown() {
        try {
            Todo.generateTodoFromInput("todo");
            fail();
        } catch (Exception e) {
            assertEquals("You did not provide any description to this Todo.\n"
                + "To add a Todo, enter \"todo <description>\".\n", e.getMessage());
        }
    }
}
