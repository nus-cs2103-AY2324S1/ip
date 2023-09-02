package haste;

import haste.commands.Command;
import haste.commands.Parser;
import haste.data.Storage;
import haste.data.TaskList;
import haste.exceptions.HasteException;
import haste.ui.Ui;

import java.util.Scanner;

/**
 * Represents a chatbot that keeps track of tasks.
 */
public class Haste {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    /**
     * Creates an instance of the chatbot.
     *
     * @param filePath Location of the file storing Tasks data.
     */
    public Haste(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList();
    }

    public static void main(String[] args) {
        Haste haste = new Haste("./Data.txt");

        // greet and check for storage files
        haste.load();
        haste.run();
        haste.end();
    }

    /**
     * Starts the chatbot.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (ui.running) {
            String cmd = sc.nextLine();
            Command c = Parser.handleCommand(cmd, storage);

            try {
                c.execute(tasks, ui);
            } catch (HasteException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    /**
     * Welcomes user and loads any saved tasks.
     */
    public void load() {
        this.ui.greet();
        this.storage.read(tasks);
    }

    /**
     * Close the chatbot and saves tasks in list.
     */
    public void end() {
        this.storage.delete();
        this.storage.save(tasks);
    }

}
