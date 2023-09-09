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
    private TaskList list;
    private Storage storage;
    private Ui ui;

    /**
     * Create ChatBot from data path.
     *
     * @param path String.
     */
    public Nexus(String path) {
        this.storage = new Storage(path);
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
        boolean exit = false;
        while (!exit) {
            try {
                String input = scanner.nextLine();
                exit = Parser.parseInput(ui, storage, this.list, input);
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
        ui.printBye();
    }

    public static void main(String[] args) {
        // OS-Independent path
        String path = String.join(File.separator, "src", "main", "data", "nexus.txt");
        new Nexus(path).run();
    }
}

