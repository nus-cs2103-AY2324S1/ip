package duke.parsers;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.commands.DeleteCommand;
import duke.commands.FindCommand;
import duke.commands.MarkAsDoneCommand;
import duke.exceptions.UnknownCommandException;

public class ParserHelperTest {

    @Test
    public void parseMarkCommand_validIndex_returnsMarkAsDoneCommand() throws UnknownCommandException {
        assertTrue(ParserHelper.parseMarkCommand("1") instanceof MarkAsDoneCommand);
    }

    @Test
    public void parseDeleteCommand_validIndex_returnsDeleteCommand() throws UnknownCommandException {
        assertTrue(ParserHelper.parseDeleteCommand("1") instanceof DeleteCommand);
    }

    @Test
    public void parseFindCommand_validInput_returnsFindCommand() throws UnknownCommandException {
        assertTrue(ParserHelper.parseFindCommand("keyword") instanceof FindCommand);
    }

    @Test
    public void parseMarkCommand_invalidIndex_throwsUnknownCommandException() {
        assertThrows(UnknownCommandException.class, () -> ParserHelper.parseMarkCommand("abc"));
    }

    @Test
    public void parseDeleteCommand_invalidIndex_throwsUnknownCommandException() {
        assertThrows(UnknownCommandException.class, () -> ParserHelper.parseDeleteCommand("abc"));
    }

    @Test
    public void parseFindCommand_emptyInput_throwsUnknownCommandException() {
        assertThrows(UnknownCommandException.class, () -> ParserHelper.parseFindCommand(""));
    }

    @Test
    public void parseTodoCommand_emptyInput_throwsUnknownCommandException() {
        assertThrows(UnknownCommandException.class, () -> ParserHelper.parseTodoCommand(""));
    }
}

