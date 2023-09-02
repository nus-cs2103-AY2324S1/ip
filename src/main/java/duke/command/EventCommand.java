package duke.command;

import duke.exception.ChatException;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class EventCommand extends Command {
    private String description;
    private String from;
    private String to;
    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task event = new Event(this.description, this.from, this.to);
            tasks.addTask(event);
            ui.formatTaskResponse(event, tasks);
            storage.saveList(tasks);
        } catch (ChatException e) {
            ui.showLoadingError(e);
        }
    };
    public boolean isExit() {
        return false;
    };
}
