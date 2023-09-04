package shiba.commands;

import java.util.Random;

import shiba.tasks.FilePersistentTaskList;
import shiba.ui.Replier;

/**
 * Represents a command that gives the bot belly rubs.
 */
public class BellyRubCommand extends ShibaCommand {
    private static final Random random = new Random();
    private static final String msg1 = "Woof! More please!";
    private static final String msg2 = "<Happy Shiba noises>";

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
        Replier.printWithNoIndents(random.nextBoolean() ? msg1 : msg2);
        Replier.reply();
    }
}
