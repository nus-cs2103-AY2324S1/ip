package dude.command;

import java.time.LocalDateTime;

import dude.Storage;
import dude.note.NoteList;
import dude.task.Deadline;
import dude.task.TaskList;
import dude.ui.Ui;

/**
 * Represents a command that adds a Deadline task.
 */
public class AddDeadlineCommand extends Command {
    private String taskDescription;
    private LocalDateTime byDateTime;

    /**
     * Creates an Add Deadline Command to add a deadline to the list.
     * @param taskDescription Short description of deadline to be added.
     * @param byDateTime Time that task needs to be completed.
     */
    public AddDeadlineCommand(String taskDescription, LocalDateTime byDateTime) {
        this.taskDescription = taskDescription;
        this.byDateTime = byDateTime;
    }

    /**
     * Executes the command to add a Deadline task.
     *
     * @param taskList List of tasks.
     * @param storage Storage containing saved tasks, and saves and loads tasks.
     * @param ui User interface of Dude.
     */
    @Override
    public String execute(TaskList taskList, NoteList noteList, Ui ui, Storage storage) {
        Deadline newTask = new Deadline(taskDescription, byDateTime);
        assert !newTask.isDone() : "Newly added Deadline should not be done.";
        taskList.addTask(newTask);
        int nTasks = taskList.getSize();
        String output = ui.showAddedTask(newTask, nTasks) + "\n";
        storage.saveToDisk(taskList, noteList);
        return output;
    }
}
