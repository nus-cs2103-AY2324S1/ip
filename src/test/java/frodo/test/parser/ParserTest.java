package frodo.test.parser;

import frodo.tasks.Events;
import core.Duke;
import frodo.parser.Parser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseCommand_WithTodoCommandType_ReturnsCorrectCommandType_ThrowsException() {
        String command = "todo run 2 miles";
        Parser parser = new Parser();

        assertEquals(parser.getCommandType(command), Duke.CommandType.TODO);
    }


    @Test
    public void parseCommand_WithInvalidCommandType_ReturnsUnknownCommandType_ThrowsException() {
        String command = "Hello";
        Parser parser = new Parser();

        assertEquals(parser.getCommandType(command), Duke.CommandType.UNKNOWN);
    }


}
