package Commands;
import Duke.DukeException;
import OOP.TaskList;
import OOP.Ui;
import OOP.Storage;
import Tasks.Deadline;
public class AddDeadlineCommand implements Command {
    /** The deadline to be added to the task upon execution.*/
    private Deadline deadline;

    /**
     * Constructs a command that adds a deadline to the TaskList when executed.
     *
     * @param deadlineName The name of the deadline.
     * @param deadlineString The string to be parsed and converted into a LocalDate/LocalDateTime object.
     */
    public AddDeadlineCommand(String deadlineName, String deadlineString) {
        Deadline deadline = Storage.parseDeadlineFromString(deadlineName, deadlineString);
        this.deadline = deadline;
        assert !deadline.isDone();
    }

    public Deadline getDeadline() {
        return this.deadline;
    }

    /**
     * {@inheritDoc}
     *  Executes the command to add a deadline to the TaskList.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.deadline.getName().length() == 0) {
            throw new DukeException("Empty Description");
        }
        tasks.addTask(this.deadline);
        return ui.getTaskAddedMessage(this.deadline, tasks);
    }
}
