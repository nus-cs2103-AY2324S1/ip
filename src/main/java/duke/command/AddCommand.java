package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class AddCommand extends Command {
    private Task task;

    public AddCommand() {

    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(this.task);
        ui.addTask(tasks);
        storage.update(tasks);
    }

    public class TodoCommand extends AddCommand {
        public TodoCommand(String fullCommand) throws DukeException {
            try {
                super.task = Todo.of(fullCommand);
            } catch (DukeException e) {
                throw e;
            }
        }
    }

    public class DeadlineCommand extends AddCommand {
        public DeadlineCommand(String fullCommand) throws DukeException {
            try {
                super.task = Deadline.of(fullCommand);
            } catch (DukeException e) {
                throw e;
            }
        }
    }

    public class EventCommand extends AddCommand {
        public EventCommand(String fullCommand) throws DukeException {
            try {
                super.task = Event.of(fullCommand);
            } catch (DukeException e) {
                throw e;
            }
        }
    }
}
