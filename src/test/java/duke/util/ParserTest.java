package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.command.AddCommand;
import duke.command.ListCommand;

public class ParserTest {

    @Test
    public void check_parse_output() {
        //check if list command works
        try {
            assert(Parser.parseUserInput("list") instanceof ListCommand);
        } catch (Exception e) {
            fail(); //should not reach this line
        }

        //check if add command works
        try {
            assert(Parser.parseUserInput("todo homework") instanceof AddCommand);
        } catch (Exception e) {
            fail(); //should not reach this line
        }

        //check if invalid input throws an error
        try {
            Parser.parseUserInput("invalid input string");
            fail(); //should not reach this line
        } catch (Exception e) {
            assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }
}
