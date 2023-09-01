package kevin.evaluator;

import kevin.exception.KevinException;
import kevin.storage.FileStorage;
import kevin.taskList.Task;
import kevin.taskList.TaskList;
import kevin.ui.Logger;

import java.util.ArrayList;

public class FindStrategy extends BaseStrategy {
    public FindStrategy(TaskList taskList, ArrayList<String> arguments) {
        super(taskList, arguments);
    }

    @Override
    public boolean evaluate(Logger logger, FileStorage fileStorage, boolean isInFile) throws KevinException {
        String keyword = this.arguments.get(1);
        logger.log(taskList.find(keyword));
        return true;
    }
}
