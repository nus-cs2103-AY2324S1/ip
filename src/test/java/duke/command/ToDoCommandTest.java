package duke.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.exception.KoraException;

public class ToDoCommandTest {
    @Test
    public void isExit_shouldBeFalse() throws KoraException {
        Command command = new ToDoCommand(new String[]{"todo test"});
        Assertions.assertFalse(command.isExit());
    }

    @Test
    public void isFileCommand_shouldBeFalse() throws KoraException {
        Command command = new ToDoCommand(new String[]{"todo test"});
        Assertions.assertFalse(command.isFileCommand());
    }

    @Test
    public void isSetCommand_shouldBeFalse() throws KoraException {
        Command command = new ToDoCommand(new String[]{"todo test"});
        Assertions.assertFalse(command.isSetCommand());
    }

    @Test
    public void invalidInput_shouldThrowKoraException() {
        Exception exception =
                Assertions.assertThrows(KoraException.class, () -> {
                    Command command = new ToDoCommand(new String[]{""});
                });
        String expected = "AiGu! ToDo must have details!";
        String actual = exception.getMessage();
        Assertions.assertTrue(actual.contains(expected));
    }
}
