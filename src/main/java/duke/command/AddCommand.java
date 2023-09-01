package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * A class that represents all task addition commands that user can give
 */
public class AddCommand extends Command {
    private Task task;

    public AddCommand() {

    }

    /**
     * A method that executes the command that user gave
     * @params tasks TaskList containing all existing Task objects
     * @params ui UI interface that is used to print messages to the terminak
     * @params storage Storage object that houses database of the program
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(this.task);
        ui.addTask(tasks);
        storage.update(tasks);
    }

    /**
     * A class that represents the user command to add a Todo task
     */
    public class TodoCommand extends AddCommand {
        /**
         * Constructor for TodoCommand object
         * @param fullCommand command that user gave
         * @throws DukeException when command is unable to be processed properly
         */
        public TodoCommand(String fullCommand) throws DukeException {
            try {
                super.task = Todo.of(fullCommand);
            } catch (DukeException e) {
                throw e;
            }
        }
    }

    /**
     * A class that represents the user command to add a Deadline task
     */
    public class DeadlineCommand extends AddCommand {
        /**
         * Constructor for DeadlineCommand object
         * @param fullCommand command that user gave
         * @throws DukeException when command is unable to be processed properly
         */
        public DeadlineCommand(String fullCommand) throws DukeException {
            try {
                super.task = Deadline.of(fullCommand);
            } catch (DukeException e) {
                throw e;
            }
        }
    }

    /**
     * A class that represents the user command to add an Event task
     */
    public class EventCommand extends AddCommand {
        /**
         * Constructor for EventCommand object
         * @param fullCommand command that user gave
         * @throws DukeException when command is unable to be processed properly
         */
        public EventCommand(String fullCommand) throws DukeException {
            try {
                super.task = Event.of(fullCommand);
            } catch (DukeException e) {
                throw e;
            }
        }
    }
}
