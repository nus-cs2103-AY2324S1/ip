package minion.commands;

import java.io.IOException;

import minion.data.TaskList;
import minion.data.task.Deadline;
import minion.storage.Storage;
import minion.ui.Ui;

/**
 * Represents a deadline command.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    private final Deadline deadline;

    /**
     * Constructs a deadline command object.
     * @param deadline Deadline of the command.
     */
    public DeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    /**
     * Executes the deadline command.
     * @param tasks Task list.
     * @param ui Ui of chatbot.
     * @param storage Storage of chatbot.
     * @throws IOException if there is IO error.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.add(deadline);
        ui.print(
                "Got it. I've added this task:",
                "\t" + deadline.toString(),
                "Now you have " + tasks.size() + " tasks in the list."
        );
        storage.writeToFile(tasks);
    }

    /**
     * Checks whether this object equals the other object.
     * @param o other object.
     * @return whether this object equals the other object.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof DeadlineCommand)) {
            return false;
        }
        DeadlineCommand c = (DeadlineCommand) o;
        return this.deadline.equals(c.deadline);
    }
}
