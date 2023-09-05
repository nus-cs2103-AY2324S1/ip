package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.command.Command;

public class ParserTest {
    @Test
    public void unknownCommand_success() {
        try {
            Command c = Parser.parse("asdfghjkl");
            fail();
        } catch (Exception e) {
            assertEquals("    ____________________________________________________________\n"
                    + "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                    + "    ____________________________________________________________\n",
                    e.getMessage());
        }
    }

    @Test
    public void wrongIndex_success() {
        try {
            Command c = Parser.parse("mark r");
        } catch (Exception e) {
            assertEquals("    ____________________________________________________________\n"
                    + "     ☹ OOPS!!! The index for marking is invalid.\n"
                    + "    ____________________________________________________________\n",
                    e.getMessage());
        }
    }

}
