package dogebot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The DogeBot program is an application that keeps track of tasks. Different types of tasks can be added, deleted,
 * marked and progress is saved to a text file.
 *
 * @author Kenvyn Kwek
 */
public class DogeBot {
    protected static TaskList tasks;
    private static final String PATH = "DogeBotData/";
    private static File file;
    protected Ui ui;
    private Parser userInput;
    private Storage storage;

    /**
     * Initializes a DogeBot object with a filename.
     *
     * @param filename Name of the text file.
     */
    public DogeBot(String filename) {
        file = new File(PATH, filename);
        try {
            if (!file.exists()) {
                Files.createDirectory(Paths.get(PATH));
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
     * Gets Responses from DogeBot to be output in GUI.
     *
     * @param input User input.
     * @return Output from DogeBot.
     */
    public String getResponse(String input) {
        String response = userInput.scan(input);

        // save to harddisk i.e. "tasklist.txt"
        if (response.equals("Bye~ See you again")) {
            storage.save(tasks);
        }

        return response;
    }
}
