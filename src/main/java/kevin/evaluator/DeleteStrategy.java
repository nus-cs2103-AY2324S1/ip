package kevin.evaluator;

import kevin.ui.Logger;
import kevin.storage.FileStorage;
import kevin.taskList.TaskList;
import kevin.taskList.Task;
import kevin.exception.KevinException;

import java.util.ArrayList;

public class DeleteStrategy extends BaseStrategy {
    public DeleteStrategy(TaskList taskList, ArrayList<String> arguments) {
        super(taskList, arguments);
    }

    @Override
    public boolean evaluate(Logger logger, FileStorage fileStorage, boolean isInFile) throws KevinException {
        int index = Integer.parseInt(this.arguments.get(1));

        Task deletedTask = this.taskList.delete(index);

        if (!isInFile) {
            fileStorage.deleteTask(index);
            logger.log("Noted. I've removed this task: \n\t\t" +
                    deletedTask +
                    "\n\tNow you have " + this.taskList.size() + " tasks in the list.");
        }

        return true;
    }
}
