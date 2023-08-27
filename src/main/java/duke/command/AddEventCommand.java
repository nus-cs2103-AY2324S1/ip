package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.task.Event;

public class AddEventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The format of an event should be: event DESCRIPTION /from DATE /to DATE");
        }
        tasks.addTask(new Event(description, from, to));
        ui.showTaskAddedMessage(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
