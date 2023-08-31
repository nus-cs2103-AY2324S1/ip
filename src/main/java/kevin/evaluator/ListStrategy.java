package kevin.evaluator;

import kevin.ui.Logger;
import kevin.storage.FileStorage;
import kevin.taskList.TaskList;
import kevin.exception.KevinException;

import java.util.ArrayList;

public class ListStrategy extends BaseStrategy {
    public ListStrategy(TaskList taskList, ArrayList<String> arguments) {
        super(taskList, arguments);
    }

    @Override
    public boolean evaluate(Logger logger, FileStorage fileStorage, boolean isInFile) throws KevinException {
        logger.log(taskList.list());
        return true;
    }
}
