package Jarvis;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static ArrayList<String> validCommands; // list of valid commands

    public Parser() {
        validCommands = new ArrayList<>();
        addValidCommands();
    }

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
                throw new IncorrectJarvisCommandException("Apologies Sir, the command " + inputCommand + " is not a valid command.");
            } catch (IncorrectJarvisCommandException e) {
                Ui.printListOfCommands(validCommands, inputCommand, e);
            }
        }
        return validInputCommand;
    }

    // identifies which command has wrong formatting and prints feedback to user
    public static void isWrongFormat(String inputCommand, String validInputCommand) {
        if (validInputCommand.equals("")) { // if input is empty
            return;
        }

        // since command is valid, check if formatting of the command is correct
        boolean markMatch = inputCommand.matches("mark \\d+");
        boolean uncheckMatch = inputCommand.matches("unmark \\d+");
        boolean todoMatch = inputCommand.matches("todo .+");
        boolean deadlineMatch = inputCommand.matches("deadline .+ /.+");
        boolean eventMatch = inputCommand.matches("event .+ /.+ /.+");

        if (validInputCommand.equals("mark") && !markMatch) { // if mark command but wrong format
            try {
                throw new WrongJarvisCommandFormatException("Apologies Sir, the format of the mark command you provided is incorrect.");
            } catch (WrongJarvisCommandFormatException e) {
                Ui.printWrongFormat("mark", e);
            }
        } else if (validInputCommand.equals("uncheck") && !uncheckMatch) { // if uncheck command but wrong format
            try {
                throw new WrongJarvisCommandFormatException("Apologies Sir, the format of the uncheck command you provided is incorrect.");
            } catch (WrongJarvisCommandFormatException e) {
                Ui.printWrongFormat("uncheck", e);
            }
        } else if (validInputCommand.equals("todo") && !todoMatch) { // if todo command but wrong format
            try {
                throw new WrongJarvisCommandFormatException("Apologies Sir, the format of the todo command you provided is incorrect.");
            } catch (WrongJarvisCommandFormatException e) {
                Ui.printWrongFormat("todo", e);
            }
        } else if (validInputCommand.equals("deadline") && !deadlineMatch) { // if deadline command but wrong format
            try {
                throw new WrongJarvisCommandFormatException("Apologies Sir, the format of the deadline command you provided is incorrect.");
            } catch (WrongJarvisCommandFormatException e) {
                Ui.printWrongFormat("deadline", e);
            }
        } else if (validInputCommand.equals("event") && !eventMatch) { // if event command but wrong format
            try {
                throw new WrongJarvisCommandFormatException("Apologies Sir, the format of the event command you provided is incorrect.");
            } catch (WrongJarvisCommandFormatException e) {
                Ui.printWrongFormat("event", e);
            }
        }

    }

    public static void parseCommand(Storage storage, TaskList tasks, String userInput) throws
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
            Ui.printTaskList(tasks);

        } else if (userInput.matches("bye")) { // if "bye" is entered
            Ui.printBye();
            System.exit(0);

        } else if (userInput.matches("mark \\d+") ||
                userInput.matches("uncheck \\d+")) { // if "mark" or "uncheck" is entered

            int taskNum = Integer.parseInt(userInput.substring(userInput.length() - 1));
            if (!tasks.isValidTaskNumber(taskNum)) {return;}

            Task currentTask = tasks.getTask(taskNum - 1);
            if (userInput.matches("uncheck \\d+")) { // if "uncheck" is entered
                currentTask.setDone(false);
                Ui.printUncheck(currentTask);

            } else { // if "mark" is entered
                currentTask.setDone(true);
                Ui.printMark(currentTask);
            }

        } else if (todoMatcher.matches() ||
                deadlineMatcher.matches() ||
                eventMatcher.matches()) { // if task command is entered
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
            Ui.printTask(tasks ,newTask);

        } else if (deleteMatcher.matches()) { // if delete is entered
            int taskNum = Integer.parseInt(deleteMatcher.group(2));
            if (tasks.isValidTaskNumber(taskNum)) {
                Task deletedTask = tasks.getTask(taskNum - 1);
                tasks.deleteTask(taskNum - 1);
                Ui.printDelete(tasks ,deletedTask);
            }

        } else if (findMatcher.matches()) { // if find is entered
            String keyword = findMatcher.group(2);
            Ui.printFoundTask(tasks.findTask(keyword));
        }

        else { // if none of the above commands go through
            isWrongFormat(userInput, nameOfValidCommand);
        }
    }
}
