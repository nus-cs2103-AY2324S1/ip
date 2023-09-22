package Duke.parser;

import frodo.tasks.Events;
import core.Duke;
import core.DukeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
