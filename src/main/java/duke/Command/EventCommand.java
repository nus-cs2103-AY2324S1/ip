package duke.Command;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;
import duke.task.Task;



public class EventCommand extends Command {
    private String description;
    private String from;
    private String to;
    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }
    @Override
    public String execute(Ui ui, Storage storage, TaskList tasks) throws IOException {
        Task task = new Event(description, from, to);
        tasks.addTask(task);
        storage.writeTasksToFile(tasks);
        return ui.printAddTaskToList(tasks, task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
