package duke;

import java.io.IOException;

/**
 * Represents the main class for the Duke program.
 */
public class Duke {
    private static StringBuilder tempData = new StringBuilder();

    /**
     * Constructs a Duke object with the specified file path and initializes the program.
     *
     * @param filePath The file path to the data file.
     * @throws Exception If an error occurs during initialization.
     */
    public Duke(String filePath) throws Exception{
        try {
            LoadFile loadFile = new LoadFile(filePath);
            loadFile.load();
            Ui ui = new Ui();
            ui.greet();
            TaskList taskList = new TaskList();
            taskList.Answer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The main method to start the Duke program.
     *
     * @param args Command-line arguments (not used in this program).
     * @throws Exception If an error occurs during program execution.
     */
    public static void main(String[] args) throws Exception {
        String logo = "Zenith";

        String zenithData = "/Users/william/Desktop/ip/src/main/java/data/zenith.txt";
        Duke duke = new Duke(zenithData);
    }
}
