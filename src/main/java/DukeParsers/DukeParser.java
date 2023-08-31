package DukeParsers;

import DukeTaskList.DukeTaskList;
import DukeTasks.Task;
import DukeUIClasses.DukeUi;

import java.util.Scanner;

/**
 * Encapsulates a class that parses input.
 *
 * @author Tan Kerway
 */
public class DukeParser {

    private final DukeUi ui;
    private final DukeTaskList taskListUtility;

    /**
     * Constructs a parser for Duke.
     *
     * @author Tan Kerway
     */
    public DukeParser(DukeTaskList taskListUtility) {
            this.ui = new DukeUi();
            this.taskListUtility = taskListUtility;
    }

    /**
     * Processes user commands. Breaks down the input and
     * Checks which command the user types in.
     *
     * @author Tan Kerway
     * @param input the input to be processed
     * @return true if the command is not "bye", false otherwise
     */
    private boolean processUserCommand(String input) {
        // case where the chatbot has been first initialised
        if (input == null) { return true; }
        // case where the input is "list" => enumerate all tasks
        if (input.equals("list")) {
            this.ui.listAllTasks(this.taskListUtility.getTasks());
            return true;
        }
        // case where the input is "bye" => terminate early
        if (input.equals("bye")) { return false; }
        // case where the input is the mark command => mark the task as done
        if (input.startsWith("mark")) {
            this.taskListUtility.handleMark(input);
            return true;
        }
        // case where the input is unmark
        if (input.startsWith("unmark")) {
            this.taskListUtility.handleUnmark(input);
            return true;
        }
        // case where the input is deleted
        if (input.startsWith("delete")) {
            this.taskListUtility.handleDelete(input);
            return true;
        }
        Task createdTask = this.taskListUtility.addTask(input);
        if (createdTask != null) {
            this.ui.echoTaskAdded(createdTask, this.taskListUtility.getTasks().size());
        }
        return true;
    }

    /**
     * Returns the length of the task list.
     *
     * @author Tan Kerway
     * @return the length of the tasklist
     */
    public int getTaskListSize() {
        return this.taskListUtility.getTasks().size();
    }

    /**
     * Parses the String. if there is error, this method will return null to
     * indicate unsuccessful parsing.
     *
     * @author Tan Kerway
     * @param numberString the number to parse
     * @return an integer if parsing was successful, null otherwise
     */
    public Integer parseString(String numberString) {
        int res = 0;
        // trim and trailing spaces
        numberString = numberString.trim();
        for (int i = 0; i < numberString.length(); i++) {
            // get the current char
            char currentChar = numberString.charAt(i);
            // gc: not a number
            if (!Character.isDigit(currentChar)) {
                return null;
            }
            // else, add to the res
            res = res * 10 + (currentChar - '0');
        }
        return res - 1 < 0 || res - 1 >= this.taskListUtility.getTasks().size() ? null : res;
    }

    /**
     * When called, awaits user input. If the input is "list", the tasks
     * that the user has added to the list so far is printed to the console.
     * If the input is "bye" the program will terminate. For other inputs,
     * the input will be added and saved as a task.
     *
     * @author Tan Kerway
     */
    public void handleUserInput() {
        Scanner sc = new Scanner(System.in);
        String input;
        boolean isNotDone;
        while (sc.hasNextLine()) {
            input = sc.nextLine(); // get the input from the user
            isNotDone = processUserCommand(input); // process the input
            if (!isNotDone) { break; }
        }
        sc.close();
    }
}
