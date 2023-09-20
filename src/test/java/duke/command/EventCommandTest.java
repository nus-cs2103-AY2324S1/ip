package duke.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.exception.KoraException;

public class EventCommandTest {

    @Test
    public void isExit_shouldBeFalse() throws KoraException {
        Command command = new EventCommand(new String[]{"test", "2023-08-05 00:01", "2023-08-05 12:28"});
        Assertions.assertFalse(command.isExit());
    }

    @Test
    public void isFileCommand_shouldBeFalse() throws KoraException {
        Command command = new EventCommand(new String[]{"test", "2023-08-05 00:01", "2023-08-05 12:28"});
        Assertions.assertFalse(command.isFileCommand());
    }

    @Test
    public void isSetCommand_shouldBeFalse() throws KoraException {
        Command command = new EventCommand(new String[]{"test", "2023-08-05 00:01", "2023-08-05 12:28"});
        Assertions.assertFalse(command.isSetCommand());
    }

    @Test
    public void invalidInput_shouldThrowKoraException() {
        Exception exception =
                Assertions.assertThrows(KoraException.class, () -> {
                    Command command = new EventCommand(new String[]{"test", "2023-08-05 00:01"});
                });
        String expected = "AiGu! Event needs to have a start date and end date!";
        String actual = exception.getMessage();
        Assertions.assertTrue(actual.contains(expected));
    }
}
