package duke.command;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;
import duke.exception.DukeException;


/**
 * Represents the actions needed if the user inputs a deadline task
 */
public class DeadlineCommand extends Command {

    String inp;

    /**
     * Constructor for the deadline command.
     *
     * @param taskList TaskList object being used to maintain the list of tasks in the chatbot.
     * @param inp Line of input from the CLI
     */
    public DeadlineCommand(TaskList taskList, String inp) {
        super(taskList);
        this.inp = inp;
    }

    /**
     * Method to be executed when this command is called.
     * Initializes a new Deadline object and adds it to the taskList.
     */
    @Override
    public void execute() {
        try {
            String[] parts = inp.split("/", 2);
            String[] typeDescription = parts[0].split(" ", 2);
            String type = typeDescription[0];
            String description = typeDescription[1];
            Task newTask = new Deadline(description, parts[1]);
            taskList.add(newTask);
            System.out.println("Okay! Task added \n" + newTask);
        } catch (Exception e) {
            throw new DukeException("Please give a valid description for a Deadline task!");
        }
    }
}
