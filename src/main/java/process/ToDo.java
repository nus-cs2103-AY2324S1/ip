package process;

import task.TaskManager;

/**
 * A class for the process of creating a todo task
 */
public class ToDo implements ComplexProcess {
    private TaskManager tasks = TaskManager.init();
    private boolean isComplete = false;
    @Override
    public String firstInstruction() {
        return "So you want to add a ToDo task. Tell me what's the task.";
    }

    @Override
    public boolean isComplete() {
        return isComplete;
    }

    @Override
    public String processInput(String input) {
        isComplete = true;
        return tasks.addTask(new task.ToDo(input));
    }
}
