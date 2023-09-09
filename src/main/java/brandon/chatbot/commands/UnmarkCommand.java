package brandon.chatbot.commands;

import java.io.IOException;

import brandon.chatbot.common.DukeIndexOutOfBoundsException;

/**
 * Represents the command that unmarks the finished status of a task.
 */
public class UnmarkCommand extends Command {
    public static final String UNMARK_SUCCESS = "ok... I'm unmarking...";
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }
    @Override
    public CommandResult execute() throws DukeIndexOutOfBoundsException, IOException {
        tasks.unmark(index);
        return new CommandResult(UNMARK_SUCCESS);
    }
}
