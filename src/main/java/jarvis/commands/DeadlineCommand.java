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

public class DeadlineCommand implements Command {

    private String userInput;

    public DeadlineCommand(String userInput) {
        this.userInput = userInput;
    }

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
