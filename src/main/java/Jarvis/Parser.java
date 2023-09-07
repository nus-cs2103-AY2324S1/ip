package Jarvis;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Responsible for parsing user input, executing commands and handling any exceptions
 * <p>
 *     This class takes care of interpreting user commands and then calling the appropriate methods to perform
 *     the actions described by those commands. It also handles any exceptions and calls the appropriate methods
 *     to print the details of the exception for the user to read.
 * </p>
 */
public class Parser {
    protected static ArrayList<String> validCommands; // list of valid commands

    /**
     * Constructs a new Parser object.
     */
    public Parser() {
        validCommands = new ArrayList<>();
        addValidCommands();
    }

    /**
     * Adds the list of valid commands into the validCommands ArrayList.
     */
    private void addValidCommands() {
        validCommands.add("list");
        validCommands.add("bye");
        validCommands.add("mark");
        validCommands.add("uncheck");
        validCommands.add("todo");
        validCommands.add("deadline");
        validCommands.add("event");
        validCommands.add("find");
    }

    /**
     * checks if the user inputted a valid command in the validCommand ArrayList.
     *
     * @param inputCommand commands that the user inputs.
     * @return the valid command that the user inputted, an empty string otherwise.
     */
    // checks if command is valid and throws Jarvis.IncorrectJarvisCommandException
    private static String isValidCommand(String inputCommand) {
        // check if command is one of the valid keywords
        boolean isValidCommand = false;
        String validInputCommand = "";

        for (String validCommand: validCommands) {
            if (inputCommand.contains(validCommand)) {
                isValidCommand = true;
                validInputCommand = validCommand;
                break;
            }
        }

        if (!isValidCommand) {
            try {
                throw new IncorrectJarvisCommandException(
                        "Apologies Sir, the command " + inputCommand + " is not a valid command.");
            } catch (IncorrectJarvisCommandException e) {

            }
        }
        return validInputCommand;
    }

    /**
     * checks if the command the user inputted has a wrong format, if the format of the command is wrong,
     * print the details to the screen for the user to know how to correct it. This method assumes that
     * isValidCommand has been run before this and the user has used a valid command. It just checks if
     * the formatting of the command is correct.
     *
     * @param inputCommand the command the user inputted
     * @param validInputCommand the valid command which the user inputted
     */
    // identifies which command has wrong formatting and prints feedback to user
    public static String isWrongFormat(String inputCommand, String validInputCommand) {
        // since command is valid, check if formatting of the command is correct
        boolean markMatch = inputCommand.matches("mark \\d+");
        boolean uncheckMatch = inputCommand.matches("unmark \\d+");
        boolean todoMatch = inputCommand.matches("todo .+");
        boolean deadlineMatch = inputCommand.matches("deadline .+ /.+");
        boolean eventMatch = inputCommand.matches("event .+ /.+ /.+");

        if (validInputCommand.equals("mark") && !markMatch) { // if mark command but wrong format
            try {
                throw new WrongJarvisCommandFormatException(
                        "Apologies Sir, the format of the mark command you provided is incorrect.");
            } catch (WrongJarvisCommandFormatException e) {
                return Ui.getWrongFormatMessage("mark", e);
            }
        } else if (validInputCommand.equals("uncheck") && !uncheckMatch) { // if uncheck command but wrong format
            try {
                throw new WrongJarvisCommandFormatException(
                        "Apologies Sir, the format of the uncheck command you provided is incorrect.");
            } catch (WrongJarvisCommandFormatException e) {
                return Ui.getWrongFormatMessage("uncheck", e);
            }
        } else if (validInputCommand.equals("todo") && !todoMatch) { // if todo command but wrong format
            try {
                throw new WrongJarvisCommandFormatException(
                        "Apologies Sir, the format of the todo command you provided is incorrect.");
            } catch (WrongJarvisCommandFormatException e) {
                return Ui.getWrongFormatMessage("todo", e);
            }
        } else if (validInputCommand.equals("deadline") && !deadlineMatch) { // if deadline command but wrong format
            try {
                throw new WrongJarvisCommandFormatException(
                        "Apologies Sir, the format of the deadline command you provided is incorrect.");
            } catch (WrongJarvisCommandFormatException e) {
                return Ui.getWrongFormatMessage("deadline", e);
            }
        } else { // if event command but wrong format
            try {
                throw new WrongJarvisCommandFormatException(
                        "Apologies Sir, the format of the event command you provided is incorrect.");
            } catch (WrongJarvisCommandFormatException e) {
                return Ui.getWrongFormatMessage("event", e);
            }
        }
    }

