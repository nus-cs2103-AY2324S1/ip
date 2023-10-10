package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class deals with interactions with the user
 */
public class Ui {
    private static final String NAME = "Zac";
    private static final String MEGA_INDENT = "    ";

    /**
     * Prints out all the tasks in TaskList, which are stored in duke.txt
     * @param filePath The file where the list of Tasks is stored
     * @throws FileNotFoundException If the file at this file path is not found
     */
    public static String printFileContents(Path filePath) throws FileNotFoundException {
        StringBuilder string = new StringBuilder();
        try {
            Scanner s = new Scanner(Files.newBufferedReader(filePath)); // reads the contents of the duke.txt file
            string.append("Hi, I am " + NAME + "!\nHere are the tasks in your list:\n");
            while (s.hasNext()) {
                string.append(s.nextLine() + "\n");
            }
            string.append("Type 'help' to see the list of things you can do");
            return string.toString();
        } catch (IOException e) {
            System.err.println(e + " error printing file contents");
        }
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
        string.append("10. bye (ends the chat)\n");
        return string.toString();
    }

    /**
     * Prints out all the tasks in the TaskList
     * @param tasks TaskList
     * @return Output
     */
    public static String displayList(ArrayList<Task> tasks) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            int num = i + 1;
            Task curr = tasks.get(i);
            stringBuilder.append(num + ". " + curr.toString() + "\n");
        }
        return stringBuilder.toString();
    }
    /**
     * Prints out the tasks that matches the user input in the find command
     * @param tasks TaskList that contains only tasks that match the user input
     * @return Output
     */
    public static String displayMatchingList(ArrayList<Task> tasks) {
        StringBuilder stringBuilder = new StringBuilder();
        if (tasks.isEmpty()) {
            stringBuilder.append("There are no matching tasks\n");
        } else {
            stringBuilder.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                int num = i + 1;
                Task curr = tasks.get(i);
                stringBuilder.append(num + ". " + curr.toString() + "\n");
            }
        }
        return stringBuilder.toString();
    }

    /**
     * Handles Ui for addTask method
     * @param tasksSize Size of TaskList
     * @param newlyAddedTask Most recently added Task
     * @return Output
     */
    public static String handleAddTaskUi(int tasksSize, Task newlyAddedTask) {
        StringBuilder string = new StringBuilder();
        string.append("Got it. I've added this task:\n");
        string.append(MEGA_INDENT + newlyAddedTask.toString() + "\n");
        string.append("Now you have " + tasksSize + " tasks in the list.\n");
        return string.toString();
    }

    /**
     * Handles Ui for markTask method
     * @param userInput mark or unmark
     * @param curr The Task that is marked/unmarked
     * @return Output
     */
    public static String handleMarkTaskUi(String userInput, Task curr) {
        StringBuilder stringBuilder = new StringBuilder();
        if (userInput.contains("unmark")) {
            stringBuilder.append("OK, I've marked this task as not done yet:\n");
        } else if (userInput.contains("mark")) {
            stringBuilder.append("Nice! I've marked this task as done:\n");
        }
        stringBuilder.append(MEGA_INDENT + curr.getStatusIconWithBracket() + " " + curr.getDescription() + "\n");
        return stringBuilder.toString();
    }

    /**
     * Handles Ui for deleteTask method
     * @param deletedTask Deleted Task
     * @param tasksSize Size of TaskList
     * @return Output
     */
    public static String handleDeleteTaskUi(Task deletedTask, int tasksSize) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Noted. I've removed this task:\n");
        stringBuilder.append(MEGA_INDENT + deletedTask.toString() + "\n");
        stringBuilder.append("Now you have " + tasksSize + " tasks in the list.\n");
        return stringBuilder.toString();
    }
    public static String handleUndoUi() {
        return "Most recent move undone :)\n";
    }
    public static String handleExitUi() {
        return "Thanks for coming!";
    }
    public static String handleInvalidCommandUi() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
