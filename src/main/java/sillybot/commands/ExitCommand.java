package sillybot.commands;

import java.io.IOException;

import sillybot.Storage;
import sillybot.Ui;
import sillybot.tasks.TaskList;

/**
 * Represents an ExitCommand object that handles the exit command.
 */
public class ExitCommand extends Command {
    public ExitCommand() {
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.save(tasks);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.exit(0);
        return null;
    }
}
