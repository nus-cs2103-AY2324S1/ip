package duke;

import duke.exceptions.DukeInvalidArgumentException;
import duke.exceptions.DukeMissingArgumentException;
import duke.task.Tasklist;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * Represents a command line-based task manager.
 */
public class Duke {

    /**
     * String constant for the find command.
     */
    private static final String FIND = "find";

    /**
     * String constant for the mark command.
     */
    private static final String MARK = "mark";

    /**
     * String constant for the unmark command.
     */
    private static final String UNMARK = "unmark";

    /**
     * String constant for the exit command.
     */
    private static final String BYE = "bye";

    /**
     * String constant for the list command.
     */
    private static final String LIST = "list";

    /**
     * String constant for the delete command.
     */
    private static final String DELETE = "delete";

    /**
     * Path for the storage file.
     */
    private static final Path FILE_PATH = Paths.get("./data/bot.txt");

    /**
     * Represents the list of tasks.
     */
    private Tasklist tasks;

    /**
     * Represents the storage functionality for Duke.
     */
    private Storage storage;



    /**
     * Constructs a new instance of the Duke command line-based task manager.
     */
    public Duke() {
        this.tasks = new Tasklist();
        this.storage = new Storage(FILE_PATH);
    }

    /**
     * Saves tasks to storage and exits the application.
     */
    private void exit() {
        try {
            storage.saveFile(tasks);
            Ui.exit();
        } catch (IOException e) {
            System.out.println("Error when saving data!");
        }
    }

    /**
     * Processes the user input and responds accordingly.
     * @param s The user's input string.
     * @return false if the user inputs the exit command; true otherwise.
     */
    private boolean respond(String s) {
        StringBuilder str = new StringBuilder(s);
        String check1 = "";
        String check2 = "";
        //2 over so that delete and mark cannot have blank input
        if (s.length() >= 6) {
            check1 = str.substring(0, 4);
        }
        if (s.length() >= 8) {
            check2 = str.substring(0, 6);
        }
        if (s.equals(BYE)) {
            this.exit();
            return false;
        } else if (s.equals(LIST)) {
            Ui.printList(tasks);
            return true;
        } else if (check1.equals(MARK)) {
            tasks.mark(Integer.parseInt(str.substring(5, str.length())));
            return true;
        } else if (check1.equals(FIND)) {
            Ui.find(tasks.find(str.substring(5, str.length())));
            return true;
        } else if (check2.equals(UNMARK)) {
            tasks.unmark(Integer.parseInt(str.substring(7, str.length())));
            return true;
        } else if (check2.equals(DELETE)) {
            tasks.delete(Integer.parseInt(str.substring(7, str.length())));
            return true;
        } else {
            try {
                tasks.addToList(s);
            } catch (DukeMissingArgumentException | DukeInvalidArgumentException e) {
                System.out.println(e.toString());
            }
            return true;
        }
    }



    /**
     * Processes the user input and responds accordingly.
     * @param s The user's input string.
     * @return The response
     */
    public String getResponse(String s) {
        StringBuilder str = new StringBuilder(s);
        String check1 = "";
        String check2 = "";
        //2 over so that delete and mark cannot have blank input
        if (s.length() >= 6) {
            check1 = str.substring(0, 4);
        }
        if (s.length() >= 8) {
            check2 = str.substring(0, 6);
        }
        if (s.equals(BYE)) {
            return Ui.exit();
        } else if (s.equals(LIST)) {
            return Ui.printList(tasks);
        } else if (check1.equals(MARK)) {
            return tasks.mark(Integer.parseInt(str.substring(5, str.length())));
        } else if (check1.equals(FIND)) {
            return Ui.find(tasks.find(str.substring(5, str.length())));
        } else if (check2.equals(UNMARK)) {
            return tasks.unmark(Integer.parseInt(str.substring(7, str.length())));
        } else if (check2.equals(DELETE)) {
            return tasks.delete(Integer.parseInt(str.substring(7, str.length())));
        } else {
            String result = "";
            try {
                result = tasks.addToList(s);
            } catch (DukeMissingArgumentException | DukeInvalidArgumentException e) {
                System.out.println(e.toString());
            }
            return result;
        }
    }

    /**
     * The entry point of the Duke application.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Duke dukeInstance = new Duke();
        Ui.greet();
        while (scanner.hasNextLine()) {
            String t = scanner.nextLine();
            if (!dukeInstance.respond(t)) {
                return;
            }
        }
    }
}
