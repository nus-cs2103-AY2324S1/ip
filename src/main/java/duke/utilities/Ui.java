package duke.utilities;

import java.util.ArrayList;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.exceptions.EmptyListException;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidCommandSyntaxException;
import duke.exceptions.InvalidTaskNumberException;
import duke.exceptions.InvalidTaskTimeException;
import duke.exceptions.MissingTaskDescriptionException;
import duke.exceptions.MissingTaskNameException;
import duke.exceptions.MissingTaskNumberException;

/**
 * Handles user interaction
 */
public class Ui {

    /** Variable to detect user input */
    private Scanner sc = new Scanner(System.in);

    /** Variable to show horizontal lines */
    public static final String LINE_BREAK = ("--------------------------------------------------"
            + "---------------------------------");

    /**
     * Prints greetings to the user interface
     */
    public void greet() {
        System.out.println("\n");
        System.out.println(LINE_BREAK);
        System.out.println("Welcome. My name is Duke");
        System.out.println("What will you do today?");
    }

    /**
     * Allows users to type in their inputs
     * 
     * @return Input as String
     */
    public String startInputSession() {
        System.out.println(LINE_BREAK);
        System.out.println("\n");
        String input = sc.nextLine().trim();
        System.out.println(LINE_BREAK);
        return input;
    }

    /**
     * Shows the contents of the list of tasks
     *
     * @param tasks The list of tasks
     * @param fullInput The full user input
     * @param numberOfWords The number of words in the user input
     * @throws InvalidCommandSyntaxException If there are words after the "list" command
     */
    public void showList(TaskList tasks, String fullInput, int numberOfWords)
            throws InvalidCommandSyntaxException {
        if (numberOfWords > 1) {
            throw new InvalidCommandSyntaxException("'list' command must not be followed by anything");
        }
        if (tasks.getSize() == 0) {
            System.out.println("List is empty");
        } else {
            for (int i = 1; i < tasks.getSize() + 1; i++) {
                Task current = tasks.getTask(i - 1);
                System.out.println(i + ". " + current.convertToString());
            }
        }
    }

    /**
     * Shows output text when a task is marked, unmarked, or deleted
     *
     * @param tasks The list of tasks
     * @param command The first word of the input
     * @param fullInput The full user input
     * @param numberOfWords The number of words in the user input
     * @throws MissingTaskNumberException If the command is not followed by any number
     * @throws EmptyListException If the list of tasks is empty
     * @throws InvalidTaskNumberException If the task number to be manipulated does not exist
     */
    public void showManipulateTasks(TaskList tasks, String command, String fullInput, int numberOfWords)
            throws MissingTaskNumberException, EmptyListException, InvalidTaskNumberException {
        if (numberOfWords == 1) {
            throw new MissingTaskNumberException("Task number cannot be empty");
        } else if (tasks.getSize() < 1) {
            throw new EmptyListException("List is currently empty");
        }
        switch (command) {
        case "mark":
            tasks.manipulateTasks(fullInput, "mark", 5);
            break;
        case "unmark":
            tasks.manipulateTasks(fullInput, "unmark", 7);
            break;
        case "delete":
            tasks.manipulateTasks(fullInput, "delete", 7);
            break;
        default:
            break;
        }
    }

    /**
     * Shows text output when a todo task is added
     *
     * @param tasks The list of tasks
     * @param fullInput The full user input
     * @param numberOfWords The number of words in the user input
     * @throws MissingTaskDescriptionException If the command is not followed by any description
     */
    public void showAddToDo(TaskList tasks, String fullInput, int numberOfWords)
            throws MissingTaskDescriptionException {
        if (numberOfWords <= 1) {
            throw new MissingTaskDescriptionException("Todo task description cannot be empty");
        }
        String taskName = fullInput.substring(5);
        tasks.addToDo(taskName);
    }

    /**
     * Shows text output when a deadline task is added
     *
     * @param tasks The list of tasks
     * @param fullInput The full user input
     * @param numberOfWords The number of words in the user input
     * @param parser The parser to parse dates
     * @throws MissingTaskDescriptionException If the command is not followed by any description
     * @throws MissingTaskNameException If the task name is not specified
     * @throws InvalidTaskTimeException If there are missing or more than one deadlines
     */
    public void showAddDeadline(TaskList tasks, String fullInput, int numberOfWords, Parser parser)
            throws MissingTaskDescriptionException, MissingTaskNameException, InvalidTaskTimeException {
        if (numberOfWords <= 1) {
            throw new MissingTaskDescriptionException("Deadline task description cannot be empty");
        }
        String[] taskDesc = fullInput.substring(9).split("/by");
        if (taskDesc.length != 2) {
            throw new InvalidTaskTimeException("Deadline task must have exactly one /by deadline");
        }
        String taskName = taskDesc[0].trim();
        if (taskName.length() == 0) {
            throw new MissingTaskNameException("Deadline task name cannot be empty");
        }
        String strDeadline = taskDesc[1].trim();
        String deadline = parser.formatDate(strDeadline);
        if (!deadline.equals("Invalid date")) {
            tasks.addDeadline(taskName, deadline);
        }
    }

