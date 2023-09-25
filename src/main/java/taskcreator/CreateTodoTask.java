package taskcreator;

import java.util.ArrayList;

import duke.IrisException;
import duplicatedetectors.TodoDuplicateDetector;
import tasks.Task;
import tasks.Todo;

/**
 * This class handles the creation of Todo objects.
 */
public class CreateTodoTask extends CreateTask {
    /**
     * This method creates an Todo object.
     * @param content
     * @param taskList
     * @return A new Todo object using the inputs.
     */
    @Override
    public Todo create(String content, ArrayList<Task> taskList) {
        TodoDuplicateDetector todoDuplicateDetector = new TodoDuplicateDetector();
        if (todoDuplicateDetector.checkDuplicates(content, taskList)) {
            throw new IrisException("Error: This todo task already exists.");
        }
        return new Todo(content);
    }
}
