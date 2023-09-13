package prts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import prts.command.CommandParser;
import prts.command.ListCommand;
import prts.command.MessageCommand;
import prts.command.ParsingException;


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
            // success case, ignore exception
        }
    }

}
