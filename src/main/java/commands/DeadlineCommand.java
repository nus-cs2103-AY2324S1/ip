package commands;

import functions.TaskList;
import tasks.Deadline;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command{
    private TaskList taskList;
    private String secondHalfInput;

    public DeadlineCommand(TaskList taskList, String secondHalfInput) {
        this.taskList = taskList;
        this.secondHalfInput = secondHalfInput;
    }

    @Override
    public String execute() {
        try {
            String[] deadlineInputArray = this.secondHalfInput.split("/");
            String deadlineDescription = deadlineInputArray[0].substring(0,deadlineInputArray[0].length()-1);
            String deadlineDateString = deadlineInputArray[1].substring(3);

            LocalDateTime deadlineDate = parseDateTime(deadlineDateString);
            if (deadlineDate == null) {
                return "Error in parsing date.";
            }

            Deadline newDeadline = new Deadline(deadlineDescription, deadlineDate);
            this.taskList.add(newDeadline);
            String message = "Added: " + newDeadline.getTaskAsString();
            return message;
        } catch (Exception e ) {
            return "Sorry, I did not understand that. Please enter in the following format: \n" +
                    "deadline {description} /by {deadline}.";
        }
    }
}
