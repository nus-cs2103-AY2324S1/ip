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


public class Oreo {
    private Ui ui;

    private Storage storage;

    private TaskList tasks;

    public Oreo(String filePath) {
        this.tasks = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage(filePath);
    }

    public void run() throws IllegalCommandException {
        /*
        This portion reads file that has been loaded
         */
        try {
            storage.readFile(tasks); // reads loaded file
        } catch (FileNotFoundException | IllegalDateTimeException |
                 InputMismatchException e) {
            storage.clearFile();    // file is corrupt
            tasks.clearAll();
            ui.say("saved file is corrupt, creating new file...");
        }
        /*
        This portion displays greet message after file read
         */
        ui.greet(tasks);            // passes tasks in to see if there saved task
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
            storage.writeFile(tasks);   // write file with all tasks
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