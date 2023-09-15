package dude.command;

import java.io.IOException;

import dude.NoteList;
import dude.Storage;
import dude.TaskList;
import dude.Ui;
import dude.task.Task;

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
            output = output + ui.showMarkedTask(markedTask);
            storage.saveTasksToDisk(taskList, noteList);
        } catch (IOException e) {
            output = "Error in Mark Command\n";
        }
        return output;
    }
}
