package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class InvalidCommandTest {
    @Test
    public void execute_invalidCommand_success() {
        Command command = new InvalidCommand("this is invalid");
        String[] response = command.execute();

        assertArrayEquals(response, new String[]{"this is invalid"});
    }
}
