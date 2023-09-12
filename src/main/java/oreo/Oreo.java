package oreo;

import oreo.exception.IllegalCommandException;
import oreo.exception.IllegalDateTimeException;
import oreo.command.Command;
import oreo.parser.Parser;
import oreo.storage.Storage;
import oreo.task.TaskList;
import oreo.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;


/**
 * This class implements the chatbot Oreo.
 *
 * @author Daniel Loh
 * @version 03/09/2023
 */
public class Oreo {
    private MainWindow ui;

    private Storage storage;

    private TaskList tasks;

    /**
     * Constructor of Oreo chatbot.
     *
     * @param filePath file path to where saved file is and where saved
     *                 task will be written to file for next start up
     *                 of chatbot.
     */
    public Oreo(String filePath, MainWindow gui) {
        this.tasks = new TaskList();
        this.ui = gui;
        gui.setOreo(this);
        this.storage = new Storage(filePath);
        gui.startUp();
    }

    /**
     * Handles the main logic of the chatbot.
     *
     * @throws IllegalCommandException If user inputs a command that
     *                                 is not accepted.
     */
    public String getResponse(String input) {
        return "Oreo heard: " + input;
    }

    public void startUp() throws FileNotFoundException, IllegalDateTimeException {
            storage.readFile(tasks); // reads loaded file
    }
    public void clearTaskAndFile() {
        storage.clearFile();
        tasks.clearAll();
    }

    public String greet() {
        String greetMessage = "Woof! I'm Oreo! How may I help you?\n";
        if (tasks.getNumberOfTask() != 0) {
            return greetMessage
                    + "Welcome back! "
                    + tasks.list();
        } else {
            return greetMessage;
        }
    }
    public String sayBye() {
        return "I will be sad to see you go! bye!\n";
    }

    public String execute(Command command) {
        return command.execute(tasks);
    }

    public void closeProcess() throws IOException {
        storage.writeFile(tasks);
    }
}