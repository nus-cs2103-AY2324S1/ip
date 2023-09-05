package duke.command;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.JTextArea;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * Represents a command to add a new task.
 */
public class AddCommand extends Command {
    private final Command.TaskType taskType;
    private final String taskDescription;
    private final String additionalInfo1;
    private final String additionalInfo2;

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

    /**
     * Executes the AddCommand, adding a new task to the task list.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The data storage.
     * @param chatArea JTextArea for displaying messages in the GUI.
     */
    @Override
    public void doCommand(ArrayList<Task> tasks, Ui ui, Storage storage, JTextArea chatArea) {
        try {
            if (taskDescription.isEmpty()) {
                throw new DukeException("Unable to add new task. Task description cannot be empty.");
            }

            Task newTask;

            switch (taskType) {
            case TODO:
                newTask = new Todo(taskDescription);
                break;
            case DEADLINE:
                LocalDate byDate = Parser.parseDate(additionalInfo1, chatArea);
                newTask = new Deadline(taskDescription, byDate);
                break;
            case EVENT:
                LocalDateTime fromDate = Parser.parseDateTime(additionalInfo1, chatArea);
                LocalDateTime toDate = Parser.parseDateTime(additionalInfo2, chatArea);
                if (fromDate == null || toDate == null) {
                    return;
                }
                newTask = new Event(taskDescription, fromDate, toDate);
                break;
            default:
                throw new DukeException("Unsupported task type.");
            }

            tasks.add(newTask);

            chatArea.append("Got it. I've added this task:\n");
            newTask.display(chatArea);
            chatArea.append("Now you have " + tasks.size() + " tasks in the list.\n");

            storage.saveTasks(tasks, chatArea);
        } catch (DukeException e) {
            chatArea.append(e.getMessage() + "\n");
        }
    }
}
