package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeadlineCommand extends Command {

    private final Deadline deadline;

    public DeadlineCommand(String input) throws DukeException {
        if (input == null) {
            throw new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
        } else if (!input.contains("/by")) {
            throw new DukeException(" ☹ OOPS!!! By when?");
        }
        String[] tokens = input.split("/by");
        this.deadline = new Deadline(tokens[0].strip(), tokens[1].strip());
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.addTask(deadline);
        ui.showMessage(String.format("Got it. I've added this task:\n    " +
                "%s\nNow you have %d tasks in the list.", deadline, taskList.getListSize()));
        storage.appendFile(deadline);
    }
}