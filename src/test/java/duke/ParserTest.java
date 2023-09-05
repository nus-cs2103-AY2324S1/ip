package duke;

import duke.command.ListCommand;
import duke.command.MessageCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parse_list_success() {
        try {
            assertEquals(Parser.parse("list").getClass(), ListCommand.class);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parse_listCasing_success() {
        try {
            assertEquals(Parser.parse("lIsT").getClass(), ListCommand.class);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parse_listMultipleWords_reject() {
        try {
            assertEquals(Parser.parse("list list").getClass(), MessageCommand.class);
            fail();
        } catch (DukeException ignored) {
        }
    }

}
