package duke.command;

import duke.exception.KoraException;
import duke.list.TaskList;
import duke.storage.Storage;

/**
 * Subclass of Command class. Changes the current file directory.
 */
public class ChangeFileCommand extends Command {
    private String commandMessage = "";
    private String filePath;

    /**
     * Class constructor for ChangeFileCommand.
     * @param input File Name from user input.
     */
    public ChangeFileCommand(String input) {
        filePath = "./data/" + input.replace("change ", "") + ".txt";
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
     * Changes the current working directory to specified file.
     * @param taskList List with tasks.
     * @param storage Storage where tasks are stored.
     * @throws KoraException When there is error in loading the tasks in storage.
     */

    @Override
    public void execute(TaskList taskList, Storage storage) throws KoraException {
        taskList.clearTasks();
        taskList.addTaskList(storage.loadTask());
        commandMessage = "Wow! I have changed the file path to: " + filePath;
    }

    /**
     * Returns true as it is file related command.
     * @return True.
     */
    @Override
    public boolean isFileCommand() {
        return true;
    }

    /**
     * Returns specified file path.
     * @return File path.
     */
    @Override
    public String getFilePath() {
        return filePath;
    }
}
