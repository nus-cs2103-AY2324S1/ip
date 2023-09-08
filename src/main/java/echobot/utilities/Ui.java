package echobot.utilities;

import java.util.ArrayList;
import java.util.Scanner;

import echobot.EchoBot;
import echobot.exceptions.EmptyListException;
import echobot.exceptions.InvalidCommandSyntaxException;
import echobot.exceptions.InvalidTaskNumberException;
import echobot.exceptions.InvalidTaskTimeException;
import echobot.exceptions.MissingTaskDescriptionException;
import echobot.exceptions.MissingTaskNameException;
import echobot.exceptions.MissingTaskNumberException;

/**
 * Handles user interaction
 */
public class Ui {

    /** Variable to detect user input */
    private Scanner sc = new Scanner(System.in);

    /**
     * Prints greetings to the user interface
     */
    public void greet() {
        System.out.println("Welcome. My name is Duke");
        System.out.println("What will you do today?");
    }

    /**
     * Allows users to type in their inputs
     *
     * @return Input as String
     */
    public String startInputSession() {
        return sc.nextLine().trim();
    }

    /**
     * Shows the contents of the list of tasks
     *
     * @param tasks The list of tasks
     * @param numberOfWords The number of words in the user input
     * @return String output showing tasks
     * @throws InvalidCommandSyntaxException If there are words after the "list" command
     */
    public String showList(TaskList tasks, int numberOfWords)
            throws InvalidCommandSyntaxException {
        if (numberOfWords > 1) {
            throw new InvalidCommandSyntaxException("'list' command must not be followed by anything");
        }
        if (tasks.getSize() == 0) {
            return "List is empty";
        } else {
            StringBuilder output = new StringBuilder();
            for (int i = 1; i < tasks.getSize() + 1; i++) {
                Task current = tasks.getTask(i - 1);
                output.append(i).append(". ").append(current.convertToString()).append("\n");
            }
            return output.toString();
        }
    }

    /**
     * Shows output text when a task is marked, unmarked, or deleted
     *
     * @param tasks The list of tasks
     * @param command The first word of the input
     * @param fullInput The full user input
     * @param numberOfWords The number of words in the user input
     * @return String output showing changed task mark
     * @throws MissingTaskNumberException If the command is not followed by any number
     * @throws EmptyListException If the list of tasks is empty
     * @throws InvalidTaskNumberException If the task number to be manipulated does not exist
     */
    public String showManipulateTasks(TaskList tasks, String command, String fullInput, int numberOfWords)
            throws MissingTaskNumberException, EmptyListException, InvalidTaskNumberException {
        if (numberOfWords == 1) {
            throw new MissingTaskNumberException("Task number cannot be empty");
        } else if (tasks.getSize() < 1) {
            throw new EmptyListException("List is currently empty");
        }
        String output = "";
        switch (command) {
        case "mark":
            output = tasks.manipulateTasks(fullInput, "mark", 5);
            break;
        case "unmark":
            output = tasks.manipulateTasks(fullInput, "unmark", 7);
            break;
        case "delete":
            output = tasks.manipulateTasks(fullInput, "delete", 7);
            break;
        default:
            break;
        }
        return output;
    }

    /**
     * Shows text output when a todo task is added
     *
     * @param tasks The list of tasks
     * @param fullInput The full user input
     * @param numberOfWords The number of words in the user input
     * @return String output showing added todo task
     * @throws MissingTaskDescriptionException If the command is not followed by any description
     */
    public String showAddToDo(TaskList tasks, String fullInput, int numberOfWords)
            throws MissingTaskDescriptionException {
        if (numberOfWords <= 1) {
            throw new MissingTaskDescriptionException("Todo task description cannot be empty");
        }
        String taskName = fullInput.substring(5);
        return tasks.addToDo(taskName);
    }

