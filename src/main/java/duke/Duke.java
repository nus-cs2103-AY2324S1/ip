package duke;

import java.io.IOException;

import command.Executable;
import dukeexception.CorruptedFileException;
import dukeexception.FailureInExecuteException;
import dukeexception.InvalidCommandException;
import dukeexception.InvalidVarException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
/**
 * Duke represents a chatbot that parses user inputs and commands, stores tasks given to it in memory and on a file,
 * and provides a user interface for easier correspondence.
 */
public class Duke extends Application {
    private final TaskList list;
    private final Storage storage;
    private final Parser parser;
    private final UserInterface ui;
    private String filePath;
    private String userImagePath;
    private String dukeImagePath;
    private String logo;
    /**
     * Creates a new Duke instance.
     */
    public Duke() {
        this.storage = new Storage();
        this.list = new TaskList(storage);
        this.parser = new Parser();
        this.ui = new UserInterface(this);
    }


    /**
     * Starts the application.
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set.
     *              Applications may create other stages, if needed, but they will not be
     *              primary stages.
     */
    @Override
    public void start(Stage stage) {
        stage.setScene(ui.makeScene());

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Luke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
        stage.show();
        try {
            startup();
            ui.output("Hi, I'm \n" + logo);
        } catch (IOException e) {
            ui.output("Could not read from file");
            closeDuke();
        } catch (CorruptedFileException f) {
            boolean isShuttingDown = handleCorruptFile();
            if (isShuttingDown) {
                ui.output("Could not clear file");
                closeDuke();
            }
        }
    }

    /**
     * Initializes the arguments.
     */
    @Override
    public void init() {
        String[] temp = new String[4];
        getParameters().getRaw().toArray(temp);
        filePath = temp[0];
        userImagePath = temp[1];
        dukeImagePath = temp[2];
        logo = temp[3];
    }

    /**
     * Initializes the storage, list and outputs a greeting.
     * @throws IOException when unable to read from disk.
     * @throws CorruptedFileException when unable to interpret file.
     */
    public void startup() throws CorruptedFileException, IOException {
        storage.init(filePath);
        list.loadFromDisk();
        ui.init(userImagePath, dukeImagePath);
    }

    /**
     * Shuts down Duke, performing any cleanup tasks along the way.
     */
    public void closeDuke() {
        ui.output("Goodbye!");
        Platform.exit();
    }
    /**
     * Clears the file if it is corrupted, and returns a boolean indicating whether the clearing was successful.
     */
    public boolean handleCorruptFile() {
        ui.output("Corrupted file detected! deleting file...");
        try {
            list.clear();
        } catch (IOException e) {
            ui.output("Can't delete file.");
            return true;
        }
        ui.output("Cleared corrupted file successfully.");
        return false;

        // TODO: make this compatible with the new parsing format
    }

    /**
     * Handles a string input and gives an appropriate reply.
     * @param input the string to be parsed.
     */
    public void handle(String input) {
        try {
            Executable command = parser.parse(input);
            assert (command != null); // Parser should throw an exception if this occurs.
            boolean isShuttingDown = (command.execute(list, ui));
            if (isShuttingDown) {
                closeDuke();
            }
        } catch (InvalidCommandException e) {
            ui.output("Unknown command given; " + e.getMessage());
        } catch (InvalidVarException e) {
            ui.output("Invalid input; " + e.getMessage());
        } catch (FailureInExecuteException e) {
            ui.output("Failure to execute command; " + e.getMessage());
        }
    }
}
