package parser;
import commands.Command;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import commands.*;

public class ParserTest {
    @Test
    void list_wrongInput_incorrectCommand_listoutCommand() {
        Command com = Parser.parseCommand("listout ");
        assertTrue(com instanceof IncorrectCommand);
    }

    @Test
    void list_correctInput_listoutCommand() {
        Command com = Parser.parseCommand("listout");
        assertTrue(com instanceof ListoutCommand);
    }
}

