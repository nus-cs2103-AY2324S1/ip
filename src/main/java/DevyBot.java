import exceptions.*;

import java.util.Scanner;
import java.util.ArrayList;

public class DevyBot {
    private static ArrayList<Task> taskList = new ArrayList<>();
    public enum CommandType {
        TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, LIST, BYE, UNKNOWN
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printMessage("Hello! I'm DevyBot\nWhat can I do for you?");

        boolean run = true;

        while (run) {
            String userInput = scanner.nextLine();
            String[] wordsArray = userInput.split("\\s+");
            CommandType commandType = getCommandType(wordsArray[0]);

            try {
                switch (commandType) {
                    case TODO:
                        addTodoTask(userInput);
                        break;
                    case DEADLINE:
                        addDeadlineTask(userInput);
                        break;
                    case EVENT:
                        addEventTask(userInput);
                        break;
                    case MARK:
                        int markIndex = getIndex(wordsArray);
                        markTaskAsDone(markIndex);
                        break;
                    case UNMARK:
                        int unmarkIndex = getIndex(wordsArray);
                        markTaskAsUndone(unmarkIndex);
                        break;
                    case DELETE:
                        int deleteIndex = getIndex(wordsArray);
                        deleteTask(deleteIndex);
                        break;
                    case BYE:
                        printMessage("Bye. Hope to see you again soon!");
                        run = false;
                        break;
                    case LIST:
                        listTasks();
                        break;
                    default:
                        throw new UnknownCommandException();
                }
            } catch (DevyBotException e) {
                printMessage(e.getMessage());
            }
        }
        scanner.close();
    }

    public static void listTasks() {
        if (taskList.size() == 0) {
            printMessage("Currently no tasks available.");
            return;
        }
        String outpString = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            outpString += (i + 1) + ". " + taskList.get(i).toString() + "\n";
        }
        printMessage(outpString);
    }

    public static CommandType getCommandType(String command) {
        try {
            return CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            return CommandType.UNKNOWN;
        }
    }

    public static void printMessage(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }

    public static void addTodoTask(String userInput) throws EmptyDescriptionException {
        String description = userInput.substring(4).trim();
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("todo");
        }

        Task newTask = new TodoTask(description);
        taskList.add(newTask);

        String outpString = "Got it. I've added this task:\n  " + newTask + "\nNow you have " + taskList.size()
                + " tasks in the list.";
        printMessage(outpString);
    }

    public static void addDeadlineTask(String userInput) throws EmptyDescriptionException {
        String[] parts = userInput.split(" /by ");

        String description = parts[0].substring(8).trim();
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("deadline");
        }

        String by = parts[1].trim();

        Task newTask = new DeadlineTask(description, by);
        taskList.add(newTask);

        String outpString = "Got it. I've added this task:\n  " + newTask + "\nNow you have " + taskList.size()
                + " tasks in the list.";
        printMessage(outpString);
    }

    public static void addEventTask(String userInput) throws EmptyDescriptionException {
        String[] parts = userInput.split(" /from | /to ");

        String description = parts[0].substring(5).trim();
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("event");
        }

        String from = parts[1].trim();
        String to = parts[2].trim();

        Task newTask = new EventTask(description, from, to);
        taskList.add(newTask);

        String outpString = "Got it. I've added this task:\n  " + newTask + "\nNow you have " + taskList.size()
                + " tasks in the list.";
        printMessage(outpString);
    }

    public static void markTaskAsDone(int index) throws TaskIndexOutOfBoundsException {

        if (index >= taskList.size() || index < 0) {
            throw new TaskIndexOutOfBoundsException(index);
        }

        Task currentTask = taskList.get(index);
        currentTask.markTask();

        String outpString = "Nice! I've marked this task as done:\n  " + currentTask;
        printMessage(outpString);
    }

    public static void markTaskAsUndone(int index) throws TaskIndexOutOfBoundsException {

        if (index >= taskList.size() || index < 0) {
            throw new TaskIndexOutOfBoundsException(index);
        }

        Task currentTask = taskList.get(index);
        currentTask.unmarkTask();

        String outpString = "OK, I've marked this task as not done yet:\n  " + currentTask;
        printMessage(outpString);
    }

    public static void deleteTask(int index) throws TaskIndexOutOfBoundsException {

        if (index >= taskList.size() || index < 0) {
            throw new TaskIndexOutOfBoundsException(index);
        }

        Task currentTask = taskList.get(index);
        taskList.remove(index);

        String outpString = "Noted. I've removed this task:\n  " + currentTask;
        printMessage(outpString);
    }

    public static int getIndex(String[] wordsArray) throws EmptyDescriptionException, NonIntegerInputException{
        try {
            if (wordsArray.length <= 1) {
                throw new EmptyDescriptionException(wordsArray[0].toString());
            }
            int index = Integer.parseInt(wordsArray[1]) - 1;
            return index;
        } catch (NumberFormatException e) {
            throw new NonIntegerInputException();
        }
    }
}
