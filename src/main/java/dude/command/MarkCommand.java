package dude.command;

import java.io.IOException;

import dude.Storage;
import dude.note.NoteList;
import dude.task.Task;
import dude.task.TaskList;
import dude.ui.Ui;

/**
 * Represents a command that marks a task as done.
 */
public class MarkCommand extends Command {
    private int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command to mark a task as done.
     *
     * @param taskList List of tasks.
     * @param storage Storage containing saved tasks, and saves and loads tasks.
     * @param ui User interface of Dude.
     */
    @Override
    public String execute(TaskList taskList, NoteList noteList, Ui ui, Storage storage) {
        String output = "";
        try {
            output = "Executing Mark Command\n";
            Task markedTask = taskList.markTask(taskIndex);
            output = output + ui.showMarkedTask(markedTask) + "\n";
            storage.saveToDisk(taskList, noteList);
        } catch (IOException e) {
            output = "Error in Mark Command\n";
        }
        return output;
    }
}
