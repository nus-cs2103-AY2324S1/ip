package duke.command;

import duke.list.TaskList;
import duke.storage.Storage;

/**
 * Subclass of Command class. Displays list of Commands that user can use.
 */
public class HelpCommand extends Command {
    private String commandMessage = "";

    /**
     * Class constructor of HelpCommand.
     */
    public HelpCommand() {
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public String getCommandMessage() {
        return commandMessage;
    }

    /**
     * {@inheritDoc}
     *
     * Generates message with all the available commands in Kora.
     * @param taskList List with tasks.
     * @param storage Storage where tasks are stored.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {

        commandMessage = "Here is the list of commands that you can use! \n"
                + "\n=== === === === === === === ===     task related commands     === === === === === === === ===\n"
                + "todo [description] - to add new todo task \n"
                + "deadline [description] /by [due date YYYY-MM-DD HH:mm] - to add new deadline task \n"
                + "event [description] /from [start YYYY-MM-DD HH:mm] /to [start YYYY-MM-DD HH:mm] - to add new event task \n"
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
    }
}
