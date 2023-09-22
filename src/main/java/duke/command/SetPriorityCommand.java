package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidArgumentException;
import duke.util.Priority;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Command sets the priority of a task
 */
public class SetPriorityCommand extends Command {
    private final int taskNumber;
    private final int priorityInt;
    private Priority priority;

    /**
     * Constructs a command containing the tasknumber in list to change priority of.
     * @param taskNumber The position of task in the list to be deleted
     * @param priorityInt The priority level the task should be changed to
     */
    public SetPriorityCommand(int taskNumber, int priorityInt) {
        super();
        this.taskNumber = taskNumber;
        this.priorityInt = priorityInt;
    }

    /**
     * Executes the set priority command.
     * @param taskList list of tasks
     * @param ui ui component of the program
     * @param storage storage component of the program
     * @throws DukeException Errors that occur when trying to delete the task
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        convertPriority();
        String s = taskList.setPriority(taskNumber, priority, ui);
        storage.rewriteFile(taskList);
        return s;
    }

    /**
     * Checks if the current task is an exit task.
     * @return false since task is not an exit task
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Helps convert an int to an enum.
     * @throws InvalidArgumentException Error as a result of an invalid priority number
     */
    private void convertPriority() throws InvalidArgumentException {
        switch (priorityInt) {
        case 1:
            this.priority = Priority.LOW;
            break;
        case 2:
            this.priority = Priority.MEDIUM;
            break;
        case 3:
            this.priority = Priority.HIGH;
            break;
        default:
            throw new InvalidArgumentException("Please enter a priority from 1-3");
        }
    }
}
