import extensions.exceptions.DukeException;
import extensions.exceptions.DukeIllegalArgumentException;
import extensions.exceptions.DukeUnknownCommandException;
import extensions.tasks.Deadline;
import extensions.tasks.Event;
import extensions.tasks.Task;
import extensions.tasks.ToDo;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    // read user input
    private static final Scanner sc = new Scanner(System.in);
    // store list of tasks
    private static final ArrayList<Task> list = new ArrayList<>();
    // check if chat has ended
    private static boolean hasEndedChat = false;

    /**
     * Ends the chat when the user inputs "bye".
     */
    private static void endChat() {
        hasEndedChat = true;
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the current list of tasks.
     */
    private static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        if (list.isEmpty()) {
            System.out.println("You have no tasks in your list.");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + "." + list.get(i));
            }
        }
    }

    /**
     * Prints an OK message when a task is added.
     */
    private static void printAddTaskMessage() {
        System.out.println("Got it. I've added this task:");
    }

    /**
     * Prints the number of tasks in the list after adding/removing a task.
     */
    private static void printNumberOfTasks() {
        String taskOrTasks = list.size() == 1 ? "task" : "tasks";
        System.out.printf("Now you have %d %s in the list.%n", list.size(), taskOrTasks);
    }

    /**
     * Marks a task as done.
     *
     * @param num The number of the task to be marked as done.
     * @throws DukeIllegalArgumentException If the task number is out of range of the list.
     */
    private static void markTaskAsDone(int num) throws DukeIllegalArgumentException {
        int index = num - 1;
        if (index < 0 || index >= list.size()) {
            throw new DukeIllegalArgumentException(
                    "The task number is out of range. Use \"list\" to see your tasks.");
        }
        list.get(index).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(list.get(index));
    }

    /**
     * Marks a task as undone.
     *
     * @param num The number of the task to be marked as undone.
     * @throws DukeIllegalArgumentException If the task number is out of range of the list.
     */
    private static void unmarkTaskAsDone(int num) throws DukeIllegalArgumentException {
        int index = num - 1;
        if (index < 0 || index >= list.size()) {
            throw new DukeIllegalArgumentException(
                    "The task number is out of range. Use \"list\" to see your tasks.");
        }
        list.get(index).unmarkAsDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(list.get(index));
    }

    /**
     * Adds a ToDo task to the list.
     *
     * @param description The description of the ToDo task.
     */
    private static void addToDoTask(String description) throws DukeIllegalArgumentException {
        if (description.isBlank()) {
            throw new DukeIllegalArgumentException("The description of a ToDo task cannot be blank.");
        }
        Task task = new ToDo(description);
        list.add(task);
        printAddTaskMessage();
        System.out.println(task);
        printNumberOfTasks();
    }

    /**
     * Adds a Deadline task to the list.
     *
     * @param description The description of the Deadline task.
     * @param by The date/time of the Deadline task.
     */
    private static void addDeadlineTask(String description, String by) throws DukeIllegalArgumentException {
        if (description.isBlank()) {
            throw new DukeIllegalArgumentException("The description of a Deadline task cannot be blank.");
        }
        if (by.isBlank()) {
            throw new DukeIllegalArgumentException("The date/time of a Deadline task cannot be blank.");
        }
        Task task = new Deadline(description, by);
        list.add(task);
        printAddTaskMessage();
        System.out.println(task);
        printNumberOfTasks();
    }

    /**
     * Adds an Event task to the list.
     *
     * @param description The description of the Event task.
     * @param start The start date/time of the Event task.
     * @param end The end date/time of the Event task.
     */
    private static void addEventTask(String description, String start, String end)
            throws DukeIllegalArgumentException {
        if (description.isBlank()) {
            throw new DukeIllegalArgumentException("The description of an Event task cannot be blank.");
        }
        if (start.isBlank()) {
            throw new DukeIllegalArgumentException("The start date/time of an Event task cannot be blank.");
        }
        if (end.isBlank()) {
            throw new DukeIllegalArgumentException("The end date/time of an Event task cannot be blank.");
        }
        Task task = new Event(description, start, end);
        list.add(task);
        printAddTaskMessage();
        System.out.println(task);
        printNumberOfTasks();
    }

    /**
     * Deletes a task from the list.
     *
     * @param num The number of the task to be deleted.
     * @throws DukeIllegalArgumentException If the task number is out of range of the list.
     */
    private static void deleteTask(int num) throws DukeIllegalArgumentException {
        int index = num - 1;
        if (index < 0 || index >= list.size()) {
            throw new DukeIllegalArgumentException(
                    "The task number is out of range. Use \"list\" to see your tasks.");
        }
        Task task = list.remove(index);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        printNumberOfTasks();
    }

    /**
     * Parses the input.
     *
     * @param input The input from the user.
     * @return A String array where the 1st element is the command and the 2nd element is the argument.
     */
    private static String[] parseCommand(String input) {
        // check if input is a command with arguments (e.g. mark 2)
        String[] splitInputs = input.split("\\s+");

        if (splitInputs.length > 1) {
            // the user has input a command with arguments
            String command = splitInputs[0];
            String argument = input.substring(command.length() + 1);
            return new String[]{command, argument};
        } else {
            // the user has input a command without arguments
            return new String[]{input, ""};
        }
    }

    private static void executeCommand(String[] command) throws DukeException {
        switch (command[0]) {
        case "bye":
            endChat();
            break;
        case "list":
            listTasks();
            break;
        case "mark":
            try {
                markTaskAsDone(Integer.parseInt(command[1]));
            } catch (NumberFormatException e) {
                throw new DukeIllegalArgumentException(
                        "Please enter a valid task number. You entered: \"" + command[1] + "\"");
            }
            break;
        case "unmark":
            try {
                unmarkTaskAsDone(Integer.parseInt(command[1]));
            } catch (NumberFormatException e) {
                throw new DukeIllegalArgumentException(
                        "Please enter a valid task number. You entered: \"" + command[1] + "\"");
            }
            break;
        case "todo":
            addToDoTask(command[1]);
            break;
        case "deadline":
            if (!command[1].contains(" /by ")) {
                throw new DukeIllegalArgumentException("Deadline Task is missing a \"/by\" field.");
            }
            String[] deadlineArgs = command[1].split(" /by ");
            if (deadlineArgs.length != 2) {
                throw new DukeIllegalArgumentException("Deadline Task is missing a description and/or date/time.");
            }
            addDeadlineTask(deadlineArgs[0], deadlineArgs[1]);
            break;
        case "event":
            if (!command[1].contains(" /from ") || !command[1].contains(" /to ")) {
                throw new DukeIllegalArgumentException("Event Task is missing \"/from\" and/or \"/to\" fields.");
            }
            String[] eventArgs = command[1].split(" /from ");
            String[] eventTimes = eventArgs[1].split(" /to ");
            addEventTask(eventArgs[0], eventTimes[0], eventTimes[1]);
            break;
        case "delete":
            try {
                deleteTask(Integer.parseInt(command[1]));
            } catch (NumberFormatException e) {
                throw new DukeIllegalArgumentException(
                        "Please enter a valid task number. You entered: \"" + command[1] + "\"");
            }
            break;
        default:
            throw new DukeUnknownCommandException(
                    "I'm sorry, but I don't know what \"" + command[0] + "\" means.");
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Max!");
        System.out.println("What can I do for you?");

        while (!hasEndedChat) {
            String input = sc.nextLine();
            String[] command = parseCommand(input);
            try {
                executeCommand(command);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
