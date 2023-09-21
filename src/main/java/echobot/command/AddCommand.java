package echobot.command;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import echobot.parser.Parser;
import echobot.storage.Storage;
import echobot.task.Deadline;
import echobot.task.Event;
import echobot.task.Task;
import echobot.task.Todo;
import javafx.scene.layout.VBox;

/**
 * Represents a command to add a new task.
 */
public class AddCommand extends Command<Task> {
    private final Command.TaskType taskType;
    private final String taskDescription;
    private final String dateTimeInfo1;
    private final String dateTimeInfo2;
    private String responseText;

    /**
     * Constructs an AddCommand instance.
     *
     * @param taskType        The type of the task.
     * @param taskDescription The description of the task.
     * @param dateTimeInfo1   Datetime additional information.
     * @param dateTimeInfo2   Datetime additional information.
     */
    public AddCommand(Command.TaskType taskType, String taskDescription,
            String dateTimeInfo1, String dateTimeInfo2) {
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.dateTimeInfo1 = dateTimeInfo1;
        this.dateTimeInfo2 = dateTimeInfo2;
    }

    @Override
    public String doCommand(ArrayList<Task> tasks, Storage storage, VBox dialogContainer) {
        String errorMessage = validateInput();

        if (errorMessage != null) {
            responseText = errorMessage;
        } else {
            Task newTask;

            switch (taskType) {
            case TODO:
                newTask = new Todo(taskDescription);
                break;
            case DEADLINE:
                LocalDate byDate = Parser.parseDate(dateTimeInfo1);
                newTask = new Deadline(taskDescription, byDate);
                break;
            case EVENT:
                LocalDateTime fromDate = Parser.parseDateTime(dateTimeInfo1);
                LocalDateTime toDate = Parser.parseDateTime(dateTimeInfo2);

                newTask = new Event(taskDescription, fromDate, toDate);
                break;
            default:
                assert false : "Unsupported task type";
                return "Unsupported task type.";
            }

            tasks.add(newTask);

            responseText = "Got it. I've added this task:\n";
            responseText += newTask.display() + "\n";
            responseText += "Now you have " + tasks.size() + " tasks in the list.\n";

            storage.saveTasks(tasks, dialogContainer);
        }

        return responseText;
    }

    private String validateInput() {
        if ((taskType == TaskType.TODO && taskDescription.isBlank() || taskDescription.isEmpty())) {
            return "Unable to add a new " + taskType.toString().toLowerCase()
                    + " since the description is empty";
        }

        return null; // Input is valid
    }

    public String getResponse() {
        return responseText;
    }
}
