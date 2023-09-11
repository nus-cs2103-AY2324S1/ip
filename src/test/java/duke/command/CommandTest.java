package duke.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandTest {

    @Test
    public void byeCommand_shouldExit() {
        Command command = new ByeCommand();
        Assertions.assertTrue(command.isExit());
    }
}