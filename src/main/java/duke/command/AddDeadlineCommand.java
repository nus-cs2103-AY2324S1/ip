package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Deadline;

import java.time.LocalDateTime;

public class AddDeadlineCommand extends Command {
    String description;
    LocalDateTime by;
    public AddDeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Deadline deadline = new Deadline(description, by);
        taskList.addDeadline(deadline);
        storage.writeData(taskList.toWriteString());
        System.out.println("Ok. Your tasklist has grown longer with this addition:\n"
        + deadline.toString()
        + "\nYou now have " + taskList.getLength() + " things to do.\n");
    }
}