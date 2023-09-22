package dude.command;

import java.io.IOException;

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
        String output = "";
        try {
            output = "Executing Unmark Command\n";
            Task unmarkedTask = taskList.unmarkTask(taskIndex);
            output = output + ui.showUnmarkedTask(unmarkedTask) + "\n";
            storage.saveToDisk(taskList, noteList);
        } catch (IOException e) {
            output = "Error in Unmark Command";
        }
        return output;
    }
}
