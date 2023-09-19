package duke.command;

import duke.exception.KoraException;
import duke.list.TaskList;
import duke.storage.Storage;

public class LoadFileCommand extends Command {
    private String commandMessage = "";
    private String filePath;

    public LoadFileCommand(String input) {

        filePath = "./data/" + input.replace("load ", "") + ".txt";
    }

    @Override
    public String getCommandMessage() {
        return commandMessage;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws KoraException {
        taskList.addTaskList(storage.loadTask());
        commandMessage = "Wow! I have loaded tasks from: " + filePath;
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
