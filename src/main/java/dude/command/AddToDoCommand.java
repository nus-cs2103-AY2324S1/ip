package dude.command;

import java.io.IOException;

import dude.note.NoteList;
import dude.Storage;
import dude.task.TaskList;
import dude.ui.Ui;
import dude.task.ToDo;

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
        String output = "";
        try {
            output = "Executing Add ToDo Command\n";
            ToDo newTask = new ToDo(taskDescription);
            assert !newTask.isDone() : "Newly added ToDo should not be done.";
            taskList.addTask(newTask);
            int nTasks = taskList.getSize();
            output = output + ui.showAddedTask(newTask, nTasks) + "\n";
            storage.saveTasksToDisk(taskList, noteList);
        } catch (IOException e) {
            System.out.println("Error in Add ToDo Command");
        }
        return output;
    }
}
