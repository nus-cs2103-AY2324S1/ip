import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import chatter.ChatterException;
import chatter.Parser;
import chatter.command.AddCommand;
import chatter.command.Command;
import chatter.command.DeleteCommand;
import chatter.command.ExitCommand;
import chatter.command.ListCommand;

public class ParserTest {
    @Test
    public void parseExit_byeString_returnExitCommand() throws ChatterException {
        String fullCommand = "bye";
        Command c = Parser.parse(fullCommand);
        assertInstanceOf(ExitCommand.class, c);
    }

    @Test
    public void parseEnter_todoTaskString_returnAddCommand() throws ChatterException {
        String fullCommand = "todo return book";
        Command c = Parser.parse(fullCommand);
        assertInstanceOf(AddCommand.class, c);
    }

    @Test
    public void parseList_listString_returnListCommand() throws ChatterException {
        String fullCommand = "list";
        Command c = Parser.parse(fullCommand);
        assertInstanceOf(ListCommand.class, c);
    }

    @Test
    public void parseDelete_deleteString_returnDeleteCommand() throws ChatterException {
        String fullCommand = "delete 1";
        Command c = Parser.parse(fullCommand);
        assertInstanceOf(DeleteCommand.class, c);
    }

    @Test
    public void parseInvalidCommand_invalidString_throwChatterException() {
        String fullCommand = "blahblah";
        try {
            Command c = Parser.parse(fullCommand);
            fail();
        } catch (ChatterException e) {
            assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-("
                    + "\nPlease enter a valid command!", e.getMessage());
        }
    }
}
