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
    static enum CommandTypes {
        list, bye, todo, deadline, event, mark, unmark
    }

    public static void main(String[] args) throws InvalidCommandTypeException {

        // Greets user
        String greetMessage = "Hello I'm " + chatbotName + "\nWhat can I do for you?";
        printReply(greetMessage);

        // Echo user input
        Scanner sc = new Scanner(System.in);
        String userInput;
        while (true) {
            userInput = sc.nextLine();

            // Checks if user input is correct (Error handling)
            checkInvalidCommandTypeException(userInput);

            // exits
            if (getCommandType(userInput).equals("bye")) {
                break;
            // list
            } else if (getCommandType(userInput).equals("list")) {
                processListCommand();
            // Add task OR mark/unmark
            } else if (getCommandType(userInput).equals("mark") | getCommandType(userInput).equals("unmark")) {
                try {
                    checkValidMarkOrUnmarkCommand(userInput);
                } catch (InvalidCommandTypeException e) {
                    printReply("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.exit(0);
                } catch (InvalidDescriptionException e) {
                    printReply("☹ OOPS!!! The description of a mark/unmark must be between 1 and " + Task.getAllTasks().size() + ".");
                    System.exit(0);
                }
                processMarkUnmarkCommand(userInput);
            } else if (getCommandType(userInput).equals("todo")) {
                try {
                    checkValidTodoCommand(userInput);
                } catch (InvalidCommandTypeException e) {
                    printReply("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.exit(0);
                } catch (InvalidDescriptionException e) {
                    printReply("☹ OOPS!!! The description of a todo cannot be empty.");
                    System.exit(0);
                }
                processAddTaskCommand(userInput);
            } else if (getCommandType(userInput).equals("deadline")) {
                // TODO: Add checks for Deadline-type tasks
                processAddTaskCommand(userInput);
            } else if (getCommandType(userInput).equals("event")) {
                // TODO: Add checks for Event-type tasks
                processAddTaskCommand(userInput);
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

    static void checkValidMarkOrUnmarkCommand(String userInput) throws InvalidCommandTypeException, InvalidDescriptionException {
        // Checks if the user input command is a valid "mark" or "unmark" command
        // by checking if the command starts with "mark"/"unmark", followed by a whitespace,
        // followed by an integer from 1 to Task.getAllTasks().size()
        String indexWithinList;

        if (userInput.startsWith(CommandTypes.mark.toString())) {
            indexWithinList = userInput.substring(CommandTypes.mark.toString().length()).trim();
        } else {
            if (!userInput.startsWith(CommandTypes.unmark.toString())) {
                throw new InvalidCommandTypeException();
            }
            indexWithinList = userInput.substring(CommandTypes.unmark.toString().length()).trim();
        }

        if (!checkStringIsInteger(indexWithinList)) {
            throw new InvalidDescriptionException();
        } else if (Integer.parseInt(indexWithinList) < 1 | Integer.parseInt(indexWithinList) > Task.getAllTasks().size()) {
            throw new InvalidDescriptionException();
        }
    }

    static void checkValidTodoCommand(String userInput) throws InvalidCommandTypeException, InvalidDescriptionException {
        // Checks if "todo" command is valid by checking if there is text
        // coming after the word "todo"
        if (!userInput.startsWith(CommandTypes.todo.toString())) {
            throw new InvalidCommandTypeException();
        } else if (userInput.substring(CommandTypes.todo.toString().length()).isEmpty()) {
            throw new InvalidDescriptionException();
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

    static String getCommandType(String userInput) throws InvalidCommandTypeException {
        // Returns the type of command input by the user
        // Possible values: "todo", "deadline", "event", "bye", "list", "mark", "unmark"
        for (CommandTypes type : CommandTypes.values()) {
            String typeString = type.toString();
            if (userInput.startsWith(typeString)) {
                return typeString;
            }
        }
        throw new InvalidCommandTypeException();
    }

    static void checkInvalidCommandTypeException(String userInput) {
        try {
            String commandType = getCommandType(userInput);
        } catch (InvalidCommandTypeException e) {
            printReply("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            System.exit(0);
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
            int indexToMark = Integer.parseInt(userInput.substring(CommandTypes.mark.toString().length()).trim());
            indexToMark -= 1;
            Task taskToMark = Task.getAllTasks().get(indexToMark);
            taskToMark.markAsDone();

            // print message when marking as done
            String message = "Nice! I've marked this task as done:\n  " + taskToMark;
            printReply(message);

        } else {
            int indexToUnmark = Integer.parseInt(userInput.substring(CommandTypes.unmark.toString().length()).trim());
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

        printReply("Got it. I've added this task:\n  " + newTask.toString() + "\nNow you have " + Task.getAllTasks().size() + " task(s) in the list.");
        return newTask;
    }
}