package Evaluator;

import Ui.Logger;
import Storage.FileStorage;
import TaskList.Task;
import TaskList.TaskList;
import Exception.KevinException;

import java.util.ArrayList;

public class UnmarkStrategy extends BaseStrategy {
    public UnmarkStrategy(TaskList taskList, ArrayList<String> arguments) {
        super(taskList, arguments);
    }

    @Override
    public boolean evaluate(Logger logger, FileStorage fileStorage, boolean isInFile) throws KevinException {
        int index = Integer.parseInt(this.arguments.get(1));

        Task newTask = this.taskList.unmark(index);

        if (!isInFile) {
            fileStorage.overwriteTask(newTask, index);
            logger.log("OK, I've marked this task as not done yet: \n\t\t" + newTask);
        }

        return true;
    }
}
