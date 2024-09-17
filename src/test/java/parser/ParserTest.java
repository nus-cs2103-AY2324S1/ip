package parser;

import duke.commands.Command;
//CHECKSTYLE.OFF: CustomImportOrder
import duke.commands.IncorrectCommand;
import duke.commands.ListoutCommand;
import duke.parser.Parser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
//CHECKSTYLE.ON: CustomImportOrder
public class ParserTest {
    @Test
    void listIncorrectCommandListoutCommand() {
        Command com = Parser.parseCommand("listout ");
        assertTrue(com instanceof IncorrectCommand);
    }

    @Test
    void list_correctInput_listoutCommand() {
        Command com = Parser.parseCommand("listout");
        assertTrue(com instanceof ListoutCommand);
    }
}