    /**
     * Shows text output when a deadline task is added
     *
     * @param tasks The list of tasks
     * @param fullInput The full user input
     * @param numberOfWords The number of words in the user input
     * @param parser The parser to parse dates
     * @return String output showing added deadline task
     * @throws MissingTaskDescriptionException If the command is not followed by any description
     * @throws MissingTaskNameException If the task name is not specified
     * @throws InvalidTaskTimeException If there are missing or more than one deadlines
     */
    public String showAddDeadline(TaskList tasks, String fullInput, int numberOfWords, Parser parser)
            throws MissingTaskDescriptionException, MissingTaskNameException, InvalidTaskTimeException {
        if (numberOfWords <= 1) {
            throw new MissingTaskDescriptionException("Deadline task description cannot be empty");
        }
        String[] taskDesc = fullInput.substring(9).split("/by");
        if (taskDesc.length != 2) {
            throw new InvalidTaskTimeException("Deadline task must have exactly one deadline using /by tag");
        }
        String taskName = taskDesc[0].trim();
        if (taskName.length() == 0) {
            throw new MissingTaskNameException("Deadline task name cannot be empty");
        }
        String strDeadline = taskDesc[1].trim();
        String deadline = parser.formatDate(strDeadline);
        if (!deadline.equals("Invalid date")) {
            return tasks.addDeadline(taskName, deadline);
        } else {
            return "\nDate needs to be in the form of yyyy-mm-dd";
        }
    }

    /**
     * Shows text output when an event task is added
     *
     * @param tasks The list of tasks
     * @param fullInput The full user input
     * @param numberOfWords The number of words in the user input
     * @param parser The parser to parse dates
     * @return String output showing added event task
     * @throws MissingTaskDescriptionException If the command is not followed by any description
     * @throws MissingTaskNameException If the task name is not specified
     * @throws InvalidTaskTimeException If there are missing or more than one start or end dates
     */
    public String showAddEvent(TaskList tasks, String fullInput, int numberOfWords, Parser parser)
            throws MissingTaskDescriptionException, MissingTaskNameException, InvalidTaskTimeException {
        if (numberOfWords <= 1) {
            throw new MissingTaskDescriptionException("Event task description cannot be empty");
        }
        String[] taskDesc = fullInput.substring(6).split("/from");
        if (taskDesc.length != 2) {
            throw new InvalidTaskTimeException(
                    "Event task must have exactly one start and one end dates"
                    + "using one /from and one /to tags, in that order");
        }
        String taskName = taskDesc[0].trim();
        String[] fromAndTo = taskDesc[1].split("/to");
        if (fromAndTo.length != 2) {
            throw new InvalidTaskTimeException(
                    "Event task must have exactly one start and one end dates"
                            + "using one /from and one /to tags, in that order");
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
                return tasks.addEvent(taskName, start, end);
            } else {
                return "\nDate needs to be in the form of yyyy-mm-dd";
            }
        } else {
            return "\nDate needs to be in the form of yyyy-mm-dd";
        }
    }

    /**
     * Shows text output of the result for searching tasks
     *
     * @param tasks The list of tasks
     * @param fullInput The full user input
     * @param numberOfWords The number of words in the user input
     * @return String output showing found tasks with specified keywords
     * @throws MissingTaskDescriptionException If command is not followed by any description
     */
    public String showFind(TaskList tasks, String fullInput, int numberOfWords)
            throws MissingTaskDescriptionException {
        if (numberOfWords <= 1) {
            throw new MissingTaskDescriptionException("Todo task description cannot be empty");
        }
        String taskToBeFound = fullInput.substring(5);
        ArrayList<Task> tasksFiltered = tasks.filterTaskName(taskToBeFound);
        int size = tasksFiltered.size();
        if (size > 0) {
            StringBuilder output = new StringBuilder();
            output.append("Here are the matching tasks in your list:");
            for (int i = 0; i < size; i++) {
                output.append("\n").append(i + 1).append(". ").append(tasksFiltered.get(i).convertToString());
            }
            return output.toString();
        } else {
            return "There are no matching tasks in your list";
        }
    }

    /**
     * Shows text output when the user exits the chatbot
     *
     * @param numberOfWords The number of words in the user input
     * @return String output showing goodbye
     * @throws InvalidCommandSyntaxException If there are words after the "bye" command
     */
    public String showBye(int numberOfWords)
            throws InvalidCommandSyntaxException {
        if (numberOfWords > 1) {
            throw new InvalidCommandSyntaxException("'bye' command must not be followed by anything");
        }
        EchoBot.stopBot();
        return "I hope you enjoy my service. Thank you and goodbye";
    }
}
