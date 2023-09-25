package duke.command;

import duke.exception.KoraException;
import duke.list.TaskList;
import duke.storage.Storage;

/**
 * Subclass of Command class. Loads content of the specified file.
 */
public class LoadFileCommand extends Command {
    private String commandMessage = "";
    private String filePath;

    /**
     * Class constructor of LoadFileCommand.
     * @param input String user input for file name.
     */
    public LoadFileCommand(String input) {

        filePath = "./data/" + input.replace("load ", "") + ".txt";
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
     * Loads tasks from specified file.
     * @param taskList List with tasks.
     * @param storage Storage where tasks are stored.
     * @throws KoraException When there is error in loading tasks from the specified file.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) throws KoraException {
        taskList.addTaskList(storage.loadTask());
        commandMessage = "Wow! I have loaded tasks from: " + filePath;
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
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public String getFilePath() {
        return filePath;
    }
}
