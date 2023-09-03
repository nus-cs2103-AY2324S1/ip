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
    private Ui ui;

    private Storage storage;

    private TaskList tasks;

    /**
     * Constructor of Oreo chatbot.
     *
     * @param filePath file path to where saved file is and where saved
     *                 task will be written to file for next start up
     *                 of chatbot.
     */
    public Oreo(String filePath) {
        this.tasks = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage(filePath);
    }

    /**
     * Handles the main logic of the chatbot.
     *
     * @throws IllegalCommandException If user inputs a command that
     *                                 is not accepted.
     */
    public void run() throws IllegalCommandException {
        /*
        This portion reads file that has been loaded.
         */
        try {
            storage.readFile(tasks); // reads loaded file
        } catch (FileNotFoundException | IllegalDateTimeException |
                 InputMismatchException e) {
            storage.clearFile();    // file is corrupt, clear file
            tasks.clearAll();       // clear any task if it has been loaded
            ui.say("saved file is corrupt, creating new file...");  // alerts user
        }
        /*
        This portion displays greet message after file read
         */
        ui.greet(tasks);            // passes in tasks to see if there are any saved task
        /*
        This portion handles the main logic of taking input and processing it
         */
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            Command c = Parser.parse(fullCommand);
            c.execute(ui, tasks);
            isExit = c.isExit();
        }
        /*
        This portion writes tasks to file
         */
        try {
            storage.writeFile(tasks);   // write file with all tasks upon "bye" command
        } catch (IOException e) {
            ui.say(e.getMessage());
        }
        /*
        This portion displays exit message
         */
        ui.sayBye();
    }

        public static void main(String[] args) {
            Oreo oreo = new Oreo("/Users/daniel/Desktop/CS2103T/iP/src/main/java/data/duke.txt");
            oreo.run();
        }
    }