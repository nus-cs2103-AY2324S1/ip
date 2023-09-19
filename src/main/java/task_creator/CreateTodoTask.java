package task_creator;

import duke.IrisException;
import duplicate_detectors.TodoDuplicateDetector;
import tasks.Task;
import tasks.Todo;

import java.util.ArrayList;

public class CreateTodoTask extends CreateTask {
    @Override
    public Todo create(String content, ArrayList<Task> taskList) {
        TodoDuplicateDetector todoDuplicateDetector = new TodoDuplicateDetector();
        if (todoDuplicateDetector.checkDuplicates(content, taskList)) {
            throw new IrisException("Error: This todo task already exists.");
        }
        return new Todo(content);
    }
}
