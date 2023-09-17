package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    private String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int taskIndex = Parser.extractTaskIndex(input);
        Task deletedTask = tasks.deleteTask(taskIndex);
        storage.saveTasksToFile(tasks.getAllTasks());
        return ui.showTaskDeleted(deletedTask, tasks.getTotalTasks());
    }
}
