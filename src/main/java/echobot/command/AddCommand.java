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
import echobot.ui.Ui;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

/**
 * Represents a command to add a new task.
 */
public class AddCommand extends Command {
    private final Command.TaskType taskType;
    private final String taskDescription;
    private final String additionalInfo1;
    private final String additionalInfo2;
    private String responseText;

    /**
     * Constructs an AddCommand instance.
     *
     * @param taskType        The type of the task.
     * @param taskDescription The description of the task.
     * @param additionalInfo1 Additional information required for specific task types.
     * @param additionalInfo2 Additional information required for specific task types.
     */
    public AddCommand(Command.TaskType taskType, String taskDescription,
            String additionalInfo1, String additionalInfo2) {
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.additionalInfo1 = additionalInfo1;
        this.additionalInfo2 = additionalInfo2;
    }

    @Override
    public String doCommand(ArrayList<Task> tasks, Ui ui, Storage storage, Scene scene, VBox dialogContainer) {
        System.out.println(taskDescription.isEmpty());
        if (taskDescription.isBlank()) {
            responseText = "Unable to add a new todo since the description is empty";
        } else {
            Task newTask;

            switch (taskType) {
            case TODO:
                newTask = new Todo(taskDescription);
                break;
            case DEADLINE:
                LocalDate byDate = Parser.parseDate(additionalInfo1);
                newTask = new Deadline(taskDescription, byDate);
                break;
            case EVENT:
                LocalDateTime fromDate = Parser.parseDateTime(additionalInfo1);
                LocalDateTime toDate = Parser.parseDateTime(additionalInfo2);
//                if (fromDate == null || toDate == null) {
//                    return;
//                }
                newTask = new Event(taskDescription, fromDate, toDate);
                break;
            default:
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

    public String getResponse() {
        return responseText;
    }
}
