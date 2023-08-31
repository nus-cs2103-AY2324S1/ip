package duke.command;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;
import duke.exception.DukeException;


/**
 * Represents the actions needed if the user inputs an event task
 */
public class EventCommand extends Command {

    String inp;

    /**
     * Constructor for the event command.
     *
     * @param taskList TaskList object being used to maintain the list of tasks in the chatbot.
     * @param inp Line of input from the CLI
     */
    public EventCommand(TaskList taskList, String inp) {
        super(taskList);
        this.inp = inp;
    }

    /**
     * Method to be executed when this command is called.
     * Initializes a Event object and adds it to the taskList.
     */
    @Override
    public void execute() {
        try {
            String[] parts = inp.split("/", 3);
            String[] type_description = parts[0].split(" ", 2);
            String type = type_description[0];
            String description = type_description[1];
            String from = parts[1].trim().split(" ", 2)[1];
            String to = parts[2].trim().split(" ", 2)[1];
            Task newTask = new Event(description, from, to);
            taskList.add(newTask);
            System.out.println("Okay! Task added \n" + newTask);
        } catch (Exception e) {
            System.out.println(e);
            throw new DukeException("Please give a valid description for an Event task!");
        }
    }
}