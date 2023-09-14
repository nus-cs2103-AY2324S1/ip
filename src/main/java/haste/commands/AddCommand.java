package haste.commands;

import java.time.LocalDateTime;

import haste.data.TaskList;
import haste.tasks.Deadline;
import haste.tasks.Event;
import haste.tasks.Task;
import haste.tasks.ToDo;
import haste.ui.Ui;

/**
 * Represents a command that adds tasks to a TaskList.
 */
public class AddCommand extends Command {

    private String taskDesc;
    private LocalDateTime start;
    private LocalDateTime end;
    private String taskType;

    /**
     * Creates an AddCommand for ToDo.
     *
     * @param desc Description of the task.
     */
    public AddCommand(String desc) {
        this.taskDesc = desc;
        this.taskType = "t";
    }

    /**
     * Creates an AddCommand for Deadline.
     *
     * @param desc Description of the task.
     * @param end Deadline of the task.
     */
    public AddCommand(String desc, LocalDateTime end) {
        this.taskDesc = desc;
        this.taskType = "d";
        this.end = end;
    }

    /**
     * Creates an AddCommand for Event.
     *
     * @param desc Description of the task.
     * @param end Deadline of the task.
     * @param start Start time of the task.
     */
    public AddCommand(String desc, LocalDateTime end, LocalDateTime start) {
        this.taskDesc = desc;
        this.taskType = "e";
        this.start = start;
        this.end = end;
    }

    /**
     * Execute the command.
     *
     * @param tasks TaskList containing the tasks.
     * @param ui Ui that handles interactions.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        Task newTask = null;
        switch (this.taskType) {
        case "t":
            newTask = new ToDo(this.taskDesc, false);
            break;
        case "d":
            newTask = new Deadline(this.taskDesc, this.end, false);
            break;
        case "e":
            newTask = new Event(this.taskDesc, this.start, this.end, false);
            break;
        default:
            newTask = new ToDo("error", false);
        }
        
        assert !newTask.equals(defaultTask) : "task allocation error";

        tasks.addTask(newTask);
        return ui.add(newTask.toString(), tasks);
    }
}
