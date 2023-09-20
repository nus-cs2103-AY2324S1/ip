package duke.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.exception.KoraException;
import duke.list.TaskList;
import duke.storage.Storage;

public class InvalidCommandTest {
    @Test
    public void isExit_shouldBeFalse() {
        Command command = new InvalidCommand();
        Assertions.assertFalse(command.isExit());
    }

    @Test
    public void isFileCommand_shouldBeFalse() {
        Command command = new InvalidCommand();
        Assertions.assertFalse(command.isFileCommand());
    }

    @Test
    public void isSetCommand_shouldBeFalse() {
        Command command = new InvalidCommand();
        Assertions.assertFalse(command.isSetCommand());
    }

    @Test
    public void commandMessage_shouldBeInvalidCommandMessage() throws KoraException {
        Command command = new InvalidCommand();
        TaskList taskList = new TaskList();
        Storage storage = new Storage("./data/test");
        command.execute(taskList, storage);
        String expected = "Mi an,, I do not understand. Maybe can type 'help' to find out the commands?";
        String actual = command.getCommandMessage();
        Assertions.assertEquals(expected, actual);
    }
}
