package duke.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MarkCommandTest {
    @Test
    public void isExit_shouldBeFalse() {
        Command command = new MarkCommand(new String[]{"mark 1"});
        Assertions.assertFalse(command.isExit());
    }

    @Test
    public void isFileCommand_shouldBeFalse() {
        Command command = new MarkCommand(new String[]{"mark 1"});
        Assertions.assertFalse(command.isFileCommand());
    }

    @Test
    public void isSetCommand_shouldBeFalse() {
        Command command = new MarkCommand(new String[]{"mark 1"});
        Assertions.assertFalse(command.isSetCommand());
    }
}
