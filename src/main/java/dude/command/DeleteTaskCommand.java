package dude.command;

import dude.note.NoteList;
import dude.task.Task;
import dude.task.TaskList;
import dude.ui.Ui;
import dude.util.Storage;

/**
 * Represents a command that deletes task.
 */
public class DeleteTaskCommand extends Command {
    private int taskIndex;

    public DeleteTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command to delete a task.
     *
     * @param taskList List of tasks.
     * @param noteList List of notes.
     * @param storage Storage containing saved tasks, and saves and loads tasks.
     * @param ui User interface of Dude.
     */
    @Override
    public String execute(TaskList taskList, NoteList noteList, Ui ui, Storage storage) {
        Task deletedTask = taskList.deleteTask(taskIndex);
        int nTasks = taskList.getSize();
        String output = ui.showDeletedTask(deletedTask, nTasks) + "\n";
        storage.saveToDisk(taskList, noteList);
        return output;
    }
}
