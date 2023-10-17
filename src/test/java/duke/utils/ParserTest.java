package duke.utils;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.ExitCommand;
import duke.commands.HelpCommand;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class ParserTest {
    @Test
    public void parseTest() {
        Parser parser = new Parser();

        Command command = parser.parse("bye");
        assertInstanceOf(ExitCommand.class, command);

        command = parser.parse("help");
        assertInstanceOf(HelpCommand.class, command);

        command = parser.parse("event Attend lessons /from 2023-09-18 /to 2023-09-22");
        assertInstanceOf(AddCommand.class, command);
    }
}
