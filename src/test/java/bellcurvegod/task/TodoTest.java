package bellcurvegod.task;

import bellcurvegod.ui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TodoTest {
    @Test
    public void generateTodoFromInput_emptyDescription_exceptionThrown() {
        try {
            assertEquals(new Todo("null"), Todo.generateTodoFromInput("todo"));
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals(Ui.getLine() + "\n" +
                    "You did not provide any description to this Todo.\n" +
                    "To add a Todo, enter \"todo <description>\".\n" +
                    Ui.getLine(), e.getMessage());
        }
    }
}
