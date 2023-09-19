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

    public ChangeFileCommand(String input) {
        filePath = "./data/" + input.replace("change ", "") + ".txt";
    }

    @Override
    public String getCommandMessage() {
        return commandMessage;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws KoraException {
        taskList.clearTasks();
        taskList.addTaskList(storage.loadTask());
        commandMessage = "Wow! I have changed the file path to: " + filePath;
    }

    @Override
    public boolean isFileCommand() {
        return true;
    }

    @Override
    public String getFilePath() {
        return filePath;
    }
}
