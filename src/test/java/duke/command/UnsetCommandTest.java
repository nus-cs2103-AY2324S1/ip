package duke.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UnsetCommandTest {
    @Test
    public void isExit_shouldBeFalse() {
        Command command = new UnsetCommand("unset todo t");
        Assertions.assertFalse(command.isExit());
    }

    @Test
    public void isFileCommand_shouldBeFalse() {
        Command command = new UnsetCommand("unset todo t");
        Assertions.assertFalse(command.isFileCommand());
    }

    @Test
    public void isSetCommand_shouldBeTrue() {
        Command command = new UnsetCommand("unset todo t");
        Assertions.assertTrue(command.isSetCommand());
    }
}
