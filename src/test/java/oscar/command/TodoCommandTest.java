package oscar.command;

import org.junit.jupiter.api.Test;
import oscar.exception.OscarException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoCommandTest {
    @Test
    public void validate_validTodoCommand_success() {
        String details = "return book";
        assertDoesNotThrow(() -> new TodoCommand(details).validate());
    }

    @Test
    public void validate_emptyTodoCommand_success() {
        try {
            String details = "";
            TodoCommand todoCommand = new TodoCommand(details);
            todoCommand.validate();
        } catch (OscarException e) {
            assertEquals("Sorry! The description of a todo task cannot be empty.\n", e.getMessage());
        }
    }

    @Test
    public void validate_longTodoCommand_success() {
        try {
            String details = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                    "Morbi pellentesque eros sem, et accumsan lectus placerat volutpat.";
            TodoCommand todoCommand = new TodoCommand(details);
            todoCommand.validate();
        } catch (OscarException e) {
            assertEquals("Sorry! The description of a todo task cannot exceed 200 characters.\n", e.getMessage());
        }
    }
}
