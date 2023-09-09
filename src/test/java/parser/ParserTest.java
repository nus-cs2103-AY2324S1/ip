package parser;

import commands.Command;
//CHECKSTYLE.OFF: CustomImportOrder
import commands.IncorrectCommand;
import commands.ListoutCommand;
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

