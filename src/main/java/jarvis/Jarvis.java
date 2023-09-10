package jarvis;

import java.util.Scanner;

import jarvis.commands.Command;
import jarvis.exceptions.JarvisException;
import jarvis.tasks.TaskList;

/**
 * The main class for Jarvis application, a CLI chatbot.
 * It initialises and manages the core components of the application.
 * Namely: ui, storage, taskList, and commands.
 */
public class Jarvis {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * Constructor for Jarvis class.
     */
    public Jarvis() {
        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage();
        taskList.setTasks(storage.loadTasks());
    }

    /**
     * Starts Jarvis by printing the introductory message.
     */
    public void start() {
        ui.printIntro();
    }

    /**
     * Responds to user input by parsing and executing commands.
     *
     * @param userInput The input provided by the user through CLI.
     */
    public void respond(final String userInput) {
        try {
            Command command = Parser.parseCommand(userInput);
            command.execute(taskList, ui, storage);
        } catch (JarvisException e) {
            ui.printError(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Jarvis jarvis = new Jarvis();
        jarvis.start();

        Scanner scanner = new Scanner(System.in);
        String userInput;

        try {
            while (true) {
                userInput = scanner.nextLine();
                jarvis.respond(userInput);
            }
        } finally {
            scanner.close();
        }
    }
}
