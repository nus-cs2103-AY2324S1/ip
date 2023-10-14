package shiba.commands;

import java.util.Random;

import shiba.tasks.PersistentTaskList;
import shiba.ui.Replier;

/**
 * Represents a command that pats the bot
 */
public class PatCommand extends ShibaCommand {
    private static final Random RANDOM = new Random();
    private static final String REPLY_MSG_1 = "Woof! I'm so happy!";
    private static final String REPLY_MSG_2 = "<Happy Shiba noises>";

    /**
     * Constructor for PatCommand
     *
     * @param tasks Current state of task list
     */
    public PatCommand(PersistentTaskList tasks) {
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
