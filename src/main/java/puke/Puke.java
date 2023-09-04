package puke;

import java.io.File;
import java.io.IOException;

import puke.command.Command;
import puke.command.ErrorCommand;


/**
 * A chatbot that uses overly complicated sentences.
 */
public class Puke {
    /**
     * List of tasks stored by the chatbot
     */
    private TaskList tasks;
    /**
     * The UI of the chatbot that prints all applicable messages.
     */
    private final Ui ui;

    /**
     * Constructor for the chatbot
     * @throws IOException when an error occurs with the file reader.
     */
    public Puke() throws IOException {
        this.ui = new Ui();
        try {
            tasks = new TaskList(DataHandler.loadDatabase());
        } catch (PukeException e) {
            new File("ListData.txt").createNewFile();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the program
     */
    public void run() {
        ui.startup();
        boolean isExit = false;
        while (!isExit) {
            String command = ui.command();
            String input = ui.input();
            ui.line();
            Command next;
            try {
                next = Parser.parse(command, input);
            } catch (PukeException e) {
                next = new ErrorCommand();
            }
            next.execute(tasks, ui);
            isExit = next.isExit();
        }
    }

    public static void main(String[] args) throws IOException {
        new Puke().run();
    }
}













