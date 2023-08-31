import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Jarvis {
    private static TaskList tasks;
    private static Storage storage;
    private static ArrayList<String> validCommands = new ArrayList<>(); // list of valid commands

    private static void addValidCommands() {
        validCommands.add("list");
        validCommands.add("bye");
        validCommands.add("mark");
        validCommands.add("uncheck");
        validCommands.add("todo");
        validCommands.add("deadline");
        validCommands.add("event");
    }

    private static final String name = "Jarvis";
    private static final String line = "____________________________________________________________";
    private static final String greeting = "Good day Sir! I'm ";
    private static final String question = "How can I help you today Sir?";
    private static final String signOff = "Good bye Sir!";
    private static final String listInforming = "Here are the task in your list Sir:";
    private static final String markInforming = "Roger that Sir, I've marked this task as done:";
    private static final String uncheckInforming = "Noted Sir, I've marked this task as NOT done yet:";
    private static final String taskInforming = "As you please Sir, I've added the task:";
    private static final String deleteInforming = "Alright Sir, I've removed this task";

    private static void printGreeting() {
        System.out.println(line);
        System.out.println(greeting + name + "!");
        System.out.println(question);
        System.out.println(line);
    }

    private static void printBye() {
        System.out.println(line);
        System.out.println(signOff);
        System.out.println(line);
    }

    private static void printMark(Task currentTask) {
        System.out.println(line);
        System.out.println(markInforming);
        System.out.println(currentTask.toString());
        System.out.println(line);
    }

    private static void printUncheck(Task currentTask) {
        System.out.println(line);
        System.out.println(uncheckInforming);
        System.out.println(currentTask.toString());
        System.out.println(line);
    }

    // identifies which command has wrong formatting and prints feedback to user
    private static void printIncorrectFormat(String inputCommand, String validInputCommand) {
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
                System.out.println(line);
                System.out.println(e.getMessage());
                System.out.println("    The following is the correct format:");
                System.out.println("        mark <task number>");
                System.out.println(line);
                return;
            }
        } else if (validInputCommand.equals("uncheck") && !uncheckMatch) { // if uncheck command but wrong format
            try {
                throw new WrongJarvisCommandFormatException("Apologies Sir, the format of the uncheck command you provided is incorrect.");
            } catch (WrongJarvisCommandFormatException e) {
                System.out.println(line);
                System.out.println(e.getMessage());
                System.out.println("    The following is the correct format:");
                System.out.println("        uncheck <task number>");
                System.out.println(line);
                return;
            }
        } else if (validInputCommand.equals("todo") && !todoMatch) { // if todo command but wrong format
            try {
                throw new WrongJarvisCommandFormatException("Apologies Sir, the format of the todo command you provided is incorrect.");
            } catch (WrongJarvisCommandFormatException e) {
                System.out.println(line);
                System.out.println(e.getMessage());
                System.out.println("    The following is the correct format:");
                System.out.println("        todo <description>");
                System.out.println(line);
                return;
            }
        } else if (validInputCommand.equals("deadline") && !deadlineMatch) { // if deadline command but wrong format
            try {
                throw new WrongJarvisCommandFormatException("Apologies Sir, the format of the deadline command you provided is incorrect.");
            } catch (WrongJarvisCommandFormatException e) {
                System.out.println(line);
                System.out.println(e.getMessage());
                System.out.println("    The following is the correct format:");
                System.out.println("        deadline <description> /by <date or time>");
                System.out.println(line);
                return;
            }
        } else if (validInputCommand.equals("event") && !eventMatch) { // if event command but wrong format
            try {
                throw new WrongJarvisCommandFormatException("Apologies Sir, the format of the event command you provided is incorrect.");
            } catch (WrongJarvisCommandFormatException e) {
                System.out.println(line);
                System.out.println(e.getMessage());
                System.out.println("    The following is the correct format:");
                System.out.println("        event <description> /from <date or time> /to <date or time>");
                System.out.println(line);
                return;
            }
        }

    }

    // checks if command is valid and throws IncorrectJarvisCommandException
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
                System.out.println(line);
                System.out.println(e.getMessage());
                System.out.println("    This is the list of valid commands:");
                for (int i = 0; i < validCommands.size(); i++) {
                    int numbering = i + 1;
                    System.out.println("    " + numbering + ". " + validCommands.get(i));
                }
                System.out.println(line);
            }
        }
        return validInputCommand;
    }

    public static void main(String[] args) {

        addValidCommands(); // adding the list of valid commands to the validCommands arraylist
        printGreeting();

        // getting the file path to the save file
        String home = System.getProperty("user.home");
        Path pathToSaveFile = Paths.get(home, "Desktop", "CS2103T", "IP", "data", "data.txt");
        boolean isFileExists = Files.exists(pathToSaveFile);
        storage = new Storage(pathToSaveFile.toString());
        tasks = new TaskList(storage.load());

        Pattern todoPattern = Pattern.compile("(todo) (.+)");
        Pattern deadlinePattern = Pattern.compile("(deadline) (.+) /by (.+)");
        Pattern eventPattern = Pattern.compile("(event) (.+) /from (.+) /to (.+)");
        Pattern deletePattern = Pattern.compile("(delete) (\\d+)");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine().trim();
            String nameOfValidCommand = isValidCommand(command);

            Matcher todoMatcher = todoPattern.matcher(command);
            Matcher deadlineMatcher = deadlinePattern.matcher(command);
            Matcher eventMatcher = eventPattern.matcher(command);
            Matcher deleteMatcher = deletePattern.matcher(command);

            if (command.matches("list")) { // if "list" is entered
                tasks.printTaskList();

            } else if (command.matches("bye")) { // if "bye" is entered
                printBye();
                break;

            } else if (command.matches("mark \\d+") ||
                    command.matches("uncheck \\d+")) { // if "mark" or "uncheck" is entered

                int taskNum = Integer.parseInt(command.substring(command.length() - 1));
                if (!tasks.isValidTaskNumber(taskNum)) {continue;}

                Task currentTask = tasks.getTask(taskNum - 1);
                if (command.matches("uncheck \\d+")) { // if "uncheck" is entered
                    currentTask.setDone(false);
                    printUncheck(currentTask);

                } else { // if "mark" is entered
                    currentTask.setDone(true);
                    printMark(currentTask);
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
                    newTask.stringToDate();

                } else { // if "event" is entered
                    String taskDescription = eventMatcher.group(2);
                    String from = eventMatcher.group(3);
                    String to = eventMatcher.group(4);
                    newTask = new Event(taskDescription, from, to);

                }
                tasks.appendTask(newTask);
                tasks.printTask(newTask);

            } else if (deleteMatcher.matches()) { // if delete is entered
                int taskNum = Integer.parseInt(deleteMatcher.group(2));
                if (tasks.isValidTaskNumber(taskNum)) {
                    Task deletedTask = tasks.getTask(taskNum - 1);
                    tasks.deleteTask(taskNum - 1);
                    tasks.printDelete(deletedTask);
                }
            }

            else { // if none of the above commands go through
                printIncorrectFormat(command, nameOfValidCommand);
            }

            storage.deleteContents();
            storage.save(tasks); // save task list to data file after every iteration
        }
        scanner.close(); // closing the user input scanner
    }
}