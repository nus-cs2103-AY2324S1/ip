package Jelly.main;

import java.util.Scanner;

import Jelly.commands.Command;
import Jelly.exceptions.JellyException;

/**
 * The main class which is responsible in running the Jelly chatbot.
 */
public class Jelly {
    private static final String FILE_PATH = "./taskData/jelly.txt";
    private final Scanner scanner = new Scanner(System.in);

    private TaskList taskList;
    private final Ui ui;
    private final Storage storage;

    /**
     * Constructor for an instance of Jelly.
     *
     * @param filePath The file path used when saving or starting up the bot. Contains a list of tasks(if any).
     * @throws JellyException If there are any errors while starting up Jelly.
     */
    private Jelly(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();

        try {
            this.taskList = new TaskList(storage.startUp());
        } catch (JellyException e) {
            this.taskList = new TaskList();
            System.out.println(e.getMessage());
        }
    }

    /**
     * The main initialiser for the Jelly Chat bot.
     *
     * @param args
     * @throws JellyException
     */
    public static void main(String[] args) throws JellyException {
        Jelly jelly = new Jelly(FILE_PATH);
        jelly.run();
    }

    /**
     * Runs the commands given to the Jelly Chat bot.
     */
    private void run() {

        ui.startUpMessage();
        boolean isRunning = true;

        while (isRunning) {
            try {
                String command = ui.commandMe();
                Command c = Parser.parse(command);
                c.execute(taskList, ui, storage);
                isRunning = c.isRunning();
            } catch (JellyException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
