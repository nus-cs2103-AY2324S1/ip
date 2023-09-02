package didier.command;

import java.time.LocalDate;

import didier.Storage;
import didier.TaskList;
import didier.exception.DidierException;
import didier.task.Deadline;
import didier.task.Event;
import didier.task.Task;
import didier.task.ToDo;

/**
 * The AddCommand encapsulates the logic of what occurs when the user tries to add any type of task
 * to their task list.
 */
public class AddCommand extends Command {

    private String description;
    private LocalDate by;
    private LocalDate from;
    private LocalDate to;
    private Task task;

    /**
     * Constructor for the AddCommand object.
     *
     * @param description The description of the task.
     * @param by When the task should be completed by if it is a deadline else null.
     * @param from When the task begins if it is an event else null.
     * @param to When the task ends if it is an event else null.
     */
    public AddCommand(String description, LocalDate by, LocalDate from, LocalDate to) {
        this.description = description;
        this.by = by;
        this.from = from;
        this.to = to;
        this.task = null;
    }
    @Override
    public void execute(TaskList taskList, Storage storage) throws DidierException {
        if (this.from != null && this.to != null) {
            task = new Event(this.description, this.from, this.to);
        } else if (this.by != null) {
            task = new Deadline(this.description, this.by);
        } else {
            task = new ToDo(this.description);
        }
        taskList.addTask(task);
        storage.saveTasks(taskList);
    }

    @Override
    public String getBotOutput(TaskList taskList, Storage storage) {
        String outputText = "";
        if (task != null) {
            outputText += "Okay! I've added your task:";
            outputText += String.format("\n%s", task.toString());
            outputText += String.format("\nThere are now %d tasks in your list", taskList.getSize());
        }
        return outputText;
    }

}
