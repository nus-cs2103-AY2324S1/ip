package duke.parser;

import duke.commands.Command;
import duke.commands.IncorrectCommand;
import duke.commands.ListCommand;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.DukeList;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    void list_wrongInput_incorrectCommand() {
        Command com = Parser.parse("list  ");
        assertTrue(com instanceof IncorrectCommand);
    }

    @Test
    void list_correctInput_listCommand() {
        Command com = Parser.parse("list");
        assertTrue(com instanceof ListCommand);
    }
}
