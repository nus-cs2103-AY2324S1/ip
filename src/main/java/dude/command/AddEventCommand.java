package dude.command;

import dude.Storage;
import dude.TaskList;
import dude.Ui;
import dude.task.Event;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Represents a command that adds an Event task.
 */
public class AddEventCommand extends Command {
    private String taskDescription;
    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;

    public AddEventCommand(String taskDescription, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        this.taskDescription = taskDescription;
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    /**
     * Executes the command to add an Event task.
     *
     * @param taskList List of tasks.
     * @param storage Storage containing saved tasks, and saves and loads tasks.
     * @param ui User interface of Dude.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            System.out.println("Executing Add Event Command");
            Event newTask = new Event(taskDescription, fromDateTime, toDateTime);
            taskList.addTask(newTask);
            int nTasks = taskList.getSize();
            ui.showAddedTask(newTask, nTasks);
            storage.saveTasksToDisk(taskList);
        } catch (IOException e) {
            System.out.println("Error in Add Event Command");
        }
    }
}
