package thorndike;
import java.io.IOException;

import command.Command;
import command.CommandParser;
import exceptions.ThorndikeException;
import storage.Storage;
import task.TaskList;
import utility.TextFileHandler;

/**
 * Thorndike - A simple chatbot for managing tasks and user interactions.
 *
 * @author Ho Khee Wei
 */
public class Thorndike {
    private TaskList taskList;

    /**
     * Constructs a Thorndike chatbot and initializes necessary components.
     * It sets up the user interface, task list, and handles potential file I/O
     * exceptions.
     *
     * @author Ho Khee Wei
     */
    public Thorndike() {
        try {
            TextFileHandler.createFile(Storage.TASK_FILE_PATH);
            this.taskList = Storage.readFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets Thorndike's reponse to user's message.
     *
     * @param input User's input
     * @return Thorndike's response
     * @throws ThorndikeException when the user input has issues.
     */
    public String getResponse(String input) throws ThorndikeException {
        Command command = CommandParser.parse(input);
        return command.execute(taskList);
    }
}
