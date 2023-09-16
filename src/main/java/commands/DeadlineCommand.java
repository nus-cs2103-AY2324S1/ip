package commands;

import java.time.LocalDateTime;

import functions.TaskList;
import tasks.Deadline;



/**
 * The class for executing an addition command of a deadline task
 */
public class DeadlineCommand extends Command {
    private TaskList taskList;
    private String functionDescription;

    /**
     * Constructs a new DeadlineCommand object with the specified task list and function description.
     *
     * @param taskList The task list to add the deadline task to.
     * @param functionDescription The description of the deadline task to be added.
     */
    public DeadlineCommand(TaskList taskList, String functionDescription) {
        this.taskList = taskList;
        this.functionDescription = functionDescription;
    }

    @Override
    public String execute() {
        try {
            String[] deadlineInputArray = this.functionDescription.split("/");
            String deadlineDescription = deadlineInputArray[0].substring(0, deadlineInputArray[0].length() - 1);
            String deadlineDateString = deadlineInputArray[1].substring(3);

            LocalDateTime deadlineDate = parseDateTime(deadlineDateString);
            if (deadlineDate == null) {
                return "Error in parsing date.";
            }

            Deadline newDeadline = new Deadline(deadlineDescription, deadlineDate);
            this.taskList.add(newDeadline);
            String message = "Added: " + newDeadline.getTaskAsString();
            return message;
        } catch (Exception e) {
            return "Sorry, I did not understand that. Please enter in the following format: \n"
                    + "deadline {description} /by {deadline}.";
        }
    }
}
