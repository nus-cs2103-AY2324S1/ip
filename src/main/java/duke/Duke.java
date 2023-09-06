package duke;

import java.nio.file.Path;

import duke.command.Command;
import duke.gui.GuiApplication;
import duke.task.TaskList;
import javafx.application.Application;

/**
 * The primary Duke class. Contains the main function to run the Duke chatbot Bob.
 */
public class Duke {

    /** The TaskList to use in the chatbot. */
    private TaskList tasks;

    /** The Storage object handling file read/save. */
    private Storage storage;

    /**
     * Creates a new Duke chatbot object.
     *
     * @param filePath The file path of the file that will contain all tasks within the chatbot.
     */
    public Duke(Path filePath) {
        storage = new Storage(filePath);
        tasks = storage.load();
    }

    /**
     * Returns the response to a message by parsing the message through a Parser, then executing the command returned
     * by the Parser.
     * @param message The message to be parsed and executed.
     * @return The text response by the chatbot.
     */
    public String getResponse(String message) {
        try {
            Command c = Parser.parse(message);
            return c.execute(tasks, storage);
        } catch (DukeException e) {
            return e.toString();
        }
    }

    /**
     * Returns a welcome message for the Duke chatbot.
     * @return Welcome message for the chatbot.
     */
    public String getWelcomeMessage() {
        return "Hello! I'm Bob the Chatbot!\nWhat can I do for you?";
    }

    /**
     * Returns an exit message for the Duke chatbot.
     * @return Exit message for the chatbot.
     */
    public String getExitMessage() {
        return "Bye. Hope to see you again soon!";
    }

    public static void main(String[] args) {
        Application.launch(GuiApplication.class, args);
    }
}
