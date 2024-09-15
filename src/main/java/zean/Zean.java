package zean;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javafx.scene.image.Image;
import zean.exception.ZeanException;
import zean.gui.DialogBox;

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
            if (!this.storage.getCreateFileMsg().isBlank()) {
                this.initMsg = this.storage.getCreateFileMsg();
            }
        } catch (FileNotFoundException e) {
            this.initErrorMsg = "OOPS! Something went wrong with the file."
                    + "\nShutting down now...";
        } catch (IOException e) {
            this.initErrorMsg = "OOPS! The file cannot be created.\nShutting down now...";
        } catch (SecurityException e) {
            this.initErrorMsg = "OOPS! The file cannot be written due to invalid access."
                    + "\nShutting down now...";
        } catch (ZeanException e) {
            this.initErrorMsg = e.getMessage();
        }
    }

    public String getInitMsg() {
        return this.initMsg;
    }

    public String getInitErrorMsg() {
        return this.initErrorMsg;
    }

    /**
     * Runs the CLI-based chatbot.
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
     * Returns a DialogBox containing response to user input.
     *
     * @param input The user input.
     * @return The DialogBox containing response to the input.
     */
    public DialogBox getResponseDialog(String input, Image img) {
        String output = "";
        if (input.strip().equals("bye")) {
            return DialogBox.getZeanDialog("Bye! Have a nice day!", img);
        }
        try {
            output = Parser.parse(input, this.tasks);
        } catch (ZeanException e) {
            return DialogBox.getErrorDialog(e.getMessage(), img);
        }
        assert !output.isBlank();
        return DialogBox.getZeanDialog(output, img);
    }

    public static void main(String[] args) {
        new Zean("./data/zean.txt").run();
    }
}
