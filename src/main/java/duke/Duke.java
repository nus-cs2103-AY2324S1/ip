package duke;

import java.io.File;
import java.util.Scanner;

/**
 * The Duke class represents the chatbot.
 */
public class Duke {
    /** Stores file information and handles the file operations **/
    private Storage storage;
    /** Stores contact information and handles the file operations **/
    private Storage contactStorage;
    /** Stores the list of tasks **/
    private TaskList tasks;
    /** Stores the list of contacts **/
    private ContactList contacts;
    /** Instance of Ui to handle user interactions **/
    private Ui ui;

    /**
     * Instantiates an instance of Duke.
     *
     * @param filePath String of the intended file path.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.contactStorage = new Storage("contacts");
        this.tasks = new TaskList(storage.load());
        this.contacts = new ContactList(contactStorage.load());
        File checkFile = new File(filePath);
        assert checkFile.exists();
    }

    /**
     * Runs the program.
     */
    public void run() {
        Ui.start();
        Scanner scan = new Scanner(System.in);
        String input = Ui.getInput(scan);
        while (!input.equals("bye")) {
            Parser.parseInput(input, this.tasks, this.contacts, this.storage, this.contactStorage);
            input = Ui.getInput(scan);
        }
        Ui.bye();
    }

    public static void main(String[] args) {
        new Duke("./duke.txt").run();
    }

    /**
     * Produces the response of the chatbot based on what the user types.
     *
     * @param input The user's message.
     * @return The chatbot response.
     */
    public String getResponse(String input) {
        Ui.start();
        if (input.equals("bye")) {
            return Ui.bye();
        } else {
            return Parser.parseInput(input, this.tasks, this.contacts, this.storage, this.contactStorage);
        }
    }
}
