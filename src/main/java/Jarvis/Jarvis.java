package Jarvis;

import java.util.Scanner;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

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
    private static String fileName;

    /**
     * Constructs a new Jarvis chatbot.
     *
     * @param fileName Name of the file to store the task data in.
     */
    public Jarvis(String fileName) {
        this.fileName = fileName;
    }

    /**
     * The main event loop of the Jarvis chatbot.
     * <p>
     *     The method keeps listening for user input until the user chooses to exit the application.
     *     All recognised commands are parsed and executed through the Parser class
     *     Existing data is loaded from the Storage class into an instance of the TaskList class,
     *     when the user chooses to exit the application, the data from the chatbot is saved through Storage class.
     * </p>
     */
    public void run() {
        Ui.printGreeting();
        // getting the file path to the save file
        String home = System.getProperty("user.home");
        Path pathToSaveFile = Paths.get(home, "Desktop", "CS2103T", "IP", "data", fileName);
        boolean isFileExists = Files.exists(pathToSaveFile);

        storage = new Storage(pathToSaveFile.toString());
        tasks = new TaskList(storage.load());
        parser = new Parser();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String command = scanner.nextLine().trim();
                Parser.parseCommand(storage, tasks, command);
            } catch (IncorrectJarvisCommandException e) {
                break;
            } catch (InvalidTaskNumberException e) {
                break;
            } catch (WrongJarvisCommandFormatException e) {
                break;
            }
            storage.deleteContents();
            storage.save(tasks); // save task list to data file after every iteration
        }
        scanner.close(); // closing the user input scanner
    }

    /**
     * The main entry point of the application
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        new Jarvis("data.txt").run();
    }
}