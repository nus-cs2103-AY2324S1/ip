package zean;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import zean.exception.ZeanException;

/**
 * The main class for the chatbot.
 *
 * @author Zhong Han
 */
public class Zean {

    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    private String initMsg = "";

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
            if (!this.storage.getCreateFileMsg().isBlank()) {
                this.initMsg = this.storage.getCreateFileMsg();
            }
        } catch (FileNotFoundException e) {
            this.initMsg = "OOPS! Something went wrong with the file."
                    + "\nShutting down now...";
        } catch (IOException e) {
            this.initMsg = "OOPS! The file cannot be created.\nShutting down now...";
        } catch (SecurityException e) {
            this.initMsg = "OOPS! The file cannot be written due to invalid access."
                    + "\nShutting down now...";
        } catch (ZeanException e) {
            this.initMsg = e.getMessage();
        }
    }

    public String getInitMsg() {
        return this.initMsg;
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
            } catch (ZeanException e) {
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
        } catch (ZeanException e) {
            output = e.getMessage();
        }
        return output;
    }

    public static void main(String[] args) {
        new Zean("./data/zean.txt").run();
    }
}
