package duke.Utils;

import java.util.Scanner;

/**
 * The Input class is responsible for handling user input and executing commands
 * in the Duke application.
 */
public class Input {
    private static final String FILE_PATH = "./data/duke.csv";
    private static final String FOLDER_PATH = "./data";
    private static Scanner scanner = new Scanner(System.in);
    private Storage storage;
    private TaskList tasks;
    private String input;

    /**
     * Constructs a new Input object and initializes storage and task list.
     */
    protected Input() {
        this.storage = new Storage(Input.FILE_PATH, Input.FOLDER_PATH);
        this.tasks = new TaskList(this.storage.load());
    }
    
    /**
     * Reads a command from the user via the console input.
     *
     * @return The response generated after executing the user's command.
     */
    protected Response command() {
        this.input = Input.scanner.nextLine();
        return executeCommand();
    }

    /**
     * Executes the user's command and returns a response.
     *
     * @return The response generated after executing the user's command.
     */
    protected Response executeCommand() {
        String command = this.input.split(" ")[0];
        if (command.equals("bye")) {
            return Response.TERMINATE;
        }
        try {
            Response response = this.tasks.execute(this.input, command);
            this.storage.save(this.tasks.csvArray());
            return response;
        } catch (DukeException e) {
            return Response.generate(e.toString());
        }
    }
}
