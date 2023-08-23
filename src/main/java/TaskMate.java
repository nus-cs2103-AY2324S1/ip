import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskMate {

    static String horizontalLine = "--------------------";
    static String chatbotName = "TaskMate";
    static String MARK_COMMAND_NAME = "mark";
    static String UNMARK_COMMAND_NAME = "unmark";


    public static void main(String[] args) {

        // Greets user
        String greetMessage = "Hello I'm " + chatbotName + "\nWhat can I do for you?";
        printReply(greetMessage);

        // Echo user input
        Scanner sc = new Scanner(System.in);
        String userInput;
        while (true) {
            userInput = sc.nextLine();

            // Exits
            if (userInput.equals("bye")) {
                break;

            // list
            } else if (userInput.equals("list")) {
                String allTasksString = "Here are the tasks in your list:\n";
                for (int i = 0; i < Task.getAllTasks().size(); i++) {
                    Task newTask = Task.getAllTasks().get(i);
                    boolean isDone = newTask.getDone();
                    char isDoneString = isDone ? 'X' : ' ';
                    allTasksString += Integer.toString(i+1) + ".[" + isDoneString + "] " + newTask + "\n";
                }
                printReply(allTasksString);


            // Add task OR mark/unmark
            } else {

                // Mark/unmark task
                if (checkIsMarkOrUnmarkCommand(userInput)) {
                    if (userInput.startsWith("mark")) {
                        int indexToMark = Integer.parseInt(userInput.substring(MARK_COMMAND_NAME.length()).trim());
                        indexToMark -= 1;
                        Task taskToMark = Task.getAllTasks().get(indexToMark);
                        taskToMark.markAsDone();

                        // print message when marking as done
                        String message = "[X] " + taskToMark;
                        message = "Nice! I've marked this task as done:\n" + message;
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
                } else {
                    // Add task
                    Task newTask = new Task(userInput);
                    printReply("added: " + userInput);
                }
            }
        }


        // Farewell user
        String exitMessage = "Bye. Hope to see you again soon!";
        printReply(exitMessage);
    }

    static void printReply(String text) {
        System.out.println(horizontalLine);
        System.out.println(text);
        System.out.println(horizontalLine);
        System.out.println();
    }

    static boolean checkIsMarkOrUnmarkCommand(String userInput) {
        // Checks if the user input command is a "mark" or "unmark" command
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
        try {
            Integer i = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    static void getCommandType(String userInput) {

    }
}