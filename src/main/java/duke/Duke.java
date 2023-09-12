package duke;

// fixing DukeException based on my understanding of exceptions 27/8/23
import java.util.Scanner;

import duke.commands.Command;
import duke.tasks.TaskList;

/**
 * The main file that the application runs
 */
public class Duke {
    protected Ui helper;
    protected TaskList tasks;
    protected Storage storage;

    /**
     * Constructor of the main file, takes in the filePath that
     * allow the bot to store the file with the saved taskList
     * at a known place to be retrieved later
     * Contains the UI, Storage and taskList
     * 
     * @param filePath the path in the application that contains the file
     */
    public Duke(String filePath) {
        this.helper = new Ui("DukeKing");
        try {
            this.storage = new Storage(filePath);
            this.tasks = new TaskList(storage.load());
        } catch (Exception e) {
            this.tasks = new TaskList();
            helper.noFile();
        }
    }

    /**
     * Main method, create a duke object with the filepath and run it
     * 
     * @param args takes in the arguments for the respective commands
     *             from the user
     */
    public static void main(String[] args) {
        new Duke("./dataTasks.txt").run();
    }

    /**
     * Runs the application, contains a scanner object to read the inputs
     * given by the user and will continue to loop after each command until a
     * "bye" command is given by the user to end the program
     */
    public void run() {
        helper.welcome();
        // setting up
        Scanner sc = new Scanner(System.in);
        String string = sc.nextLine();
        assert string != null : "string should not be null";
        boolean isExit = false;

        // looping in the program
        while (!isExit) {
            // end the program
            try {
                helper.printLine();
                Command command = Parser.parse(string);
                command.execute(tasks, helper, storage);
                isExit = command.isExit();
            } catch (Throwable e) {
                System.out.println(e.getMessage());
            } finally {
                helper.printLine();
                string = sc.nextLine();
            }
        }
        // end the program
        sc.close();
        helper.bye();
    }

    public String getResponse(String input) {
        String output = "";
        assert input != null : "input should not be null";
        if (tasks == null) {
            this.tasks = new TaskList();
        } else {
            try {
                Command command = Parser.parse(input);
                output = command.execute(tasks, helper, storage);
            } catch (Throwable e) {
                return e.getMessage();
            }
        }
        return output;
    }
}
