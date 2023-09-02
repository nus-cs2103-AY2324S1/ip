package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class deals with interactions with the user
 */
public class Ui {
    static String indent = "   ";
    static String horizontalLines = indent  + "__________________________________________";
    String name = "zac";
    static String dukeFilePath = "data/duke.txt";

    /**
     * Read the user's commands
     * @return the user's command
     */
    public String readCommand(Scanner scanner) {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        } else {
            // Handle the case where no input is available
            // You can return a default value or show an error message.
            return ""; // or throw an exception, print an error message, etc.
        }
    }

    /**
     * Prints the error
     *
     * @param error some stuff
     */
    public void showError(String error) {
        System.out.println(indent + error);
    }

    /**
     * Displays a horizontal line
     */
    public void showLine() {
        System.out.println(horizontalLines);
    }

    /**
     * Show the welcome message, and prints the existing content in the duke.TaskList (if it exists)
     */
    public void showWelcome() {
        System.out.println(horizontalLines);
        System.out.println(indent + "Hello! I'm " + name);
        System.out.println(indent + "What can I do for you?");
        System.out.println(horizontalLines);

        try {
            printFileContents(dukeFilePath);
        } catch (IOException e) {
            System.out.println("1" + e.getMessage());
        }
    }
    public void showExit() {
        System.out.println(indent + "Bye. Hope to see you again soon!");
    }

    /**
     * Prints out all the tasks in the list
     *
     * @param filePath the file where the lists of Tasks are stored. It is hardcoded to be "data/duke.txt"
     * @throws FileNotFoundException if the file at this filePath is not found (though I'm not sure
     * when this will happen)
     */
    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }
}
