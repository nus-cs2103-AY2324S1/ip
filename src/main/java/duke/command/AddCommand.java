package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class AddCommand extends Command {
    private String input;

    public AddCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task newTask = Parser.parseTask(input);
        assert newTask != null : "New task cannot be null";
        tasks.addTask(newTask);
        storage.saveTasksToFile(tasks.getAllTasks());
        return ui.showTaskAdded(newTask, tasks.getTotalTasks());
    }
}
