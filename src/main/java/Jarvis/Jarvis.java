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
     * @param fileName Name of the file to store the task data in.
     */
    public Jarvis(String fileName) {
        // getting the file path to the save file
        String home = System.getProperty("user.home");
        Path pathToSaveFile = Paths.get(home, "Desktop", "CS2103T", "IP", "data", fileName);
        boolean isFileExists = Files.exists(pathToSaveFile);

        storage = new Storage(pathToSaveFile.toString());
        tasks = new TaskList(storage.load());
        parser = new Parser();
    }

    public String getResponse(String input) {
        try {
            return Parser.parseCommand(storage, tasks, input);
        } catch (IncorrectJarvisCommandException e) {
            return Ui.getListOfCommands(Parser.validCommands, e);
        } catch (InvalidTaskNumberException e) {
            return Ui.respond(e.getMessage() + "\n" +
                    "    There are currently " + tasks.countTask() + " tasks in the list.");
        } catch (WrongJarvisCommandFormatException e) {
            return Ui.respond("");
        }
    }
}