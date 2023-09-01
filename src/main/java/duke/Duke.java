package duke;

import java.io.IOException;

import command.Commandable;
import dukeexception.CorruptedFileException;
import dukeexception.FailureInExecuteException;
import dukeexception.InvalidCommandException;
import dukeexception.InvalidVarException;
/**
 * Duke represents a chatbot that parses user inputs and commands, stores tasks given to it in memory and on a file,
 * and provides a user interface for easier correspondence.
 */
public class Duke {
    private static final String FILE_PATH = "./data/tasks.txt";
    private static final String LOGO = " _           _        \n"
            + "| |    _   _| | _____ \n"
            + "| |   | | | | |/ / _ \\\n"
            + "| |___| |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private TaskList list;
    private Storage storage;
    private Parser parser;
    private UserInterface ui;

    /**
     * Creates a new Duke instance.
     */
    public Duke() {
        storage = new Storage();
        list = new TaskList(storage);
        parser = new Parser();
        ui = new UserInterface();
    }

    /**
     * Initializes the storage, list and outputs a greeting.
     * @throws IOException when unable to read from disk.
     * @throws CorruptedFileException when unable to interpret file.
     */
    public void startDuke() throws IOException, CorruptedFileException {
        ui.output("Hi, I'm \n" + LOGO);
        storage.init(FILE_PATH);
        list.loadFromDisk();
    }
    public void closeDuke() {
        ui.output("Goodbye!");
    }

    /**
     * Handler is called when a corrupted file is detected, allowing user to decide how to proceed, such as
     * clearing the file or shutting down.
     * @return whether the handler decides to call for a shutdown of the Duke instance.
     */
    public boolean corruptedFileHandler() {
        ui.output("File not properly formatted;\n"
                + "Clear corrupted file Y/N?");
        while (true) {
            String input = ui.input();
            ui.output(input);
            if (input.equals("Y")) {
                try {
                    storage.clear();
                    ui.output("File cleared.");
                } catch (IOException e) {
                    ui.output("Error in clearing! Shutting down.");
                    return true;
                }
                return false;
            } else if (input.equals("N")) {
                ui.output("Understood. Shutting down.");
                return true;
            } else {
                ui.output("Bad input. Input Y/N");
            }
        }
    }

    public static void main(String[] args) {
        boolean isShuttingDown = false;
        Duke luke = new Duke();
        try {
            luke.startDuke();
        } catch (IOException e) {
            luke.ui.output("Could not read from file");
            isShuttingDown = true;
        } catch (CorruptedFileException f) {
            isShuttingDown = luke.corruptedFileHandler();
        }
        while (!isShuttingDown) {
            String input = luke.ui.input();
            try {
                Commandable command = luke.parser.parse(input);
                isShuttingDown = command.execute(luke.list, luke.ui);
            } catch (InvalidCommandException e) {
                luke.ui.output("Unknown command given; " + e.getMessage());
            } catch (InvalidVarException e) {
                luke.ui.output("Invalid input; " + e.getMessage());
            } catch (FailureInExecuteException e) {
                luke.ui.output("Failure to execute command; " + e.getMessage());
            }
        }
        luke.closeDuke();
    }
}
