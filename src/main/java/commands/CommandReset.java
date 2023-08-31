package commands;

import client.Rock;
import io.Parser;

public class CommandReset extends Command {
    public CommandReset(Rock client) {
        super(client);
    }
    /**
     * Terminates chatbot.
     * @param input Unused.
     */
    @Override
     public void accept(Parser input) {
        this.client.getTaskList().reset();
        this.client.getStorage().saveSaveFile();
        this.client.getUi().respond("Task List reset.");
    }
}
