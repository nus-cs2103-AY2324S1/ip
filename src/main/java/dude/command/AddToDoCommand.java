package dude.command;

import dude.Storage;
import dude.TaskList;
import dude.Ui;
import dude.task.ToDo;

import java.io.IOException;

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
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            System.out.println("Executing Add ToDo Command");
            ToDo newTask = new ToDo(taskDescription);
            taskList.addTask(newTask);
            int nTasks = taskList.getSize();
            ui.showAddedTask(newTask, nTasks);
            storage.saveTasksToDisk(taskList);
        } catch (IOException e) {
            System.out.println("Error in Add ToDo Command");
        }
    }
}
