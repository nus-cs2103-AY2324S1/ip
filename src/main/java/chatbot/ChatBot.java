package chatbot;

import chatbot.commands.Command;
import chatbot.parser.Parser;
import chatbot.exceptions.ChatBotException;
import chatbot.storage.Storage;
import chatbot.task.TaskList;
import chatbot.ui.Printer;
/**
 * A chat bot that can be renamed, and responds to inputs from users
 * 
 * @author Owen Yeo
 * Version Level-10
 */
public class ChatBot {

    private Storage storage;
    private TaskList tasks;
    private Printer ui;

    private static final String FILE_PATH = "src/main/java/data/chatBot.txt";

    //Constructor that allows for the naming of your own bot.
    public ChatBot() {
        ui = new Printer();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (ChatBotException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
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
