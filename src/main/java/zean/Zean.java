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

    private String initErrorMsg = "";

    /**
     * Empty constructor.
     */
    public Zean() {

    }

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
            this.initErrorMsg = "OOPS! Something went wrong with the file."
                    + "\nShutting down now...";
        } catch (IOException e) {
            this.initErrorMsg = "OOPS! The file cannot be created.\nShutting down now...";
        } catch (SecurityException e) {
            this.initErrorMsg = "OOPS! The file cannot be written due to invalid access."
                    + "\nShutting down now...";
        } catch (DukeException e) {
            this.initErrorMsg = e.getMessage();
        }
    }

    public String getInitErrorMsg() {
        return this.initErrorMsg;
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

    /**
     * Returns a response to user input.
     *
     * @param input The user input.
     * @return The response to the input.
     */
    public String getResponse(String input) {
        String output = "";
        if (input.strip().equals("bye")) {
            return "Bye! Have a nice day!";
        }
        try {
            output = Parser.parse(input, this.tasks);
        } catch (DukeException e) {
            output = e.getMessage();
        }
        return output;
    }

    public static void main(String[] args) {
        new Zean("./data/zean.txt").run();
    }
}
