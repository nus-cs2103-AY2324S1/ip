package duke.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UnmarkCommandTest {
    @Test
    public void isExit_shouldBeFalse() {
        Command command = new UnmarkCommand(new String[]{"unmark 1"});
        Assertions.assertFalse(command.isExit());
    }

    @Test
    public void isFileCommand_shouldBeFalse() {
        Command command = new UnmarkCommand(new String[]{"unmark 1"});
        Assertions.assertFalse(command.isFileCommand());
    }

    @Test
    public void isSetCommand_shouldBeFalse() {
        Command command = new UnmarkCommand(new String[]{"unmark 1"});
        Assertions.assertFalse(command.isSetCommand());
    }
}