    /**
     * Shows text output when an event task is added
     *
     * @param tasks The list of tasks
     * @param fullInput The full user input
     * @param numberOfWords The number of words in the user input
     * @param parser The parser to parse dates
     * @throws MissingTaskDescriptionException If the command is not followed by any description
     * @throws MissingTaskNameException If the task name is not specified
     * @throws InvalidTaskTimeException If there are missing or more than one start or end dates
     */
    public void showAddEvent(TaskList tasks, String fullInput, int numberOfWords, Parser parser)
            throws MissingTaskDescriptionException, MissingTaskNameException, InvalidTaskTimeException {
        if (numberOfWords <= 1) {
            throw new MissingTaskDescriptionException("Event task description cannot be empty");
        }
        String[] taskDesc = fullInput.substring(6).split("/from");
        if (taskDesc.length != 2) {
            throw new InvalidTaskTimeException(
                    "Event task must have exactly one /from and one /to times, in that order");
        }
        String taskName = taskDesc[0].trim();
        String[] fromAndTo = taskDesc[1].split("/to");
        if (fromAndTo.length != 2) {
            throw new InvalidTaskTimeException(
                    "Event task must have exactly one /from and one /to times, in that order");
        }
        if (taskName.length() == 0) {
            throw new MissingTaskNameException("Event task name cannot be empty");
        }
        String strStart = fromAndTo[0].trim();
        String strEnd = fromAndTo[1].trim();
        String start = parser.formatDate(strStart);
        if (!start.equals("Invalid date")) {
            String end = parser.formatDate(strEnd);
            if (!end.equals("Invalid date")) {
                tasks.addEvent(taskName, start, end);
            }
        }
    }

    /**
     * Shows text output of the result for searching tasks
     *
     * @param tasks The list of tasks
     * @param fullInput The full user input
     * @param numberOfWords The number of words in the user input
     * @throws MissingTaskDescriptionException If command is not followed by any description
     */
    public void showFind(TaskList tasks, String fullInput, int numberOfWords)
            throws MissingTaskDescriptionException {
        if (numberOfWords <= 1) {
            throw new MissingTaskDescriptionException("Todo task description cannot be empty");
        }
        String taskToBeFound = fullInput.substring(5);
        ArrayList<Task> tasksFiltered = tasks.filterTaskName(taskToBeFound);
        int size = tasksFiltered.size();
        if (size > 0) {  
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < size; i++) {
                System.out.println((i + 1) + ". " + tasksFiltered.get(i).convertToString());
            }
        } else {
            System.out.println("There are no matching tasks in your list");
        }
    }

    /**
     * Shows text output when the user exits the chatbot
     *
     * @param tasks The list of tasks
     * @param numberOfWords The number of words in the user input
     * @throws InvalidCommandSyntaxException If there are words after the "bye" command
     */
    public void showBye(TaskList tasks, int numberOfWords)
            throws InvalidCommandSyntaxException {
        if (numberOfWords > 1) {
            throw new InvalidCommandSyntaxException("'bye' command must not be followed by anything");
        }
        System.out.println("I hope you enjoy my service. Thank you and goodbye");
        System.out.println(LINE_BREAK);
    }

    /**
     * Handles the various cases of user inputs
     *
     * @param tasks TaskList object that contains the list
     * @param input Input object that contains parsed user input
     * @param parser Parser used to parse user inputs
     * @return True or false signifying breaking or continuing the loop
     */
    public boolean handleInput(TaskList tasks, Input input, Parser parser) {
        boolean endSession = true;
        try {
            String command = input.getCommand();
            String fullInput = input.getFullInput();
            int numberOfWords = input.getLength();
            switch (command) {
            case "list":
                showList(tasks, fullInput, numberOfWords);
                break;
            case "mark":
            case "unmark":
            case "delete":
                showManipulateTasks(tasks, command, fullInput, numberOfWords);
                break;
            case "todo":
                showAddToDo(tasks, fullInput, numberOfWords);
                break;
            case "deadline":
                showAddDeadline(tasks, fullInput, numberOfWords, parser);
                break;
            case "event":
                showAddEvent(tasks, fullInput, numberOfWords, parser);
                break;
            case "find":
                showFind(tasks, fullInput, numberOfWords);
                break;
            case "bye":
                showBye(tasks, numberOfWords);
                endSession = false;
                break;
            default:
                throw new InvalidCommandException("No such command exists, please try again");
            }
        } catch (DukeException e) {
            System.out.println("!ERROR! " + e);
        }
        return endSession;
    }
}
