package commands;

import client.Rock;
import io.Parser;

public class CommandReset extends Command{
    public CommandReset(Rock client) {
        super(client);
    }
    @Override
    /**
     * Terminates chatbot.
     * @param input Unused.
     */
    public void accept(Parser input) {
        this.client.taskList.reset();
        this.client.storage.saveSaveFile();
        this.client.ui.respond("Task List reset.");
    }
}
