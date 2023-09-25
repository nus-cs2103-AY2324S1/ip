package duke.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.exception.KoraException;

public class DeadlineCommandTest {
    @Test
    public void isExit_shouldBeFalse() throws KoraException {
        Command command = new DeadlineCommand(new String[]{"test", "2023-09-12 14:12"});
        Assertions.assertFalse(command.isExit());
    }

    @Test
    public void isFileCommand_shouldBeFalse() throws KoraException {
        Command command = new DeadlineCommand(new String[]{"test", "2023-09-12 14:12"});
        Assertions.assertFalse(command.isFileCommand());
    }

    @Test
    public void isSetCommand_shouldBeFalse() throws KoraException {
        Command command = new DeadlineCommand(new String[]{"test", "2023-09-12 14:12"});
        Assertions.assertFalse(command.isSetCommand());
    }

    @Test
    public void invalidInput_shouldThrowKoraException() {
        Exception exception =
                Assertions.assertThrows(KoraException.class, () -> {
                    Command command = new DeadlineCommand(new String[]{"test"});
                });
        String expected = "AiGu! Deadline needs to have description and due date!";
        String actual = exception.getMessage();
        Assertions.assertTrue(actual.contains(expected));
    }


}
