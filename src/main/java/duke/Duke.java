package dukey;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import dukey.TaskList;
import dukey.Storage;
import dukey.Todo;
import dukey.Deadline;
import dukey.Event;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String botName = "Aaaronbot";
        String savedString = "";
        Storage storage = new Storage("data/duke.txt");
        savedString = storage.load();
        TaskList tasks = new TaskList(savedString);
        System.out.println("Hello! I'm " + botName);
        System.out.println("What can I do for you?");

        while (true) {
            String userInput = scanner.nextLine();

            try {
                processUserInput(userInput, tasks);
                storage.save(tasks);
            } catch (EmptyDescriptionException | UnknownCommandException | WrongFormatException e) {
                System.out.println(e.getMessage());
            }

            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
        }

        scanner.close();
    }

    public static void processUserInput(String userInput, TaskList tasks)
            throws EmptyDescriptionException, UnknownCommandException, WrongFormatException {
        String[] inputParts = userInput.split(" ");
        String commandStr = inputParts[0];
        CommandType commandType = getCommandType(commandStr);

        switch (commandType) {
            case LIST:
                listTasks(tasks);
                break;
            case MARK:
            case UNMARK:
                int taskIndex = Integer.parseInt(inputParts[1]) - 1;
                if (taskIndex >= 0 && taskIndex < tasks.size()) {
                    Task task = tasks.get(taskIndex);
                    if (commandType == CommandType.MARK) {
                        task.markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                    } else {
                        task.markAsNotDone();
                        System.out.println("OK, I've marked this task as not done yet:");
                    }
                    System.out.println("  " + task);
                } else {
                    System.out.println("Invalid task index.");
                }
                break;
            case DELETE:
                int index = Integer.parseInt(inputParts[1]) - 1;
                Task task = tasks.get(index);
                tasks.remove(index);
                System.out.println("Noted. I've removed this task: \n" + task + "\nNow you have " + tasks.size() + " tasks in the list.");
                break;
            case TODO:
            case DEADLINE:
            case EVENT:
                handleTaskCreation(userInput, tasks, commandType);
                break;
            default:
                throw new UnknownCommandException();
        }
    }

    public static CommandType getCommandType(String commandStr) {
        try {
            return CommandType.valueOf(commandStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            return CommandType.UNKNOWN;
        }
    }

    public static void listTasks(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public static void handleTaskCreation(String userInput, TaskList tasks, CommandType commandType)
            throws EmptyDescriptionException, UnknownCommandException, WrongFormatException {
        String[] inputParts = userInput.split(" ");
        String command = inputParts[0];

        if (commandType == CommandType.TODO) {
            if (inputParts.length <= 1) {
                throw new EmptyDescriptionException(command);
            }
            tasks.add(new Todo(userInput.substring(5)));
        } else if (commandType == CommandType.DEADLINE) {
            if (inputParts.length <= 1){
                throw new EmptyDescriptionException(command);
            }
            try {
                String[] deadlineParts = userInput.split(" /by ");
                String description = deadlineParts[0].substring(9);
                String by = deadlineParts[1];
                tasks.add(new Deadline(description, by));
            } catch (Exception e) {
                throw new WrongFormatException("OOPS!!! deadlines need to be in this format, deadline return book /by YYYY-MM-DD");
            }
        } else if (commandType == CommandType.EVENT) {
            if (inputParts.length <= 1){
                throw new EmptyDescriptionException(command);
            }
            try {
                String[] eventParts = userInput.split(" /from | /to ");
                String description = eventParts[0].substring(6);
                String from = eventParts[1];
                String to = eventParts[2];
                tasks.add(new Event(description, from, to));
            } catch (Exception e) {
                throw new WrongFormatException("OOPS!!! events need to be in this format, event project meeting /from Mon 2pm /to 4pm");
            }
        } else {
            throw new UnknownCommandException();
        }
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
}

class EmptyDescriptionException extends Exception {
    public EmptyDescriptionException(String task) {
        super("OOPS!!! The description of a " + task + " cannot be empty.");
    }
}

class WrongFormatException extends Exception {
    public WrongFormatException(String message) {
        super(message);
    }
}

class UnknownCommandException extends Exception {
    public UnknownCommandException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-P");
    }
}

enum CommandType {
    LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, UNKNOWN
}

