package sana;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class AddTodoCommandTest {

    @Test
    public void execute_emptyTodoDescription_sanaExceptionThrown() {
        Command c = new AddTodoCommand("todo", " ");
        try {
            c.execute(new TaskList("mockList"), new Storage("mockFile"));
            fail();
        } catch (SanaException e) {
            assertEquals("OOPS!!! The description of a todo cannot be empty.", e.getMessage());
        }
    }
}
