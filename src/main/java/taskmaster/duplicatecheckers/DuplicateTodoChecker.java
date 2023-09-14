package taskmaster.duplicatecheckers;

import taskmaster.tasks.Task;
import taskmaster.tasks.TaskList;
import taskmaster.tasks.Todo;

public class DuplicateTodoChecker {
    public boolean isDuplicateTodo(String description) {
        for (Task task : TaskList.list) {
            if (task instanceof Todo) {
                Todo todo = (Todo) task;
                if (todo.getDescription().equalsIgnoreCase(description)) {
                    return true;
                }
            }
        }
        return false;
    }
}
