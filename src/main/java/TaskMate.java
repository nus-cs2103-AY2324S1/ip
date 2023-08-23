import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskMate {

    static String horizontalLine = "--------------------";
    static String chatbotName = "TaskMate";
    static String MARK_COMMAND_NAME = "mark ";
    static String UNMARK_COMMAND_NAME = "unmark ";
    static String TODO_COMMAND_NAME = "todo ";
    static String DEADLINE_COMMAND_NAME = "deadline ";
    static String EVENT_COMMAND_NAME = "event ";

    public static void main(String[] args) {

        // Greets user
        String greetMessage = "Hello I'm " + chatbotName + "\nWhat can I do for you?";
        printReply(greetMessage);

        // Echo user input
        Scanner sc = new Scanner(System.in);
        String userInput;
        while (true) {
            userInput = sc.nextLine();
            // exits
            if (userInput.equals("bye")) {
                break;
            // list
            } else if (userInput.equals("list")) {
                processListCommand();
            // Add task OR mark/unmark
            } else {
                // Mark/unmark task
                if (checkIsMarkOrUnmarkCommand(userInput)) {
                    processMarkUnmarkCommand(userInput);
                } else {
                    // Add task
                    processAddTaskCommand(userInput);
                }
            }
        }


        // print exit message
        String exitMessage = "Bye. Hope to see you again soon!";
        printReply(exitMessage);
    }

    static void printReply(String text) {
        // prints text with horizontal lines above and below it
        System.out.println(horizontalLine);
        System.out.println(text);
        System.out.println(horizontalLine);
        System.out.println();
    }

    static boolean checkIsMarkOrUnmarkCommand(String userInput) {
        // Checks if the user input command is a "mark" or "unmark" command
        // by checking if the command starts with "mark"/"unmark", followed by a whitespace, followed by an integer
        String indexWithinList;
        if (userInput.startsWith(MARK_COMMAND_NAME)) {
            indexWithinList = userInput.substring(MARK_COMMAND_NAME.length()).trim();
            return checkStringIsInteger(indexWithinList);
        } else if (userInput.startsWith(UNMARK_COMMAND_NAME)) {
            indexWithinList = userInput.substring(UNMARK_COMMAND_NAME.length()).trim();
            return checkStringIsInteger(indexWithinList);
        } else {
            return false;
        }
    }

    static boolean checkStringIsInteger(String s) {
        // Returns true if s can be parsed into an Integer object, and false otherwise
        try {
            Integer i = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    static String getCommandType(String userInput) {
        // Returns the type of command input by the user
        // Possible values: "bye", "list", "mark/unmark", "add task"
        if (userInput.equals("list") | userInput.equals("bye")) {
            return userInput;
        } else if (checkIsMarkOrUnmarkCommand(userInput)) {
            return "mark/unmark";
        } else {
            return "add task";
        }
    }

    static void processListCommand() {
        // EDIT
        String allTasksString = "Here are the tasks in your list:\n";
        for (int i = 0; i < Task.getAllTasks().size(); i++) {
            Task newTask = Task.getAllTasks().get(i);
            allTasksString += Integer.toString(i+1) + "." + newTask.toString() + "\n";
        }
        printReply(allTasksString);
    }

    static void processMarkUnmarkCommand(String userInput) {
        if (userInput.startsWith("mark")) {
            int indexToMark = Integer.parseInt(userInput.substring(MARK_COMMAND_NAME.length()).trim());
            indexToMark -= 1;
            Task taskToMark = Task.getAllTasks().get(indexToMark);
            taskToMark.markAsDone();

            // print message when marking as done
            String message = "Nice! I've marked this task as done:\n" + taskToMark;
            printReply(message);

        } else {
            int indexToUnmark = Integer.parseInt(userInput.substring(UNMARK_COMMAND_NAME.length()).trim());
            indexToUnmark -= 1;
            Task taskToUnmark = Task.getAllTasks().get(indexToUnmark);
            taskToUnmark.markAsNotDone();

            // print message when unmarking as done
            String message = "[ ] " + taskToUnmark;
            message = "OK, I've marked this task as not done yet:\n" + message;
            printReply(message);
        }
    }

    static Task processAddTaskCommand(String userInput) {
        Task newTask;
        if (userInput.startsWith("todo ")) {
            newTask = new Todo(userInput.substring(TODO_COMMAND_NAME.length()));
        } else if (userInput.startsWith("deadline ")) {
            userInput = userInput.substring(DEADLINE_COMMAND_NAME.length());
            String[] splitUserInput = userInput.split(" /");
            newTask = new Deadline(
                    splitUserInput[0],
                    splitUserInput[1].replace("by ", "")
            );
        } else {
            userInput = userInput.substring(EVENT_COMMAND_NAME.length());
            String[] splitUserInput = userInput.split(" /");
            newTask = new Event(
                    splitUserInput[0],
                    splitUserInput[1].replace("from ", ""),
                    splitUserInput[2].replace("to ", "")
            );
        }

        printReply("Got it. I've added this task:\n" + newTask.toString() + "\nNow you have " + Task.getAllTasks().size() + " task(s) in the list.");
        return newTask;
    }
}