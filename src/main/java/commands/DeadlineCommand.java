package commands;

import components.DukeException;
import components.Storage;
import components.Ui;
import tasks.Deadline;
import tasks.Task;
import tasks.TaskList;


public class DeadlineCommand extends Command {
    private String command;

    public DeadlineCommand(String command) {
        this.command = command;
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        Task newDeadline = new Deadline(command.substring(9), list);
        Task oldTask = newDeadline.getOldTask();
        if (oldTask != null) {
            Command.tempTask = newDeadline;
            throw new DukeException.DuplicateDescriptionException(oldTask);
        }
        return list.addTask(newDeadline, storage);
    }
}
