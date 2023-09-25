package chatbot;

import chatbot.commands.Command;
import chatbot.exceptions.ChatBotException;
import chatbot.parser.Parser;
import chatbot.storage.Storage;
import chatbot.task.TaskList;
import chatbot.ui.Printer;
/**
 * A chat bot that can be renamed, and responds to inputs from users
 *
 * @author Owen Yeo
 */
public class ChatBot {

    private Storage storage;
    private TaskList tasks;
    private Printer ui;

    /**
     * Constructs a ChatBot instance.
     */
    public ChatBot() {
        ui = new Printer();
        storage = new Storage("data/chatbot.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (ChatBotException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Returns the introduction of the chat bot.
     *
     * @return String of the introduction
     */
    public String showIntro() {
        return ui.intro();
    }

    /**
     * Responds to user input.
     *
     * @param input User input
     * @return String depending on user iput
     */
    public String getResponse(String input) {
        Command c = Parser.parse(input);
        return c.execute(tasks, storage, ui);
    }
}
