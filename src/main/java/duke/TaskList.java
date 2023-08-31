package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that contains the task list e.g., it has operations to add/delete tasks in the list.
 */
public class TaskList {
    private List<Task> list;

    /**
     * Constructor for a TaskList object.
     *
     * @param list List from storage for TaskList object to point to.
     */
    public TaskList(List<Task> list) {
        this.list = list;
    }

    /**
     * Adds given task to task list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        this.list.add(task);
        System.out.println("-----------------------------------------------");
        System.out.println("Got it bro! I've added this task:\n" + task);
        System.out.println("Now you have " + this.list.size() + " tasks in the list");
        System.out.println("-----------------------------------------------");
    }

    /**
     * Extracts task index from user input and deletes the task.
     *
     * @param taskIndex The task index input by user.
     * @throws DukeException If the input is not a number or is out of range of the list.
     */
    public void deleteTask(String taskIndex) throws DukeException {
        try {
            int index = Integer.parseInt(taskIndex) - 1;
            list.get(index);
            System.out.println("-----------------------------------------------");
            System.out.println("Noted! I've removed this task from the list:");
            System.out.println("" + list.get(index));
            list.remove(index);
            System.out.println("Now you have " + list.size() + " tasks in the list");
            System.out.println("-----------------------------------------------");
        } catch (IndexOutOfBoundsException e) {
            // number input is invalid
            throw new DukeException("Invalid number");
        } catch (NumberFormatException e) {
            // user did not enter a number
            throw new DukeException("Please key in a number");
        }
    }

    /**
     * Extracts task index from user input and marks the task as done.
     *
     * @param taskIndex The task index input by user.
     * @throws DukeException If the input is not a number or is out of range of the list.
     */
    public void markTask(String taskIndex) throws DukeException {
        try {
            int index = Integer.parseInt(taskIndex) - 1;
            list.get(index).markAsDone();
            System.out.println("-----------------------------------------------");
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("" + list.get(index));
            System.out.println("-----------------------------------------------");
        } catch (IndexOutOfBoundsException e) {
            // number input is invalid
            throw new DukeException("Invalid number");
        } catch (NumberFormatException e) {
            // user did not enter a number
            throw new DukeException("Please key in a number");
        }
    }

    /**
     * Extracts task index from user input and marks the task as not done.
     *
     * @param taskIndex The task index input by user.
     * @throws DukeException If the input is not a number or is out of range of the list.
     */
    public void unmarkTask(String taskIndex) throws DukeException {
        try {
            int index = Integer.parseInt(taskIndex) - 1;
            list.get(index).unMark();
            System.out.println("-----------------------------------------------");
            System.out.println("Nice! I've marked this task as not done yet:");
            System.out.println("" + list.get(index));
            System.out.println("-----------------------------------------------");
        } catch (IndexOutOfBoundsException e) {
            // number input is invalid
            throw new DukeException("Invalid number");
        } catch (NumberFormatException e) {
            // user did not enter a number
            throw new DukeException("Please key in a number");
        }
    }

    /**
     * Extracts task description from user input and adds a todo task.
     *
     * @param input The text input by the user.
     * @throws DukeException If the text input does not match the required regex pattern.
     */
    public void addTodo(String input) throws DukeException {
        // Define regular expressions for pattern matching for todo
        Pattern todoPattern = Pattern.compile("todo\\s+(.*?)$");

        // Match the input string with the pattern
        Matcher matcher = todoPattern.matcher(input.trim());

        // Check if the input string matches the pattern
        if (matcher.matches()) {
            String description = matcher.group(1); // Extract task description
            Task task = new Todo(description);
            addTask(task);
        } else {
            // Todo description is empty
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty." +
                    "\ntodo ...");
        }
    }

    /**
     * Extracts task description, start time and end time from user input and adds an event task.
     *
     * @param input The text input by the user.
     * @throws DukeException If the text input does not match the required regex pattern.
     */
    public void addEvent(String input) throws DukeException {
        // Define regular expressions for pattern matching for event
        Pattern eventPattern = Pattern.compile("event\\s+(.*?)\\s+/from" +
                "\\s+(.*?)\\s+/to\\s+(.*?)$");

        // Match the input string with the pattern
        Matcher matcher = eventPattern.matcher(input);

        // Check if the input string matches the pattern
        if (matcher.matches()) {
            String description = matcher.group(1); // Extract event name
            LocalDate startDate = Parser.stringToDate(matcher.group(2)); // Extract start date
            LocalDate endDate = Parser.stringToDate(matcher.group(3));   // Extract end date
            Task task = new Event(startDate, endDate, description);
            addTask(task);
        } else {
            // User did not follow event format
            throw new DukeException("Input for event doesn't match the expected format." +
                    "\nevent ... /from ... /to ...");
        }
    }

    /**
     * Extracts task description and due date from user input and adds a deadline task.
     *
     * @param input The text input by the user.
     * @throws DukeException If the text input does not match the required regex pattern.
     */
    public void addDeadline(String input) throws DukeException {
        // Define regular expressions for pattern matching for deadline
        Pattern deadlinePattern = Pattern.compile("deadline\\s+(.*?)\\s+/by\\s+(.*?)$");

        // Match the input string with the pattern
        Matcher matcher = deadlinePattern.matcher(input);

        // Check if the input string matches the pattern
        if (matcher.matches()) {
            String description = matcher.group(1); // Extract task description
            LocalDate dueDate = Parser.stringToDate(matcher.group(2));  // Extract due date
            Task task = new Deadline(dueDate, description);
            addTask(task);
        } else {
            // User did not follow deadline format
            throw new DukeException("Input for deadline doesn't match the expected format." +
                    "\ndeadline ... /by ...");
        }
    }

    /**
     * Prints all tasks in the list currently.
     */
    public void printList() {
        System.out.println("-----------------------------------------------");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("" + (i + 1) + "." + list.get(i));
        }
        System.out.println("-----------------------------------------------");
    }

    /**
     * Prints all the tasks in the current list that matches the target string.
     *
     * @param target The search target to match to.
     */
    public void printMatchingTasks(String target) {
        System.out.println("-----------------------------------------------");
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).contains(target)) {
                System.out.println("" + (i + 1) + "." + list.get(i));
            }
        }
        System.out.println("-----------------------------------------------");
    }

    /**
     * Extracts the search target from the user input and calls printMatchingTasks
     * to print the results that match.
     *
     * @param input The text input by user.
     * @throws DukeException If the user left the search target blank.
     */
    public void find(String input) throws DukeException {
        // Define regular expressions for pattern matching for todo
        Pattern todoPattern = Pattern.compile("find\\s+(.*?)$");

        // Match the input string with the pattern
        Matcher matcher = todoPattern.matcher(input.trim());

        // Check if the input string matches the pattern
        if (matcher.matches()) {
            String target = matcher.group(1); // Extract search target
            printMatchingTasks(target); // Print results
        } else {
            // find description is empty
            throw new DukeException("OOPS!!! Field after find cannot be empty :(");
        }
    }
}
