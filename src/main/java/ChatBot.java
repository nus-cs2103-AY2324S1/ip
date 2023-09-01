import chatbot.exceptions.ChatBotException;
import chatbot.parser.Parser;
import chatbot.storage.Storage;
import chatbot.task.TaskList;
import chatbot.ui.Ui;
import chatbot.commands.Command;

/**
 * A chat bot that can be renamed, and responds to inputs from users
 * 
 * @author Owen Yeo
 * Version Level-7
 */
public class ChatBot {

    private Ui ui;

    private Storage storage;

    private TaskList tasks;

    //Constructor that allows for the naming of your own bot.
    public ChatBot(String path) {
        ui = new Ui();
        storage = new Storage(path);
        try {
            tasks = new TaskList(storage.load());
        } catch (ChatBotException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }

    public void run() {
        try {
            ui.intro();
            boolean hasEnded = false;
            while (!hasEnded) {
                String fullCommand = ui.readInput();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, storage, ui);
                hasEnded = c.isExit();
            }
        } catch (ChatBotException e) {
            ui.showError(e);
        }
    }

    public static void main(String[] args) {
        //Test chatbot
        new ChatBot("src/main/java/data/chatBot.txt").run();
    }
}
