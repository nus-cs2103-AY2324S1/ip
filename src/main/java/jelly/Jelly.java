package jelly;

import jelly.commands.Command;
import jelly.exceptions.JellyException;
import jelly.main.Parser;
import jelly.main.Storage;
import jelly.main.TaskList;
import jelly.main.Ui;

/**
 * The main class which is responsible in running the Jelly chatbot.
 */
public class Jelly {
    private static final String FILE_PATH = "./taskData/jelly.txt";
    private TaskList taskList;
    private final Ui ui;
    private final Storage storage;

    /**
     * Constructor for an instance of Jelly.
     *
     * @param filePath The file path used when saving or starting up the bot. Contains a list of tasks(if any).
     * @throws Exception If there are any errors while starting up Jelly.
     */
    public Jelly(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        try {
            this.taskList = new TaskList(storage.startUp());
        } catch (JellyException e) {
            this.taskList = new TaskList();
            System.out.println(e.getMessage());
        }
    }
    public Jelly() {
        this(FILE_PATH);
    }

    /**
     * The main initialiser for the Jelly Chat Bot.
     *
     * @param args
     * @throws JellyException if there are any issues starting up the ChatBot.
     */
    public static void main(String[] args) throws JellyException {
        Jelly jelly = new Jelly(FILE_PATH);
        jelly.run();
    }
    /**
     * Runs the commands given to the Jelly Chat bot.
     */
    private void run() {

        boolean isRunning = true;

        while (isRunning) {
            String command = ui.commandMe();
            try {
                Command c = Parser.parse(command);
                c.execute(taskList, ui, storage);
                isRunning = c.isRunning();
            } catch (JellyException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String getResponse(String input) {
        String response;
        try {
            response = Parser.parse(input).execute(taskList, ui, storage);
        } catch (JellyException e) {
            return e.getMessage();
        }
        return response;
    }
}
