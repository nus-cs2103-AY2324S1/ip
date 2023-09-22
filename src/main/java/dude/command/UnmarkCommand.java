package dude.command;

import dude.Storage;
import dude.note.NoteList;
import dude.task.Task;
import dude.task.TaskList;
import dude.ui.Ui;

/**
 * Represents a command that marks a task as undone.
 */
public class UnmarkCommand extends Command {
    private int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command to mark a task as undone.
     *
     * @param taskList List of tasks.
     * @param storage Storage containing saved tasks, and saves and loads tasks.
     * @param ui User interface of Dude.
     */
    public String execute(TaskList taskList, NoteList noteList, Ui ui, Storage storage) {
        Task unmarkedTask = taskList.unmarkTask(taskIndex);
        String output = ui.showUnmarkedTask(unmarkedTask) + "\n";
        storage.saveToDisk(taskList, noteList);
        return output;
    }
}
