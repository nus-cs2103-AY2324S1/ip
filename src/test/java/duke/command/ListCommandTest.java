package duke.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.exception.KoraException;
import duke.list.TaskList;
import duke.storage.Storage;

public class ListCommandTest {
    @Test
    public void isExit_shouldBeFalse() {
        Command command = new ListCommand();
        Assertions.assertFalse(command.isExit());
    }

    @Test
    public void isFileCommand_shouldBeFalse() {
        Command command = new ListCommand();
        Assertions.assertFalse(command.isFileCommand());
    }

    @Test
    public void isSetCommand_shouldBeFalse() {
        Command command = new ListCommand();
        Assertions.assertFalse(command.isSetCommand());
    }

    @Test
    public void emptyTaskList_shouldGiveCorrectCommandMessage() throws KoraException {
        Command command = new ListCommand();
        TaskList taskList = new TaskList();
        Storage storage = new Storage("./data/test");
        command.execute(taskList, storage);
        String expected = "Wow! You have no tasks!";
        String actual = command.getCommandMessage();
        Assertions.assertEquals(expected, actual);
    }
}
