import exceptions.BobCorruptFileException;
import exceptions.BobException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Bob {
    private static final String divider = "\n____________________________________________________________\n";
    private static final String logo =
            ".-. .-')              .-. .-')   \n" +
            "\\  ( OO )             \\  ( OO )  \n" +
            " ;-----.\\  .-'),-----. ;-----.\\  \n" +
            " | .-.  | ( OO'  .-.  '| .-.  |  \n" +
            " | '-' /_)/   |  | |  || '-' /_) \n" +
            " | .-. `. \\_) |  |\\|  || .-. `.  \n" +
            " | |  \\  |  \\ |  | |  || |  \\  | \n" +
            " | '--'  /   `'  '-'  '| '--'  / \n" +
            " `------'      `-----' `------'  ";
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static boolean isActive = true;
    private static final Scanner scanner = new Scanner(System.in);
    private static String taskDirectoryRelativePath = "data/";
    private static String taskFileName = "Bob.txt";
    public enum Commands {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, INVALID
    }

    public static void main(String[] args) {
        Bob.printWelcomeMessage();
        try {
            Bob.tasks = loadTasks();
            processResponse();
        } catch (BobException e) {
            System.out.println(e.getMessage() + divider);
        }
    }

    private static void printWelcomeMessage() {
        String welcomeMessage = "Hi, I'm Bob. How can I help you?";
        System.out.println(divider + logo + "\n" + welcomeMessage + divider);
    }

    private static void printGoodbyeMessage() {
        String goodbyeMessage = "Goodbye! Bob signing out!";
        System.out.println(goodbyeMessage);
    }

    private static void printListMessage() {
        if (tasks.size() == 1) {
            System.out.printf("\nNow you have %d task in your list!%n", tasks.size());
        } else {
            System.out.printf("\nNow you have %d tasks in your list!%n", tasks.size());
        }
    }

    private static Commands parseCommand(String keyword) {
        try {
            return Commands.valueOf(keyword);
        } catch (IllegalArgumentException e) {
            return Commands.INVALID;
        }
    }

    private static Task parseStoredTask(String inputString) throws BobException {
        String[] split = inputString.split("\\|");
        String commandType = split[0];

        try {
            switch (commandType) {
                case "T":
                    return new Todo(split[2], Integer.parseInt(split[1]) == 1);
                case "D":
                    return new Deadline(split[2], split[3], Integer.parseInt(split[1]) == 1);
                case "E":
                    return new Event(split[2], split[3], split[4], Integer.parseInt(split[1]) == 1);
            }
            return null;
        } catch (Exception e) {
            throw new BobCorruptFileException("Your save file might be corrupted :(");
        }
    }

    private static ArrayList<Task> loadTasks() throws BobException {
        File dataDirectory = new File(Bob.taskDirectoryRelativePath);
        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }

        try {
            File taskFile = new File(Bob.taskDirectoryRelativePath + Bob.taskFileName);
            Scanner fileScanner = new Scanner(taskFile);
            ArrayList<Task> loadedTasks = new ArrayList<>();
            while (fileScanner.hasNextLine()) {
                Task task = parseStoredTask(fileScanner.nextLine());
                loadedTasks.add(task);
            }
            fileScanner.close();
            return loadedTasks;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
    }

    private static void saveTasks() throws BobException {
        File dataDirectory = new File(Bob.taskDirectoryRelativePath);
        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }
        try {
            FileWriter fileWriter = new FileWriter(Bob.taskDirectoryRelativePath + Bob.taskFileName);
            for (Task task: tasks) {
                fileWriter.write(task.convertToFileFormat() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new BobException("Something went wrong when saving your tasks :(");
        }
    }

    private static void processResponse() throws BobException {
        String displayMessage = "I gotchu. New task added to the list:\n";
        String input = Bob.scanner.nextLine();
        String[] inputSplit = input.split(" ", 2);
        String command = inputSplit[0].toUpperCase();
        String argument = "";

        if (inputSplit.length > 1) {
            argument = inputSplit[1];
        }
        try {
            switch (parseCommand(command)) {
                case BYE:
                    Bob.saveTasks();
                    Bob.printGoodbyeMessage();
                    scanner.close();
                    Bob.isActive = false;
                    break;
                case LIST:
                    if (tasks.isEmpty()) {
                        System.out.println("You don't have any tasks. Good job!");
                        break;
                    }
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i).toString());
                    }
                    break;
                case MARK:
                    if (argument.isBlank()) {
                        System.out.println("Tell me which task to mark as done! Give me an integer number!");
                        break;
                    }
                    try {
                        int markIndex = Integer.parseInt(argument);
                        tasks.get(markIndex - 1).markAsDone();
                        System.out.println("Great Job! I've helped mark this task as done:\n" +
                                tasks.get(markIndex - 1).toString());
                    } catch (NumberFormatException e) {
                        System.out.println("The mark command must be followed by an integer number.");
                    } catch (NullPointerException | IndexOutOfBoundsException e) {
                        System.out.println("You are trying to mark a non-existent task, ensure you mark a task that you have created :O");
                    }
                    break;
                case UNMARK:
                    if (argument.isBlank()) {
                        System.out.println("Tell me which task to mark as done! Give me an integer number!");
                        break;
                    }
                    try {
                        int unmarkIndex = Integer.parseInt(argument);
                        tasks.get(unmarkIndex - 1).unmarkTask();
                        System.out.println("No worries! I will help you unmark this task:\n" +
                                tasks.get(unmarkIndex - 1).toString());
                    } catch (NumberFormatException e) {
                        System.out.println("The mark command must be followed by an integer number.");
                    } catch (NullPointerException | IndexOutOfBoundsException e) {
                        System.out.println("You are trying to unmark a non-existent task, ensure you mark a task that you have created :O");
                    }
                    break;
                case TODO:
                    if (argument.isBlank()) {
                        System.out.println("The description of your todo should not be empty! Try:\ntodo [description]");
                        break;
                    }
                    Bob.tasks.add(new Todo(argument));
                    System.out.println(displayMessage + Bob.tasks.get(tasks.size() - 1).toString());
                    printListMessage();
                    break;
                case DEADLINE:
                    if (argument.isBlank()) {
                        System.out.println("The description of your deadline should not be empty! Try:\ndeadline [description] /by [duedate]");
                        break;
                    }
                    String[] bySplit = argument.split(" /by ", 2);
                    if (bySplit.length != 2) {
                        System.out.println("Incorrect deadline command format! It should be:\ndeadline [description] /by [duedate]");
                        break;
                    }
                    Bob.tasks.add(new Deadline(bySplit[0], bySplit[1]));
                    System.out.println(displayMessage + Bob.tasks.get(tasks.size() - 1).toString());
                    printListMessage();
                    break;
                case EVENT:
                    if (argument.isBlank()) {
                        System.out.println("The description of your event should not be empty! Try:\nevent [description] /from [start] /to [end]");
                        break;
                    }
                    String[] descSplit = argument.split(" /from ", 2);
                    if (descSplit.length != 2) {
                        System.out.println("Incorrect event command format! It should be:\nevent [description] /from [start] /to [end]");
                        break;
                    }
                    String[] periodSplit = descSplit[1].split(" /to ", 2);
                    if (periodSplit.length != 2) {
                        System.out.println("Incorrect event command format! It should be:\nevent [description] /from [start] /to [end]");
                        break;
                    }
                    Bob.tasks.add(new Event(descSplit[0], periodSplit[0], periodSplit[1]));
                    System.out.println(displayMessage + Bob.tasks.get(tasks.size() - 1).toString());
                    printListMessage();
                    break;
                case DELETE:
                    if (argument.isBlank()) {
                        System.out.println("Tell me which task to delete! Give me an integer number!");
                        break;
                    }
                    try {
                        int delIndex = Integer.parseInt(argument);
                        Task deletedTask = tasks.remove(delIndex - 1);
                        System.out.println("Foosh! Let it be gone! I've helped delete the task:\n" +
                                deletedTask.toString());
                        printListMessage();
                    } catch (NumberFormatException e) {
                        System.out.println("The delete command must be followed by an integer number.");
                    } catch (NullPointerException | IndexOutOfBoundsException e) {
                        System.out.println("You are trying to delete a non-existent task, ensure you delete a task that you have created :3");
                    }
                    break;
                case INVALID:
                    System.out.println("I'm sorry! I don't understand the command :(");
                    break;
            }

            System.out.println(divider);

            if (Bob.isActive) {
                processResponse();
            }

        } catch (Exception e) {
            throw new BobException("An unknown error has occurred. I'll shut myself off for now.");
        }
    }

}
