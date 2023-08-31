package duke;

import java.io.*;

import java.util.ArrayList;

import static duke.Storage.loadTasksFromFile;
import static duke.Storage.saveTasksToFile;

/**
 * The main class for the Duke application.
 */
public class Duke {
    public static void main(String[] args) throws DukeException {
        Storage storage = new Storage();
        Ui ui = new Ui();
        Parser parser = new Parser();
        ArrayList<Task> taskList = new TaskList();

        if (!storage.getFileExists()) {
            try {
                boolean fileCreated = storage.f.createNewFile();
                if (fileCreated) {
                    Ui.print("File created: " + storage.path);
                } else {
                    Ui.print("Failed to create file.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        int LIMIT = 100;
        int index = 0;
        if (storage.getFileExists()) {
            taskList = loadTasksFromFile(String.valueOf(storage.path));
            index = taskList.size(); // Set the list size to the loaded tasks count
        }


        Ui.print(
                "Hello! I'm Sunacchi!\n" +
                        "What can I do for you?\n"
        );


        String userInput = ui.next();
        while (index < LIMIT && parser.getRunning()) {
            Parser.parse(userInput, taskList, index, ui);
        }

        saveTasksToFile(taskList, String.valueOf(storage.path));
        Ui.print("Bye. Hope to see you again soon!");

    }

}
