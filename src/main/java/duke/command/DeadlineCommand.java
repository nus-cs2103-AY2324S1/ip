package duke.command;

import java.time.LocalDateTime;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeBadInputException;
import duke.task.DeadlineTask;
import duke.task.Task;

/**
 * Represents the Deadline command
 */
public class DeadlineCommand extends Command {

    /**
     * Stores the description of the task
     */
    private String desc;

    /**
     * Stores the deadline of the task
     */
    private LocalDateTime by;
    /**
     * Constructor of the duke.task.DeadlineTask command
     *
     * @param by   - the due date of the task
     * @param desc - desc of the task
     */
    public DeadlineCommand(LocalDateTime by, String desc) {
        this.desc = desc;
        this.by = by;
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
        Task newTask = new DeadlineTask(this.by, this.desc);
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
        if (other instanceof DeadlineCommand) {
            DeadlineCommand command = (DeadlineCommand) other;
            return this.by.equals((command.by)) && this.desc.equals(command.desc);
        }
        return false;
    }
}
