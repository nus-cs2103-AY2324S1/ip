package duke.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.exception.KoraException;
import duke.list.TaskList;
import duke.storage.Storage;

public class HelpCommandTest {
    @Test
    public void isExit_shouldBeFalse() {
        Command command = new HelpCommand();
        Assertions.assertFalse(command.isExit());
    }

    @Test
    public void isFileCommand_shouldBeFalse() {
        Command command = new HelpCommand();
        Assertions.assertFalse(command.isFileCommand());
    }

    @Test
    public void isSetCommand_shouldBeFalse() {
        Command command = new HelpCommand();
        Assertions.assertFalse(command.isSetCommand());
    }

    @Test
    public void commandMessage_shouldBeCommandList() throws KoraException {
        Command command = new HelpCommand();
        TaskList taskList = new TaskList();
        Storage storage = new Storage("./data/test");
        command.execute(taskList, storage);
        String expected = "Here is the list of commands that you can use! \n"
                + "\n=== === === === === === === ===     task related commands     === === === === === === === ===\n"
                + "todo [description] - to add new todo task \n"
                + "deadline [description] /by [due date YYYY-MM-DD HH:mm] - to add new deadline task \n"
                + "event [description] /from [start YYYY-MM-DD HH:mm] /to [start YYYY-MM-DD HH:mm] "
                + "- to add new event task \n"
                + "mark [task index] - to mark the specified task to be done \n"
                + "unmark [task index] - to unmark the specified task to be undone \n"
                + "delete [task index] - to delete the specified task from current task list \n"
                + "list - to list all available commands \n"
                + "find [keyword] - to find tasks that has the matching keyword \n"
                + "=== === === === === === === === === === === === === === === === === === === === === === ===\n"
                + "\n=== === === === === === === ===     file related commands     === === === === === === === ===\n"
                + "load [file name] - to load a new task list in addition to current task list \n"
                + "change [file name] - to change to new task list \n"
                + "=== === === === === === === === === === === === === === === === === === === === === === ===\n"
                + "\n=== === === === === === === === command related commands === === === === === === === ===\n"
                + "display - to see the list of command names for each command type \n"
                + "set [command type] [command name] - to add new name to specified command \n"
                + "unset [command type] [command name] - to delete the specified command name \n"
                + "=== === === === === === === === === === === === === === === === === === === === === === ===\n"
                + "\n=== === === === === === === ===        general commands        === === === === === === === ===\n"
                + "help - to see the list of commands \n"
                + "bye - to exit the program \n"
                + "=== === === === === === === === === === === === === === === === === === === === === === ===\n"
                + "\nHope this helped!";
        String actual = command.getCommandMessage();
        Assertions.assertEquals(expected, actual);
    }
}
