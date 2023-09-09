package duke;

import java.io.IOException;
import command.Executable;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import dukeexception.CorruptedFileException;
import dukeexception.FailureInExecuteException;
import dukeexception.InvalidCommandException;
import dukeexception.InvalidVarException;
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
    String userImagePath;
    String dukeImagePath;
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


    @Override
    public void start(Stage stage) {
        stage.setScene(ui.sceneMaker());

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Luke");
        stage.setResizable(true);
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
            boolean isShuttingDown = corruptedFileHandle();
            if (isShuttingDown) {
                closeDuke();
            }
        }
    }

    /**
     * Initializes the storage, list and outputs a greeting.
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
    public void closeDuke() {
        ui.output("Goodbye!");
        Platform.exit();
    }

    /**
     * Handler is called when a corrupted file is detected, allowing user to decide how to proceed, such as
     * clearing the file or shutting down.
     * @return whether the handler decides to call for a shutdown of the Duke instance.
     * TODO: make this compatible with the new parsing format
     */
    public boolean corruptedFileHandle() {
        return true;
    }
//        ui.output("File not properly formatted;\n"
//                + "Clear corrupted file Y/N?");
//        while (true) {
//            String input = ui.input();
//            ui.output(input);
//            if (input.equals("Y")) {
//                try {
//                    storage.clear();
//                    ui.output("File cleared.");
//                } catch (IOException e) {
//                    ui.output("Error in clearing! Shutting down.");
//                    return true;
//                }
//                return false;
//            } else if (input.equals("N")) {
//                ui.output("Understood. Shutting down.");
//                return true;
//            } else {
//                ui.output("Bad input. Input Y/N");
//            }
//        }
//    }

    public void handle(String input) {
        try {
            Executable command = parser.parse(input);
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
