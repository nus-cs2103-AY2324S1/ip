package shiba.commands;

import java.util.Random;

import shiba.tasks.FilePersistentTaskList;
import shiba.ui.Replier;

/**
 * Represents a command that gives the bot belly rubs.
 */
public class BellyRubCommand extends ShibaCommand {
    private static final Random RANDOM = new Random();
    private static final String REPLY_MSG_1 = "Woof! More please!";
    private static final String REPLY_MSG_2 = "<Happy Shiba noises>";

    /**
     * Constructor for BellyRubCommand.
     *
     * @param tasks Current state of task list
     */
    public BellyRubCommand(FilePersistentTaskList tasks) {
        super(tasks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        Replier.printWithNoIndents(RANDOM.nextBoolean() ? REPLY_MSG_1 : REPLY_MSG_2);
        Replier.reply();
    }
}
