import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Jarvis {
    private static ArrayList<Task> taskList = new ArrayList<>(); // list of Tasks
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

    private static void printList() {
        System.out.println(line);
        System.out.println(listInforming);
        for (int i = 0; i < taskList.size(); i++) { // listing out the current task
            int count = i + 1;
            Task currentTask = taskList.get(i);
            System.out.println(count + "." + currentTask.toString());
        }
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

    private static void printTask(Task currentTask) {
        System.out.println(line);
        System.out.println(taskInforming);
        System.out.println(currentTask.toString());
        System.out.println("You have now " + taskList.size() + " tasks in the list Sir.");
        System.out.println(line);
    }

    private static void printDelete(Task currentTask) {
        System.out.println(line);
        System.out.println(deleteInforming);
        System.out.println(currentTask.toString());
        System.out.println("You have now " + taskList.size() + " tasks in the list Sir.");
        System.out.println(line);
    }

    // identifies which command has wrong formatting and prints feedback to user
    private static void printIncorrectFormat(String inputCommand, String validInputCommand) {
        // since command is valid, check if formatting of the command is correct
        boolean markMatch = inputCommand.matches("mark \\d+");
        boolean uncheckMatch = inputCommand.matches("unmark \\d+");
        boolean todoMatch = inputCommand.matches("todo .+");
        boolean deadlineMatch = inputCommand.matches("deadline .+ /.+");
        boolean eventMatch = inputCommand.matches("event .+ /.+ /.+");

        if (validInputCommand.equals("mark") && !markMatch) {
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
        } else if (validInputCommand.equals("uncheck") && !uncheckMatch) {
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
        } else if (validInputCommand.equals("todo") && !todoMatch) {
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
        } else if (validInputCommand.equals("deadline") && !deadlineMatch) {
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
        } else if (validInputCommand.equals("event") && !eventMatch) {
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

    // function check if task number is valid and throws InvalidTaskNumberException
    private static boolean isValidTaskNumber(int taskNum) {
        boolean isValid = true;
        if (taskNum < 0 || taskNum > taskList.size()) { // check if task number is of valid range
            try {
                throw new InvalidTaskNumberException("Apologies Sir, the task number you provided is out of range.");
            } catch (InvalidTaskNumberException e) {
                System.out.println(line);
                System.out.println(e.getMessage());
                System.out.println("    There are currently " + taskList.size() + " tasks in the list.");
                System.out.println(line);
                isValid = false;
            }
        }
        return isValid;
    }

    public static void main(String[] args) {

        addValidCommands(); // adding the list of valid commands to the validCommands arraylist
        printGreeting();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine().trim();
            String nameOfValidCommand = isValidCommand(command);
            if (nameOfValidCommand.equals("")) {continue;}

            // if "list" is entered
            if (command.matches("list")) {
                printList();
                continue;
            }

            // if "bye" is entered
            if (command.matches("bye")) {
                printBye();
                break;
            }

            // if "mark" or "uncheck" is entered
            if (command.matches("mark \\d+") ||
                    command.matches("uncheck \\d+")) {

                int taskNum = Integer.parseInt(command.substring(command.length() - 1));
                if (!isValidTaskNumber(taskNum)) {continue;}

                Task currentTask = taskList.get(taskNum - 1);
                if (command.matches("uncheck \\d+")) { // if "uncheck" is entered
                    currentTask.setDone(false);
                    printUncheck(currentTask);
                    continue;
                } else { // if "mark" is entered
                    currentTask.setDone(true);
                    printMark(currentTask);
                    continue;
                }

            }

            // if task command is entered
            Pattern todoPattern = Pattern.compile("(todo) (.+)");
            Pattern deadlinePattern = Pattern.compile("(deadline) (.+) /by (.+)");
            Pattern eventPattern = Pattern.compile("(event) (.+) /from (.+) /to (.+)");
            Matcher todoMatcher = todoPattern.matcher(command);
            Matcher deadlineMatcher = deadlinePattern.matcher(command);
            Matcher eventMatcher = eventPattern.matcher(command);
            if (todoMatcher.matches() || deadlineMatcher.matches() || eventMatcher.matches()) {
                if (todoMatcher.matches()) { // if "todo" is entered
                    String taskDescription = todoMatcher.group(2);
                    ToDo todo = new ToDo(taskDescription);
                    taskList.add(todo);
                    printTask(todo);
                    continue;
                } else if (deadlineMatcher.matches()) { // if "deadline" is entered
                    String taskDescription = deadlineMatcher.group(2);
                    String by = deadlineMatcher.group(3);
                    Deadline deadline = new Deadline(taskDescription, by);
                    taskList.add(deadline);
                    printTask(deadline);
                    continue;
                } else { // if "event" is entered
                    String taskDescription = eventMatcher.group(2);
                    String from = eventMatcher.group(3);
                    String to = eventMatcher.group(4);
                    Event event = new Event(taskDescription, from, to);
                    taskList.add(event);
                    printTask(event);
                    continue;
                }
            }

            // if delete is entered
            Pattern deletePattern = Pattern.compile("(delete) (\\d+)");
            Matcher deleteMatcher = deletePattern.matcher(command);
            if (deleteMatcher.matches()) {
                int taskNum = Integer.parseInt(deleteMatcher.group(2));
                if (isValidTaskNumber(taskNum)) {
                    Task deletedTask = taskList.get(taskNum - 1);
                    taskList.remove(taskNum - 1);
                    printDelete(deletedTask);
                }
                continue;
            }

            // if none of the above commands go through
            printIncorrectFormat(command, nameOfValidCommand);
        }
        scanner.close(); // closing the user input scanner
    }
}