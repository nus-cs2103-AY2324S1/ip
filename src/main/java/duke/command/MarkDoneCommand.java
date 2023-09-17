package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class MarkDoneCommand extends Command {
    private String input;

    public MarkDoneCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert input != null : "input cannot be null";
        int taskIndex = Parser.extractTaskIndex(input);

        // Check if the task is already marked as done
        if (tasks.isTaskDone(taskIndex)) {
            return "Relax, you already did this task. It ain't going anywhere!";
        }

        tasks.markAsDone(taskIndex);
        storage.saveTasksToFile(tasks.getAllTasks());
        return ui.showTaskMarkedAsDone(tasks.getTask(taskIndex));
    }
}
