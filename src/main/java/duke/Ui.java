package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class deals with interactions with the user
 */
public class Ui {
    private static final String NAME = "Zac";
    /**
     * Prints out all the tasks in TaskList, which are stored in duke.txt
     * @param filePath The file where the lists of Tasks are stored
     * @throws FileNotFoundException If the file at this filePath is not found
     */
    public static String printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // opens the duke.txt file
        Scanner s = new Scanner(f); // reads the contents of the duke.txt file
        StringBuilder string = new StringBuilder();
        string.append("Hi, I am " + NAME + "!\nHere are the tasks in your list:\n");
        while (s.hasNext()) {
            string.append(s.nextLine() + "\n");
        }
        string.append("Type 'help' to see the list of things you can do");
        return string.toString();
    }

    /**
     * Displays user guide
     * @return String
     */
    public static String showUserGuide() {
        StringBuilder string = new StringBuilder();
        string.append("Here are examples of things you can do\n");
        string.append("1. todo <your task>\n");
        string.append("   eg. todo borrow book\n");
        string.append("2. deadline <your task> /by <time>\n");
        string.append("   eg. deadline return book /by sun 1700\n");
        string.append("3. event <your task> /from <time> /to <time>\n");
        string.append("   eg. event project meeting /from Mon 2pm /to 4pm\n");
        string.append("4. list (displays all your tasks)\n");
        string.append("   eg. list\n");
        string.append("5. mark <task number> (marks task 1 as completed)\n");
        string.append("   eg. mark 1\n");
        string.append("6. unmark <task number>\n");
        string.append("   eg. unmark 1 (marks task 2 as uncompleted)\n");
        string.append("7. delete <task number>\n");
        string.append("   eg. delete 1 (deletes task 1)\n");
        string.append("8. find <keyword>\n");
        string.append("   eg. find book (finds all task with description 'book')\n");
        string.append("9. undo (undo the latest command)\n");
        return string.toString();
    }
}
