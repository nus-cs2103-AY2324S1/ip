package duke;

import java.io.IOException;

import command.Commandable;
import dukeexception.CorruptedFileException;
import dukeexception.FailureInExecuteException;
import dukeexception.InvalidCommandException;
import dukeexception.InvalidVarException;

public class Duke {
    private TaskList list;
    private Storage storage;
    private Parser parser;
    private UserInterface ui;
    private final String filePath = "./data/tasks.txt";
    private String logo = " _           _        \n"
        + "| |    _   _| | _____ \n"
        + "| |   | | | | |/ / _ \\\n"
        + "| |___| |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
    public Duke() {
        storage = new Storage();
        list = new TaskList(storage);
        parser = new Parser();
        ui = new UserInterface();
    }
    public void startDuke() throws IOException, CorruptedFileException {
        ui.output("Hi, I'm \n" + logo);
        storage.init(filePath);
        list.loadFromDisk(storage);
    }
    public void closeDuke() {
        ui.output("Goodbye!");
    }

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
