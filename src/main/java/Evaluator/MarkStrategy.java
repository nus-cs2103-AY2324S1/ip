package Evaluator;

import Ui.Logger;
import Storage.FileStorage;
import TaskList.TaskList;
import TaskList.Task;
import Exception.KevinException;

import java.util.ArrayList;

public class MarkStrategy extends BaseStrategy {
    public MarkStrategy(TaskList taskList, ArrayList<String> arguments) {
        super(taskList, arguments);
    }

    @Override
    public boolean evaluate(Logger logger, FileStorage fileStorage, boolean isInFile) throws KevinException {
        int index = Integer.parseInt(this.arguments.get(1));

        Task newTask = this.taskList.mark(index);

        if (!isInFile) {
            fileStorage.overwriteTask(newTask, index);
            logger.log("Nice! I've marked this task as done: \n\t\t" + newTask);
        }
        return true;
    }
}
