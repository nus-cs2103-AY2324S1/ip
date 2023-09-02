package joe.commands;

import joe.Storage;
import joe.TaskList;
import joe.Ui;
import joe.tasks.TodoTask;

public class TodoCommand extends Command {
    private final String taskDetails;

    public TodoCommand(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TodoTask newTask = new TodoTask(taskDetails);
        tasks.add(newTask);

        ui.print(
                String.format(
                        "Got it, I've added this task:%n %s%nNow you have %d tasks in the list.",
                        newTask, tasks.size()));

        storage.saveToFile(tasks);
    }
}
