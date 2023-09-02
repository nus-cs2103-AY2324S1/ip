import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class Duke {
    private Ui ui;

    private Storage storage;

    private TaskList tasks;

    public Duke(String filePath) {
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

        public static boolean isInteger(String str) {
            if (str == null) {
                return false;
            }
            int length = str.length();
            if (length == 0) {
                return false;
            }
            int i = 0;
            if (str.charAt(0) == '-') {
                if (length == 1) {
                    return false;
                }
                i = 1;
            }
            for (; i < length; i++) {
                char c = str.charAt(i);
                if (c < '0' || c > '9') {
                    return false;
                }
            }
            return true;
        }

        public static void main(String[] args) {
            Duke duke = new Duke("/Users/daniel/Desktop/CS2103T/iP/src/main/java/data/duke.txt");
            duke.run();
        }
    }