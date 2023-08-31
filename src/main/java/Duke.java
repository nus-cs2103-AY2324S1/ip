import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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

class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String botName = "Aaronbot";
        String savedString = "";
        try {
            byte[] encodedBytes = Files.readAllBytes(Paths.get("data/duke.txt"));
            String fileContent = new String(encodedBytes);
            savedString = fileContent;
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        TaskList tasks = new TaskList(savedString);

        System.out.println("Hello! I'm " + botName);
        System.out.println("What can I do for you?");

        while (true) {
            String userInput = scanner.nextLine();

            try {
                processUserInput(userInput, tasks);
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
                tasks.saveToFile();
                break;
            case DELETE:
                int index = Integer.parseInt(inputParts[1]) - 1;
                Task task = tasks.get(index);
                tasks.remove(index);
                tasks.saveToFile();
                System.out.println("Noted. I've removed this task: \n" + task + "\nNow you have " + tasks.size() + " tasks in the list.");
                break;
            case TODO:
            case DEADLINE:
            case EVENT:
                handleTaskCreation(userInput, tasks, commandType);
                tasks.saveToFile();
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
                throw new WrongFormatException("OOPS!!! deadlines need to be in this format, deadline return book /by Sunday");
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

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }
}

class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Deadline extends Task {
    public String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

class Event extends Task {
    public String from;
    public String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}


class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(String input) {
        this.tasks = new ArrayList<>();

        String[] lines = input.split("\n");
        for (String line : lines) {
            System.out.print(line);
            Task task = createTaskFromInput(line);
            if (task != null) {
                tasks.add(task);
            }
        }
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void remove(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        } else {
            System.out.println("Invalid index.");
        }
    }

    public Task get(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        } else {
            System.out.println("Invalid index.");
            return null;
        }
    }

    public int size() {
        return tasks.size();
    }
    private Task createTaskFromInput(String input) {
        char type = input.charAt(1);
        boolean isDone = (input.charAt(4) == 'X');
    
        int openParenIndex = input.indexOf('('); // Find the index of the open parenthesis
    
        String description;
    
        if (openParenIndex != -1) {
            // If there is an open parenthesis, extract description till the open parenthesis
            description = input.substring(7, openParenIndex).trim();  // Trim whitespace
        } else {
            // If there's no parenthesis, use the entire string after the type marker
            description = input.substring(7).trim();  // Trim whitespace
        }
    
        Task task;
    
        if (type == 'T') {
            task = new Todo(description);
        } else if (type == 'D') {
            int byIndex = input.indexOf("(by: ");
            String by = input.substring(byIndex + 5, input.length() - 1).trim();  // Trim whitespace
            task = new Deadline(description, by);
        } else if (type == 'E') {
            int fromIndex = input.indexOf("(from: ");
            int toIndex = input.indexOf("to: ");
            String from = input.substring(fromIndex + 7, toIndex).trim();  // Trim whitespace
            String to = input.substring(toIndex + 4, input.length() - 1).trim();  // Trim whitespace
            task = new Event(description, from, to);
        } else {
            return null;
        }
    
        if (isDone) {
            task.markAsDone();
        }
    
        return task;
    }

    public void saveToFile() {
        try {
            String content = this.toString(); // Get the string representation of tasks
            Files.write(Paths.get("data/duke.txt"), content.getBytes());
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : tasks) {
            stringBuilder.append(task.toString());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}    

