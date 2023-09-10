package nexus;

import java.io.File;
import java.util.Scanner;

import nexus.components.Parser;
import nexus.components.Storage;
import nexus.components.Ui;
import nexus.exceptions.InvalidInputException;
import nexus.task.TaskList;

/**
 * The main class for Nexus ChatBot.
 */
public class Nexus {
    /**
     * OS-Independent path.
     */
    private static final String FILEPATH = String.join(File.separator, "src", "main", "data", "nexus.txt");
    private TaskList list;
    private Storage storage;
    private Ui ui;

    /**
     * Create ChatBot from given data path.
     *
     * @param path String.
     */
    public Nexus(String path) {
        this.storage = new Storage(path);
        this.ui = new Ui();
        this.list = new TaskList(storage.loadTasks());
    }

    /**
     * Create ChatBot from default data path.
     */
    public Nexus() {
        this.storage = new Storage(FILEPATH);
        this.ui = new Ui();
        this.list = new TaskList(storage.loadTasks());
    }

    /**
     * Start the bot.
     */
    public void run() {
        ui.printWelcome();
        // Show current tasks
        ui.printList(this.list);

        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = scanner.nextLine();
                isExit = Parser.parseInput(ui, storage, this.list, input);
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
        ui.printBye();
    }

    /**
     * Generate response to user.
     */
    public String getResponse(String input) {
        return "Nexus heard: " + input;
    }

    public static void main(String[] args) {
        new Nexus(FILEPATH).run();
    }
}

