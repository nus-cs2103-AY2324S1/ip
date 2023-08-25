package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ByeCommandTest {

    @Test
    public void byeCommandShouldExit() {
        Command c = new ByeCommand();
        assertTrue(c.isExit());
    }
}
