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


        Parser.parse(taskList, index, ui, storage);
        Ui.print("Bye. Hope to see you again soon!");

    }

}
