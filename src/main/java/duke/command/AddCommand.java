package duke.command;

import java.time.DateTimeException;

import duke.main.Storage;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todos;

/**
 * A class for the command for adding new task to list based on user input.
 */
public class AddCommand extends Command {

    /**
     * The type of task being added; todo, deadline or event.
     */
    private final String commandType;

    /**
     * The description of task being added.
     */
    private final String description;

    /**
     * The starting date for the task being added.
     */
    private final String startDate;

    /**
     * The ending date for the task being added.
     */
    private final String endDate;

    /**
     * The constructor for AddCommand
     *
     * @param commandType The type of task being added; todo, deadline or event
     * @param description The description of task being added
     * @param startDate   The starting date for the task being added
     * @param endDate     The ending date for the task being added
     */
    public AddCommand(String commandType, String description, String startDate, String endDate) {
        this.commandType = commandType;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        Task temp = null;
        switch (commandType) {
        case "T":
            temp = new Todos(this.description);
            break;
        case "D":
            try {
                temp = new Deadlines(this.description, this.endDate);
                break;
            } catch (DateTimeException e) {
                return "JonBird:\n\t" + "Please ensure that your date is in \"yyyy-MM-dd HH:mm\""
                        + " format. Put 00:00 if time does not matter.";
            }

        case "E":
            try {
                temp = new Events(this.description, this.startDate, this.endDate);
                break;
            } catch (DateTimeException e) {
                return "JonBird:\n\t" + "Please ensure that your date is in \"yyyy-MM-dd HH:mm\""
                        + " format. Put 00:00 if time does not matter.";
            }
        default:
            return "Something went wrong. Please try again.";
        }
        taskList.addTask(temp);
        storage.writeData(taskList.convertToFileContent());
        return this.printCommand(taskList);
    }

    @Override
    public String printCommand(TaskList taskList) {
        return "JonBird:\n\tGot it. I've added this task:\n"
                + "\t\t" + taskList.getTask(taskList.size() - 1).printTask()
                + "\n\tNow you have " + taskList.size() + " tasks in the list.";
    }

    @Override
    public boolean isContinue() {
        return true;
    }
}
