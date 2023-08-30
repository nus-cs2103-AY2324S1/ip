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
    private static final String mark = "mark";
    private static final String unmark = "unmark";
    private static final String bye = "bye";
    private static final String list = "list";
    private static final String delete = "delete";
    private static final String FIND = "find";
    private static final Path filePath = Paths.get("./data/bot.txt");
    private Tasklist todolist;

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
    private Tasklist todoList;

    /**
     * Represents the storage functionality for Duke.
     */
    private Storage storage;

    /**
     * Constructs a new instance of the Duke command line-based task manager.
     */
    private Duke() {
        this.todoList = new Tasklist();
        this.storage = new Storage(FILE_PATH);
    }

    /**
     * Saves tasks to storage and exits the application.
     */
    private void exit() {
        try {
            storage.saveFile(todoList);
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
            Ui.printList(todoList);
            return true;
        } else if (check1.equals(MARK)) {
            todoList.mark(Integer.parseInt(str.substring(5, str.length())));
            return true;
        } else if (check1.equals(FIND)) {
            Ui.find(todoList.find(str.substring(5, str.length())));
            return true;
        } else if (check2.equals(unmark)) {
            todolist.unmark(Integer.parseInt(str.substring(7, str.length())));
        } else if (check2.equals(UNMARK)) {
            todoList.unmark(Integer.parseInt(str.substring(7, str.length())));
            return true;
        } else if (check2.equals(DELETE)) {
            todoList.delete(Integer.parseInt(str.substring(7, str.length())));
            return true;
        } else {
            try {
                todoList.addToList(s);
            } catch (DukeMissingArgumentException | DukeInvalidArgumentException e) {
                System.out.println(e.toString());
            }
            return true;
        }
        return true;
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
