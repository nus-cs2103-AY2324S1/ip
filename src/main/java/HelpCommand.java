/**
 * Represents the help command
 */
public class HelpCommand extends Command {
    /**
     * Method to encapsulate the execution logic of the command
     *
     * @param taskList - the task list instance  of the current duke
     * @param ui       - the ui instance of DUKE
     * @param storage  - the storage instance to allow the command to write to the storage
     * @throws DukeBadInputException - if the input cannot be used
     */
    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) throws DukeBadInputException {
        ui.helpMessage();
    }

    /**
     * Checks if the command is the exit command
     *
     * @return true if it is the exit command
     */
    @Override
    boolean isExit() {
        return false;
    }
}
