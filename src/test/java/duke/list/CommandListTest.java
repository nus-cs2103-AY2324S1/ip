package duke.list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.exception.KoraException;

public class CommandListTest {
    @Test
    public void initialCommandList_shouldHaveBasicCommands() {
        CommandList commandList = new CommandList();
        String expected = "todo";
        String actual = commandList.getCommandType("todo");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void addCommandName_shouldAddToCommandNameList() {
        CommandList commandList = new CommandList();
        commandList.addCommandName("todo", "td");
        Assertions.assertEquals(commandList.getCommandType("td"), "todo");
    }

    @Test
    public void deleteOnlyCommandName_shouldThrowKoraException() {
        Exception exception =
                Assertions.assertThrows(KoraException.class, () -> {
                    CommandList commandList = new CommandList();
                    commandList.deleteCommandName("todo", "todo");
                });
        String expected = "Omo! You won't have any available command name for: todo";
        String actual = exception.getMessage();
        Assertions.assertTrue(actual.contains(expected));
    }
}
