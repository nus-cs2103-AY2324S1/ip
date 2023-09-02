
import java.io.File;
import java.util.Scanner;

import duke.Ui;
import duke.Parser;
import duke.TaskListStorage;
import duke.exceptions.IncorrectCommandFormatException;
import duke.exceptions.InvalidIndexException;
import duke.exceptions.InvalidTimeFormatException;
import duke.exceptions.MissingDescriptionException;
import duke.exceptions.UnknownCommandException;

/**
 * Main class of the Duke program.
 */
public class Duke {
    private static final String TASK_FILEPATH = "." + File.separator + "data" + File.separator + "tasks.txt";

    /**
     * Main entry-point for the java.duke.Duke application.
     * 
     * @param args The command line arguments, for now it serves no purpose.
     */
    public static void main(String[] args) {
        TaskListStorage tasklistStorage = new TaskListStorage(TASK_FILEPATH);
        Parser parser = new Parser();

        Ui.opener();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            try {
                parser.dispatch(input).execute(tasklistStorage);
            } catch (UnknownCommandException | MissingDescriptionException | IncorrectCommandFormatException
                    | InvalidIndexException | InvalidTimeFormatException e) {
                Ui.printInLine(e.getMessage());
            }
            input = sc.nextLine();
        }
        sc.close();
        Ui.printWithTab("Bye. Hope to see you again soon!");
    }
}
