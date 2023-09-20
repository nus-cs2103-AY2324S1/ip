package duke.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FindCommandTest {
    @Test
    public void isExit_shouldBeFalse() {
        Command command = new FindCommand(new String[]{"hw"});
        Assertions.assertFalse(command.isExit());
    }

    @Test
    public void isFileCommand_shouldBeFalse() {
        Command command = new FindCommand(new String[]{"hw"});
        Assertions.assertFalse(command.isFileCommand());
    }

    @Test
    public void isSetCommand_shouldBeFalse() {
        Command command = new FindCommand(new String[]{"hw"});
        Assertions.assertFalse(command.isSetCommand());
    }
}
