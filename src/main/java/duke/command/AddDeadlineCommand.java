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

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Deadline deadline = new Deadline(description, by);
        tasks.addDeadline(deadline);
        storage.writeData(tasks.toWriteString());
        String returnMessage = "Ok. Your tasklist has grown longer with this addition:\n"
                + deadline.toString()
                + "\nYou now have " + tasks.getLength() + " things to do."
                + "\nI'm sure you're dying to finish them.";
        return returnMessage;
    }
}