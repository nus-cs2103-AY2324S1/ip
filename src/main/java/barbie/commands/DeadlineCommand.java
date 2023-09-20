package barbie.commands;

import java.time.LocalDate;
import java.util.ArrayList;

import barbie.Storage;
import barbie.Ui;
import barbie.types.Deadlines;
import barbie.types.Task;

/**
 * Represents the command when a "deadline" is called by the user.
 */
public class DeadlineCommand extends Command {
    private String desc;
    private LocalDate by;

    /**
     * Constructs an instance of a DeadlineCommand, and saves the description and by date.
     * @param desc description of the task to complete
     * @param by the deadline for the task to be completed
     */
    public DeadlineCommand(String desc, LocalDate by) {
        this.desc = desc;
        this.by = by;
        this.isExit = false;

    }

    /**
     * Runs the command of this instance of Command.
     * (eg ExitCommand will contain the logic of exiting the application)
     * @param taskList current list of tasks
     * @return String to return to user
     */
    @Override
    public String run(ArrayList<Task> taskList) {
        Deadlines deadline = new Deadlines(this.desc, this.by);
        taskList.add(deadline);
        Storage.addToList(desc, by);
        return Ui.taskAdded(deadline);

    }

}
