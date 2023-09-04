package jarvis.commands;

import java.time.LocalDateTime;

import jarvis.Parser;
import jarvis.Storage;
import jarvis.Ui;
import jarvis.exceptions.InvalidDateTimeFormatException;
import jarvis.exceptions.InvalidIndexException;
import jarvis.exceptions.InvalidTaskFormatException;
import jarvis.tasks.Deadline;
import jarvis.tasks.TaskList;

/**
 * Represents a command to add a "Deadline" task in the Jarvis app.
 * 
 */
public class DeadlineCommand implements Command {

    private String userInput;

    public DeadlineCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the deadline command by adding a new "Deadline" task to the task
     * list.
     *
     * @param taskList The TaskList containing the tasks.
     * @param ui       The Ui for user interface interactions.
     * @param storage  The Storage for saving tasks.
     * @throws InvalidIndexException      If an invalid index is provided.
     * @throws InvalidTaskFormatException If the task format is invalid.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage)
            throws InvalidIndexException, InvalidTaskFormatException {
        if (userInput.equalsIgnoreCase("deadline")) {
            throw new InvalidTaskFormatException(null);
        }
        int indexOfBy = userInput.indexOf("by");

        if (indexOfBy != 1 && indexOfBy <= userInput.length()) {
            try {
                String taskTitle = userInput.substring(9, indexOfBy).trim();
                String dueDate = userInput.substring(indexOfBy + 2).trim();
                LocalDateTime formattedDueDate = Parser.parseDateTime(dueDate);
                Deadline deadline = new Deadline(taskTitle, formattedDueDate, false);
                taskList.addTask(deadline);
                storage.saveTasks(taskList.getTaskList());
                ui.printResponse("Yes Master! I've added this task: \n" + "\t" + deadline.toString() + "\n" +
                        "    Master, you have " + taskList.getTaskCount() + " tasks in the list.");
            } catch (InvalidDateTimeFormatException e) {
                System.err.println(e.getMessage());
            }
        } else {
            throw new InvalidIndexException(null);
        }
    }
}
