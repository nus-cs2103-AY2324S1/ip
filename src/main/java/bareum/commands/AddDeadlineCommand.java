package bareum.commands;

import bareum.BareumException;
import bareum.DeadlineTask;
import bareum.Storage;
import bareum.TaskList;
import bareum.Ui;

/**
 * This class implements the command for creating a new deadline.
 */
public class AddDeadlineCommand extends Command {
    /**
     * Description of the deadline.
     */
    private String description;
    /**
     * Due date of the deadline.
     */
    private String dueDate;


    /**
     * Create a new instance of a command that creates a deadline with the corresponding details when executed.
     * @param description Description of the deadline.
     * @param dueDate Due date of the deadline.
     */
    public AddDeadlineCommand(String description, String dueDate) {
        this.description = description;
        this.dueDate = dueDate;
    }

    /**
     * Create a new deadline with the corresponding description and due date.
     *
     * @param ui       Lets the user know if the creation of the deadline was successful.
     * @param storage  Saves the deadline to the hard disk after creating it.
     * @param taskList Task list to add the deadline to.
     * @return Response to user input.
     * @throws BareumException If due date is not in YYYY-MM-DD format.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) throws BareumException {
        try {
            DeadlineTask task = DeadlineTask.makeDeadline(this.description, this.dueDate);
            taskList.addTask(task);
            storage.saveNewTask(task);

            String added = "I have added this task:\n" + task + "\nYou now have "
                    + taskList.size() + " task(s) in your list.";
            return added;
        } catch (BareumException e) {
            return e.getMessage();
        }
    }
}
