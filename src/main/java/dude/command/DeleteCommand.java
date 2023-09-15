package dude.command;

import java.io.IOException;

import dude.NoteList;
import dude.Storage;
import dude.TaskList;
import dude.Ui;
import dude.task.Task;

/**
 * Represents a command that deletes task.
 */
public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command to delete a task.
     *
     * @param taskList List of tasks.
     * @param storage Storage containing saved tasks, and saves and loads tasks.
     * @param ui User interface of Dude.
     */
    @Override
    public String execute(TaskList taskList, NoteList noteList, Ui ui, Storage storage) {
        String output = "";
        try {
            output = "Executing Delete Command";
            Task deletedTask = taskList.deleteTask(taskIndex);
            int nTasks = taskList.getSize();
            output = output + ui.showDeletedTask(deletedTask, nTasks) + "\n";
            storage.saveTasksToDisk(taskList, noteList);
        } catch (IOException e) {
            output = "Error in Delete Command";
        }
        return output;
    }
}
