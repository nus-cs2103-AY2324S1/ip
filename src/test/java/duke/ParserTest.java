package duke;

import duke.command.ListCommand;
import duke.command.MessageCommand;
import duke.command.CommandParser;
import duke.command.ParsingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parse_list_success() {
        try {
            assertEquals(CommandParser.parse("list").getClass(), ListCommand.class);
        } catch (ParsingException e) {
            fail();
        }
    }

    @Test
    public void parse_listCasing_success() {
        try {
            assertEquals(CommandParser.parse("lIsT").getClass(), ListCommand.class);
        } catch (ParsingException e) {
            fail();
        }
    }

    @Test
    public void parse_listMultipleWords_reject() {
        try {
            assertEquals(CommandParser.parse("list list").getClass(), MessageCommand.class);
            fail();
        } catch (ParsingException ignored) {
        }
    }

}
