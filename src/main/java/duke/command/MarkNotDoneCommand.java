package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class MarkNotDoneCommand extends Command {
    private String input;

    public MarkNotDoneCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert input != null : "input cannot be null";
        int taskIndex = Parser.extractTaskIndex(input);

        // Check if the task is already unmarked
        if (!tasks.isTaskDone(taskIndex)) {
            return "Relax, this task is already marked as not done! It isn't going to run away from you";
        }

        tasks.markAsNotDone(taskIndex);
        storage.saveTasksToFile(tasks.getAllTasks());
        return ui.showTaskMarkedAsNotDone(tasks.getTask(taskIndex));
    }
}
