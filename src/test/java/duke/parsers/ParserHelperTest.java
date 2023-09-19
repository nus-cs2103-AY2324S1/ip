package duke.parsers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import duke.commands.MarkAsDoneCommand;
import duke.commands.DeleteCommand;
import duke.exceptions.UnknownCommandException;

import org.junit.jupiter.api.Test;

public class ParserHelperTest {

    @Test
    public void parseCommandByType_markValidIndex_returnsMarkAsDoneCommand() throws UnknownCommandException {
        assertTrue(ParserHelper.parseCommandByType(CommandType.MARK, "1") instanceof MarkAsDoneCommand);
    }

    @Test
    public void parseCommandByType_deleteValidIndex_returnsDeleteCommand() throws UnknownCommandException {
        assertTrue(ParserHelper.parseCommandByType(CommandType.DELETE, "1") instanceof DeleteCommand);
    }

    @Test
    public void parseCommandByType_invalidCommand_throwsUnknownCommandException() {
        assertThrows(UnknownCommandException.class, () -> ParserHelper.parseCommandByType(CommandType.FIND, "random"));
    }

    @Test
    public void parseTodoCommand_emptyInput_throwsUnknownCommandException() {
        assertThrows(UnknownCommandException.class, () -> ParserHelper.parseTodoCommand(""));
    }
}

