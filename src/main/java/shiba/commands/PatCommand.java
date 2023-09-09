package shiba.commands;

import java.util.Random;

import shiba.tasks.PersistentTaskList;
import shiba.ui.Replier;

/**
 * Represents a command that pats the bot
 */
public class PatCommand extends ShibaCommand {
    private static final Random random = new Random();
    private static final String replyMsg1 = "Woof! I'm so happy!";
    private static final String replyMsg2 = "<Happy Shiba noises>";

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
        Replier.printWithNoIndents(random.nextBoolean() ? replyMsg1 : replyMsg2);
        Replier.reply();
    }
}
