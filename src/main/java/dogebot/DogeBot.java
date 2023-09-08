package dogebot;

import java.io.File;
import java.io.IOException;

/**
 * The DogeBot program is an application that keeps track of tasks. Different types of tasks can be added, deleted,
 * marked and progress is saved to a text file.
 *
 * @author Kenvyn Kwek
 */
public class DogeBot {
    protected static TaskList tasks;
    private static final String HOME = System.getProperty("user.home"); // get relative path
    private static final java.nio.file.Path PATH = java.nio.file.Paths.get(HOME, "OneDrive", "Desktop", "iP",
        "src", "main");
    private static File file;
    private Ui ui;
    private Parser userInput;
    private Storage storage;

    /**
     * Initializes a DogeBot object with a filename.
     *
     * @param filename Name of the text file.
     */
    public DogeBot(String filename) {
        file = new File(PATH.toString(), filename);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        ui = new Ui();
        userInput = new Parser();
        storage = new Storage(file);
        tasks = storage.readFromTxtFile();
    }

    /**
     * Runs the DogeBot program.
     */
    public void run() {
        ui.intro();
        boolean isLoop = true;
        while (isLoop) {
            isLoop = userInput.scan();
        }
        storage.save(tasks);
    }

    public static void main(String[] args) {
        new DogeBot("tasklist.txt").run();
    }
}
