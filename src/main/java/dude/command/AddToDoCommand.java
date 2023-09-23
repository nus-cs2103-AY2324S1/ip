package dude.command;

import dude.note.NoteList;
import dude.task.TaskList;
import dude.task.ToDo;
import dude.ui.Ui;
import dude.util.Storage;

/**
 * Represents a command that adds a ToDo task.
 */
public class AddToDoCommand extends Command {
    private String taskDescription;

    public AddToDoCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * Executes the command to add a ToDo task.
     *
     * @param taskList List of tasks.
     * @param storage Storage containing saved tasks, and saves and loads tasks.
     * @param ui User interface of Dude.
     */
    @Override
    public String execute(TaskList taskList, NoteList noteList, Ui ui, Storage storage) {
        ToDo newTask = new ToDo(taskDescription);
        assert !newTask.isDone() : "Newly added ToDo should not be done.";
        taskList.addTask(newTask);
        int nTasks = taskList.getSize();
        String output = ui.showAddedTask(newTask, nTasks) + "\n";
        storage.saveToDisk(taskList, noteList);
        return output;
    }
}
