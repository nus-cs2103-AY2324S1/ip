package Jarvis;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Main application class for the Jarvis chatbot.
 * <p>
 *     This class is responsible initialising the task list, loading and saving tasks in storage and
 *     running the main event loop of the application. It delegates command parsing to the Parser class and storage
 *     operations to the Storage class.
 * </p>
 */
public class Jarvis {
    private static TaskList tasks;
    private static Storage storage;
    private static Parser parser;

    /**
     * Constructs a new Jarvis chatbot.
     *
     * @param filePath Path of the file to store the task data in.
     */
    public Jarvis(String filePath) {

        assert !filePath.isEmpty() : "filePath cannot be empty";

        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
        parser = new Parser();
    }

    public String getResponse(String input) {
        try {
            return parser.parseCommand(storage, tasks, input);
        } catch (IncorrectJarvisCommandException e) {
            return Ui.getListOfCommands(parser.validCommands, e);
        } catch (InvalidTaskNumberException e) {
            return Ui.respond(e.getMessage() + "\n" +
                    "    There are currently " + tasks.countTask() + " tasks in the list.");
        } catch (WrongJarvisCommandFormatException e) {
            return Ui.respond("");
        }
    }
}