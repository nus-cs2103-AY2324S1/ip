package duke.utils;

import duke.commands.Command;
import duke.commands.ExitCommand;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class ParserTest {
    @Test
    public void parseTest() {
        Parser parser = new Parser();
        Command command = parser.parse("bye");
        assertInstanceOf(ExitCommand.class, command);
    }
}
