package duke.command;

import java.time.LocalDateTime;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeBadInputException;
import duke.task.EventTask;
import duke.task.Task;

/**
 * Represents the Event command
 */
public class EventCommand extends Command {

    /**
     * Stores the description of the task
     */
    private String desc;

    /**
     * Stores the end date of the event
     */
    private LocalDateTime to;

    /**
     * Stores the start date of the event
     */
    private LocalDateTime from;

    /**
     * Constructor of the Event command
     *
     * @param from - the start date of the task
     * @param to   - the end date of the event
     * @param desc - desc of the task
     */
    public EventCommand(LocalDateTime from, LocalDateTime to, String desc) {
        this.desc = desc;
        this.from = from;
        this.to = to;
    }

    /**
     * Method to encapsulate the execution logic of the command
     *
     * @param taskList - the task list instance  of the current duke
     * @param ui       - the ui instance of DUKE
     * @param storage  - the storage instance to allow the command to write to the storage
     * @throws DukeBadInputException - if the input cannot be used
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeBadInputException {
        if (taskList.length() >= 100) {
            throw new DukeBadInputException("quack cannot remember any more tasks!!");
        }
        Task newTask = new EventTask(this.from, this.to, this.desc);
        taskList.add(newTask);
        if (!storage.writeToFile(newTask.getStored())) {
            ui.unexpectedError("unable to write to storage");
            return;
        }
        ui.println("Quack! I have added this task:");
        ui.println(newTask.toString());
        ui.println("Quack! Quack is currently remembering " + taskList.length() + " tasks.");

    }

    /**
     * Checks if the command is the exit command
     *
     * @return true if it is the exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Checks if it is the exact same command
     *
     * @param other the other command in question
     * @return true if there are equals
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof EventCommand) {
            EventCommand command = (EventCommand) other;
            return this.from.equals(command.from)
                    && this.to.equals(command.to)
                    && this.desc.equals(command.desc);
        }
        return false;
    }
}
