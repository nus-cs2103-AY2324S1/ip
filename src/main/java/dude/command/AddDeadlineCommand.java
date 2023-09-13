package dude.command;

import dude.Storage;
import dude.TaskList;
import dude.Ui;
import dude.task.Deadline;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Represents a command that adds a Deadline task.
 */
public class AddDeadlineCommand extends Command {
    private String taskDescription;
    private LocalDateTime byDateTime;

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
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            System.out.println("Executing Add Deadline Command");
            Deadline newTask = new Deadline(taskDescription, byDateTime);
            taskList.addTask(newTask);
            int nTasks = taskList.getSize();
            ui.showAddedTask(newTask, nTasks);
            storage.saveTasksToDisk(taskList);
        } catch (IOException e) {
            System.out.println("Error in Add Deadline Command");
        }
    }
}
