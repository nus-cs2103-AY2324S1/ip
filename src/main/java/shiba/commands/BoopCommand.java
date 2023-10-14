package shiba.commands;

import java.util.Random;

import shiba.exceptions.ShibaException;
import shiba.tasks.PersistentTaskList;
import shiba.ui.Replier;

/**
 * Represents a command that gives SHIBA-BOT a nose boop.
 */
public class BoopCommand extends ShibaCommand {
    private static final Random RANDOM = new Random();
    private static final String REPLY_MSG_1 = "<Nuzzles you>";
    private static final String REPLY_MSG_2 = "<Boops you back>";

    /**
     * Constructor for BoopCommand.
     *
     * @param tasks Current state of task list
     */
    public BoopCommand(PersistentTaskList tasks) {
        super(tasks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() throws ShibaException {
        Replier.printWithNoIndents(RANDOM.nextBoolean() ? REPLY_MSG_1 : REPLY_MSG_2);
        Replier.reply();
    }
}
