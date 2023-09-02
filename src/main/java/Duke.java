import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import exceptions.ErrorStorageException;
import exceptions.InvalidCommandException;
import helpers.Parser;
import helpers.Storage;
import helpers.TaskList;
import helpers.Ui;


/**
 * Main Class of Duke
 */
public class Duke {

    private static TaskList taskList;

    private final String DIRECTORY = "data";
    private Storage storage;
    private Ui ui;

    /**
     * Public constructor to load Duke
     *
     * @param filePath File path to get text file data from
     * @throws ErrorStorageException Exception for storage loading error
     */
    public Duke(String filePath) throws ErrorStorageException {
        ui = new Ui();
        String projRoot = System.getProperty("user.dir");
        String path = projRoot + "/" + DIRECTORY + filePath;
        this.storage = new Storage(path);
        try {
            taskList = new TaskList(this.storage.read());
        } catch (Exception e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }

    }

    /**
     * Main method to execute Duke
     *
     * @throws ErrorStorageException Exception for storage loading error
     */
    public static void main(String[] args) throws ErrorStorageException {
        new Duke("/tasks.txt").run();
    }

    /**
     * Running method for executing other core processes
     */
    public void run() {
        //Create buffered reader for user input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            ui.showStartLogo();
            ui.showWelcome();
            boolean isRunning = true;
            //exits the echo commands when input contains 'bye'
            while (isRunning) {
                String input = ui.getCommand(br);
                String cmd = input.split(" ")[0].toUpperCase();
                Parser parser = new Parser(cmd, input, taskList, ui, storage);
                parser.execute();
                if (cmd.equalsIgnoreCase("bye")) {
                    isRunning = false;
                }
            }
        } catch (IOException | InvalidCommandException | ErrorStorageException e) {
            System.out.println(e.getMessage());
        }
    }
}
