package valerie;

import java.io.File;
import java.util.ArrayList;

/**
 * The Valerie class represents a simple task management chatbot application.
 * It allows users to interact with a list of tasks and store them in a file.
 */
public class Valerie {

    private final Storage storage;

    private final TaskList taskList;

    /**
     * Constructs a Duke instance with the specified file path.
     *
     * @param filepath The path to the file used for storing tasks.
     */
    public Valerie(String filepath, TaskList taskList) {
        this.storage = new Storage(filepath);
        this.taskList = taskList;
        this.storage.loadTasks(taskList); // Load tasks to list
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public ArrayList<String> getResponse(String input) {
        ArrayList<String> response = new ArrayList<>();

        File dataDirectory = new File("./data");
        if (!dataDirectory.exists()) {
            if (dataDirectory.mkdir()) {
                response.add("Data directory created successfully");
                System.out.println("        Data directory created successfully.");
            } else {
                response.add("Failed to create data directory.");
                System.out.println("        Failed to create data directory.");
            }
        }

        if (input.equals("bye")) {
            // Exit the loop if the user types "bye"
            this.storage.saveTasks(this.taskList);
            return Ui.showExit();
        }

        response.addAll(Parser.parseUserInput(input, this.taskList));
        return response;
    }

}
