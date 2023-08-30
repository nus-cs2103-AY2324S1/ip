package zean;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import zean.exception.DukeException;

/**
 * The main class for the chatbot.
 *
 * @author Zhong Han
 */
public class Zean {

    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    /**
     * Constructor for the chatbot zean.
     *
     * @param filePath The filepath of the data to be retrieved or written.
     */
    public Zean(String filePath) {
        this.ui = new Ui();
        try {
            this.storage = new Storage(filePath);
            this.tasks = new TaskList(this.storage);
        } catch (FileNotFoundException e) {
            this.ui.showError("\tOOPS! Something went wrong with the file."
                    + "\n\tShutting down now...");
        } catch (IOException e) {
            this.ui.showError("\tOOPS! The file cannot be created.\n\tShutting down now...");
        } catch (SecurityException e) {
            this.ui.showError("\tOOPS! The file cannot be written due to invalid access."
                    + "\n\tShutting down now...");
        } catch (DukeException e) {
            this.ui.showError(e.getMessage());
        }
    }

    /**
     * Runs the chatbot.
     */
    public void run() {
        this.ui.greet("Zean");
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            if (input.strip().equals("bye")) {
                break;
            }
            try {
                this.ui.printOutput(Parser.parse(input, this.tasks));
            } catch (DukeException e) {
                this.ui.showError(e.getMessage());
            }
        }
        sc.close();
        this.ui.exit();

    }

    public static void main(String[] args) {
        new Zean("./data/zean.txt").run();
    }
}
