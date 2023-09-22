package dude.command;

import java.io.IOException;
import java.time.LocalDateTime;

import dude.Storage;
import dude.note.NoteList;
import dude.task.Event;
import dude.task.TaskList;
import dude.ui.Ui;

/**
 * Represents a command that adds an Event task.
 */
public class AddEventCommand extends Command {
    private String taskDescription;
    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;

    /**
     * Creates an Add Event Command to add an event to the list.
     * @param taskDescription Short description of event to be added.
     * @param fromDateTime Starting time of event.
     * @param toDateTime Ending time of event.
     */
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
    public String execute(TaskList taskList, NoteList noteList, Ui ui, Storage storage) {
        String output = "";
        try {
            output = "Executing Add Event Command\n";
            Event newTask = new Event(taskDescription, fromDateTime, toDateTime);
            assert !newTask.isDone() : "Newly added Event should not be done.";
            taskList.addTask(newTask);
            int nTasks = taskList.getSize();
            output = output + ui.showAddedTask(newTask, nTasks) + "\n";
            storage.saveToDisk(taskList, noteList);
        } catch (IOException e) {
            System.out.println("Error in Add Event Command");
        }
        return output;
    }
}
