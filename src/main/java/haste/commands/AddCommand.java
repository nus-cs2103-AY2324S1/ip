package haste.commands;

import haste.data.TaskList;
import haste.tasks.Deadline;
import haste.tasks.Event;
import haste.tasks.Task;
import haste.tasks.ToDo;
import haste.ui.Ui;

import java.time.LocalDateTime;

/**
 * Represents a command that adds tasks to a TaskList.
 */
public class AddCommand extends Command {

    private String desc;
    private LocalDateTime start;
    private LocalDateTime end;
    private String type;

    /**
     * Creates an AddCommand for ToDo.
     *
     * @param desc Description of the task.
     */
    public AddCommand(String desc) {
        this.desc = desc;
        this.type = "t";
    }

    /**
     * Creates an AddCommand for Deadline.
     *
     * @param desc Description of the task.
     * @param end Deadline of the task.
     */
    public AddCommand(String desc, LocalDateTime end) {
        this.desc = desc;
        this.type = "d";
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
        this.desc = desc;
        this.type = "e";
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
    public void execute(TaskList tasks, Ui ui) {
        Task newTask = null;
        switch (this.type) {
            case "t":
                newTask = new ToDo(this.desc, false);
                break;
            case "d":
                newTask = new Deadline(this.desc, this.end, false);
                break;
            case "e":
                newTask = new Event(this.desc, this.start, this.end, false);
                break;
        }
        tasks.addTask(newTask);
        ui.add(newTask.toString(), tasks);
    }
}
