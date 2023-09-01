package duke.command;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Task;

import java.time.LocalDate;

public class DeadlineCommand extends Command {

    private String description;
    private LocalDate by;

    public DeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task temp = new Deadline(description, by);
        taskList.addTask(temp);
        ui.printAddTaskMessage(temp, taskList);
        storage.save(taskList);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof DeadlineCommand) {
            DeadlineCommand temp = (DeadlineCommand) obj;
            if (temp.description.equals(temp.description) && this.by.equals(temp.by)) {
                return true;
            }
        }
        return false;
    }
}
