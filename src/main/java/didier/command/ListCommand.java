package didier.command;

import didier.Storage;
import didier.TaskList;
import didier.exception.DidierException;
import didier.exception.TaskNumberException;

/**
 * The ListCommand encapsulates the logic of what occurs when the user tries to list all the tasks in
 * the current task list.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Storage storage) throws TaskNumberException {
    }

    @Override
    public String getBotOutput(TaskList taskList, Storage storage) throws DidierException {
        String outputText = "";
        outputText += "The tasks in your list are as follows:";
        for (int i = 1; i <= taskList.getSize(); i++) {
            outputText += String.format("\n%d.%s", i, taskList.getTask(i));
        }
        return outputText;
    }
}
