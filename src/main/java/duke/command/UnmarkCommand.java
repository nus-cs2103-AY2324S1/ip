package duke.command;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;
import duke.exception.DukeException;

/**
 * Represents the actions needed if the user inputs an unmark command.
 */
public class UnmarkCommand extends Command {

    String numberString;

    /**
     * Constructor for the unmark command.
     *
     * @param taskList TaskList object being used to maintain the list of tasks in the chatbot.
     * @param numberString Parsed number input from the CLI.
     */
    public UnmarkCommand(TaskList taskList, String numberString) {
        super(taskList);
        this.numberString = numberString;
    }

    /**
     * Method to be executed when this command is called.
     * Initializes a new Deadline object and adds it to the tasklist.
     */
    @Override
    public void execute() {
        Integer number = Integer.parseInt(this.numberString);
        Task task = taskList.unmark(number);
        System.out.println("YONG has unmarked this task successfully! \n" + task.toString() + "\n");
    }
}
