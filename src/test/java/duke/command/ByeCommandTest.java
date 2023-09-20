package duke.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import duke.exception.KoraException;
import duke.list.TaskList;
import duke.storage.Storage;

public class ByeCommandTest {

    @Test
    public void isExit_shouldBeTrue() {
        Command command = new ByeCommand();
        Assertions.assertTrue(command.isExit());
    }

    @Test
    public void isFileCommand_shouldBeFalse() {
        Command command = new ByeCommand();
        Assertions.assertFalse(command.isFileCommand());
    }
    @Test
    public void isSetCommand_shouldBeFalse() {
        Command command = new ByeCommand();
        Assertions.assertFalse(command.isSetCommand());
    }

    @Test
    public void isCommandMessage_equalsToByeCommandMessage() throws KoraException {
        Command command = new ByeCommand();
        TaskList taskList = new TaskList();
        Storage storage = new Storage("./data/test");
        command.execute(taskList, storage);
        String expected = "JalGa! See you again!";
        Assertions.assertEquals(expected, command.getCommandMessage());
    }
}
