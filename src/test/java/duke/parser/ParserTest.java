package duke.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.command.Command;
import duke.exception.KoraException;
import duke.list.CommandList;
import duke.list.TaskList;
import duke.storage.Storage;

public class ParserTest {
    @Test
    public void byeInput_shouldCreateByeCommand() throws KoraException {
        Parser parser = new Parser(new CommandList());
        Command command = parser.parse("bye");
        TaskList taskList = new TaskList();
        Storage storage = new Storage("./data/test");
        command.execute(taskList, storage);
        String expected = "JalGa! See you again!";
        String actual = command.getCommandMessage();
        Assertions.assertEquals(expected, actual);
    }
}
