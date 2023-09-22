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
                + "\n=== === === === === === === ===        general commands        === === === === === === === ===\n"
                + "bye - to exit the program \n"
                + "find 'keyword' - to find tasks that has the matching keyword \n"
                + "help - to see the list of commands \n"
                + "=== === === === === === === === === === === === === === === === === === === === === === ===\n"
                + "\n=== === === === === === === ===     file related commands     === === === === === === === ===\n"
                + "change 'file_name' - to change to new task list \n"
                + "load 'file_name' - to load a new task list in addition to current task list \n"
                + "=== === === === === === === === === === === === === === === === === === === === === === ===\n"
                + "\n=== === === === === === === ===     task related commands     === === === === === === === ===\n"
                + "todo 'task_name' - to add new todo task \n"
                + "deadline 'task_name' /by YYYY-MM-DD HH:mm - to add new deadline task \n"
                + "event 'task_name' /from YYYY-MM-DD HH:mm/to YYYY-MM-DD HH:mm - to add new event task \n"
                + "mark 'task_number' - to mark the specified task to be done \n"
                + "unmark 'task_number' - to unmark the specified task to be undone \n"
                + "delete 'task_number' - to delete the specified task from current task list \n"
                + "=== === === === === === === === === === === === === === === === === === === === === === ===\n"
                + "\n=== === === === === === === === command related commands === === === === === === === ===\n"
                + "display - to see the list of command names for each command type \n"
                + "set 'new_command_name' 'command_type' - to add new name to specified command \n"
                + "unset 'command_name' 'command_type' - to delete the specified command name \n"
                + "=== === === === === === === === === === === === === === === === === === === === === === ===\n"
                + "\nHope this helped!";
    }
}
