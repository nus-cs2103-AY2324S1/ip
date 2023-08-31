package duke.parse.command;

import duke.Duke;

/**
 * Represents a command to mark a task as done / not done.
 */
public class MarkCommand implements Command {
    private boolean isDone;
    private int index;

    /**
     * Instantiates the mark command
     * @param isDone whether the target task should be mark done,
     *               true if it is, false if to be marked not done
     * @param index the index of the task in the task list
     */
    public MarkCommand(boolean isDone, int index) {
        this.isDone = isDone;
        this.index = index;
    }

    /**
     * Commands the bot to list out the tasks with the given filters
     * @param bot the bot to execute this command
     * @return true, as this allows the user to continue the programme
     */
    @Override
    public boolean execute(Duke bot) {
        if (this.isDone) {
            bot.markTaskAsDone(this.index);
        } else {
            bot.markTaskAsNotDone(this.index);
        }
        return true;
    }

    /**
     * Checks whether this mark command is the same as another, for testing purposes.
     * They are the same if both are to mark the same task,
     * and both mark the task in the same way (done / not done).
     * @param another the object to compare against
     * @return whether this mark command is the same as another
     */
    @Override
    public boolean equals(Object another) {
        if (another instanceof MarkCommand) {
            MarkCommand anotherMark = (MarkCommand) another;
            return this.isDone == anotherMark.isDone
                    && this.index == anotherMark.index;
        }
        return false;
    }
}
