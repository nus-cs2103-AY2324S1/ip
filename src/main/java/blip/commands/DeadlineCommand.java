package blip.commands;

import blip.priority.Priority;
import blip.ui.BlipUI;
import blip.tasks.TaskList;
import blip.tasks.Deadline;
import blip.storage.BlipStorage;


import java.time.LocalDateTime;

/**
 * Represents the deadline command to add a deadline task.
 */
public class DeadlineCommand extends Command {
    /**
     * Description of the deadline task.
     */
    private String description;

    Priority priority;

    /**
     * Deadline of the deadline task.
     */
    private LocalDateTime deadline;

    /**
     * Creates an instance of DeadlineCommand.
     *
     * @param description The description of the deadline task
     * @param deadline The deadline of the deadline task
     */
    public DeadlineCommand(String description, LocalDateTime deadline, Priority priority) {
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
    }

    /**
     * Executes the deadline command to add a deadline task.
     *
     * @param taskList The Array List of tasks to do add deadline task into
     * @param ui The user interface of Blip
     * @param storage The storage for Blip
     * @return String message shown to user.
     */
    @Override
    public String execute(TaskList taskList, BlipUI ui, BlipStorage storage) {
        Deadline deadlineTask = new Deadline(description, deadline, false, this.priority);
        taskList.addTask(deadlineTask);
        storage.saveToFile(taskList);
        return ui.addsTasksMsg(deadlineTask, taskList.size());
    }
}
