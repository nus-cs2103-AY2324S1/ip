package duke.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ListNameCommandTest {
    @Test
    public void isExit_shouldBeFalse() {
        Command command = new ListNameCommand();
        Assertions.assertFalse(command.isExit());
    }

    @Test
    public void isFileCommand_shouldBeFalse() {
        Command command = new ListNameCommand();
        Assertions.assertFalse(command.isFileCommand());
    }

    @Test
    public void isSetCommand_shouldBeTrue() {
        Command command = new ListNameCommand();
        Assertions.assertTrue(command.isSetCommand());
    }
}