    /**
     * Parses and executes command specified in the given user input.
     *
     * @param storage The storage object responsible for task storage operations.
     * @param tasks The task list that holds all tasks.
     * @param userInput The string input from the user.
     * @throws IncorrectJarvisCommandException If the command is incorrect.
     * @throws InvalidTaskNumberException If the task number is out of the range of the number of tasks.
     * @throws WrongJarvisCommandFormatException If the command is correct but its formatted incorrectly.
     */
    public static String parseCommand(Storage storage, TaskList tasks, String userInput) throws
            IncorrectJarvisCommandException, InvalidTaskNumberException, WrongJarvisCommandFormatException {

        Pattern todoPattern = Pattern.compile("(todo) (.+)");
        Pattern deadlinePattern = Pattern.compile("(deadline) (.+) /by (.+)");
        Pattern eventPattern = Pattern.compile("(event) (.+) /from (.+) /to (.+)");
        Pattern deletePattern = Pattern.compile("(delete) (\\d+)");
        Pattern findPattern = Pattern.compile("(find) (.+)");

        Matcher todoMatcher = todoPattern.matcher(userInput);
        Matcher deadlineMatcher = deadlinePattern.matcher(userInput);
        Matcher eventMatcher = eventPattern.matcher(userInput);
        Matcher deleteMatcher = deletePattern.matcher(userInput);
        Matcher findMatcher = findPattern.matcher(userInput);

        String nameOfValidCommand = isValidCommand(userInput);

        if (userInput.matches("list")) { // if "list" is entered
            return Ui.getTaskList(tasks);

        } else if (userInput.matches("bye")) { // if "bye" is entered
            storage.deleteContents();
            storage.save(tasks); // save task list to data file after bye is entered
            return Ui.getByeMessage();

        } else if (userInput.matches("mark \\d+")
                || userInput.matches("uncheck \\d+")) { // if "mark" or "uncheck" is entered

            int taskNum = Integer.parseInt(userInput.substring(userInput.length() - 1));
            if (!tasks.isValidTaskNumber(taskNum)) {
                return null;
            }

            Task currentTask = tasks.getTask(taskNum - 1);
            if (userInput.matches("uncheck \\d+")) { // if "uncheck" is entered
                currentTask.setDone(false);
                return Ui.getUncheckMessage(currentTask);

            } else { // if "mark" is entered
                currentTask.setDone(true);
                return Ui.getMarkMessage(currentTask);
            }

        } else if (todoMatcher.matches()
                || deadlineMatcher.matches()
                || eventMatcher.matches()) { // if task command is entered
            Task newTask;
            if (todoMatcher.matches()) { // if "todo" is entered
                String taskDescription = todoMatcher.group(2);
                newTask = new ToDo(taskDescription);

            } else if (deadlineMatcher.matches()) { // if "deadline" is entered
                String taskDescription = deadlineMatcher.group(2);
                String by = deadlineMatcher.group(3);
                newTask = new Deadline(taskDescription, by);

            } else { // if "event" is entered
                String taskDescription = eventMatcher.group(2);
                String from = eventMatcher.group(3);
                String to = eventMatcher.group(4);
                newTask = new Event(taskDescription, from, to);

            }
            tasks.appendTask(newTask);
            return Ui.getTaskMessage(tasks, newTask);

        } else if (deleteMatcher.matches()) { // if delete is entered
            int taskNum = Integer.parseInt(deleteMatcher.group(2));
            if (tasks.isValidTaskNumber(taskNum)) {
                Task deletedTask = tasks.getTask(taskNum - 1);
                tasks.deleteTask(taskNum - 1);
                return Ui.getDeleteMessage(tasks, deletedTask);
            }

        } else if (findMatcher.matches()) { // if find is entered
            String keyword = findMatcher.group(2);
            return Ui.getFoundTaskMessage(tasks.findTask(keyword));

        } else { // if none of the above commands go through
            return isWrongFormat(userInput, nameOfValidCommand);
        }
        return null;
    }
}
