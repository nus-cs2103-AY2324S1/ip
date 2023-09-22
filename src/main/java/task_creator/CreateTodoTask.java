package task_creator;

import duke.IrisException;
import duplicate_detectors.TodoDuplicateDetector;
import tasks.Task;
import tasks.Todo;

import java.util.ArrayList;

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
