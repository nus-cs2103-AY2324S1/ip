package duke;

// fixing DukeException based on my understanding of exceptions 27/8/23
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;


/**
 * The main file that the application runs
 */
public class Duke {
    protected UI helper;
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
        this.helper = new UI("DukeKing");
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
        String commandType = "";
        String parserOutput = "";

        // looping in the program
        while (true) {
            // end the program
            try {
                parserOutput = Parser.isExitOrContinue(string, tasks, helper, storage);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                if (parserOutput.equals("")) {
                    commandType = "bye";
                }
                if (commandType.equals("bye")) {
                    break;
                }
                helper.printLine();
                string = sc.nextLine();
            }
        }
        // end the program
        sc.close();
        if (commandType.equals("bye")) {
            helper.bye();
        }
    }

    public String getResponse(String input) {
        String output = "";
        if (tasks == null) {
            this.tasks = new TaskList();
        } else {
            try {
                output = Parser.isExitOrContinue(input, tasks, helper, storage);
                if (output.equals("")) {
                    return "Bye. Hope to see you again soon!";
                }
            } catch (DukeException e) {
                return e.getMessage();
            }
        }
        return output;
    }
}
