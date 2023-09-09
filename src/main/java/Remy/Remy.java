package remy;

import java.nio.file.Path;

import remy.command.Command;
import remy.task.TaskList;

/**
 * A Chatbot named Remy that functions as a task manager.
 * It accepts input from users via CLI commands.
 */
// CS2103T Website Increment description-reused
// Reused the example code from the website.
public class Remy {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit;


    /**
     * Sets up a Remy chatbot for use.
     * @param filePath location to store tasklist after ending each session.
     */
    public Remy(Path filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.isExit = false;
        try {
            this.tasks = new TaskList(storage.load());
        } catch (ChatbotException e) {
            this.tasks = new TaskList();
        }
    }

    /**
     * Passes user input to the parser and executes any command formed.
     * Returns a success message based on the command if executed successfully.
     * If input is invalid, return an error message.
     *
     * @param input
     * @return
     */
    //@@author SE-EDU-reused
    // Reused from https://se-education.org/guides/tutorials/javaFxPart4.html
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String reply = c.execute(tasks, ui, storage);
            if (c.isExit()) {
                this.isExit = true;
            }
            return reply;
        } catch (ChatbotException e) {
            return Ui.formatError(e.getMessage());
        }
    }
    //@@author

    /**
     * Returns Exit status of Remy.
     * @return
     */
    public boolean hasExited() {
        return this.isExit;
    }
}
