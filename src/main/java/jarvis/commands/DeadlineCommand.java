package jarvis.commands;

import java.time.LocalDateTime;

import jarvis.exceptions.InvalidDateTimeFormatException;
import jarvis.exceptions.InvalidIndexException;
import jarvis.exceptions.InvalidTaskFormatException;
import jarvis.ui.Ui;
import jarvis.parser.Parser;
import jarvis.storage.Storage;
import jarvis.tasks.Deadline;
import jarvis.tasks.TaskList;

/**
 * Represents a command to add a "Deadline" task in the Jarvis app.
 */
@SuppressWarnings("checkstyle:DeclarationOrder")
public class DeadlineCommand implements Command {

    private static final int COMMAND_LENGTH = 9;
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
    public String execute(TaskList taskList, Ui ui, Storage storage)
            throws InvalidIndexException, InvalidTaskFormatException {
        if (userInput.equalsIgnoreCase("deadline")) {
            throw new InvalidTaskFormatException(null);
        }
        assert taskList != null && ui != null && storage != null;
        int indexOfBy = userInput.indexOf("by");
        if (indexOfBy == 1) {
            throw new InvalidIndexException(null);
        }

        try {
            return setDeadline(taskList, ui, storage, indexOfBy);
        } catch (InvalidDateTimeFormatException e) {
            return e.getMessage();
        }
    }

    private String setDeadline(TaskList taskList, Ui ui, Storage storage, int indexOfBy)
            throws InvalidDateTimeFormatException {
        String taskTitle = userInput.substring(COMMAND_LENGTH, indexOfBy).trim();
        String dueDate = userInput.substring(indexOfBy + 2).trim();
        LocalDateTime formattedDueDate = Parser.parseStringToDateTime(dueDate);
        System.out.println(formattedDueDate);
        Deadline deadline = new Deadline(taskTitle, formattedDueDate, false);
        taskList.addTask(deadline);
        storage.saveTasks(taskList.getTaskList());
        return ui.printResponse("Yes Master! I've added this task: \n" + "\t" + deadline.toString() + "\n"
                + "    Master, you have " + taskList.getTaskCount() + " tasks in the list.");
    }
}
