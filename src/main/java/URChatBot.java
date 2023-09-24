

import urchatbot.commands.Command;
import urchatbot.exception.URChatBotException;
import urchatbot.parser.Parser;
import urchatbot.storage.Storage;
import urchatbot.taskList.TaskList;
import urchatbot.ui.Ui;

/**
 * The main entry point to the application.
 */
public class URChatBot {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    /**
     * Constructs the URChatBot class.
     *
     * @param filePath Path to store users' tasklist.
     */
    public URChatBot(String filePath) {
        //Setting up the bot
        ui = new Ui();
        storage = new Storage(filePath);
        //Load tasks
        try {
            tasks = new TaskList(storage.load());
        } catch (URChatBotException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts the chatbot.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (URChatBotException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (URChatBotException e) {
            return e.getMessage();
        }
    }

    /**
     * Instantiates a URChatBot.
     *
     * @param args Arguments.
     */
    public static void main(String[] args) {
        new URChatBot("data/tasks.txt").run();
    }
}
