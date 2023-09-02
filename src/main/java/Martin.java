import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.IOException;
import exceptions.*;

public class Martin {
    private static ArrayList<Task> tasks = new ArrayList<>();
    public static void main(String[] args) throws InvalidCommandException, EmptyTaskDescriptionException {
        loadFromFile();

        Scanner scanner = new Scanner(System.in);

        printMessage("Hello! I'm Martin\n     What can I do for you?");

        while (true) {
            String input = scanner.nextLine();
            try {
                if (input.equalsIgnoreCase("bye")) {
                    printMessage("Bye. Hope to see you again soon!");
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    printTasks();
                } else if (input.startsWith("delete")) {
                    deleteTask(input);
                    saveToFile();
                } else if (input.startsWith("mark")) {
                    markTask(input);
                    saveToFile();
                } else if (input.startsWith("unmark")) {
                    unmarkTask(input);
                    saveToFile();
                } else if (input.startsWith("todo")) {
                    addTodo(input);
                    saveToFile();
                } else if (input.startsWith("deadline")) {
                    addDeadline(input);
                    saveToFile();
                } else if (input.startsWith("event")) {
                    addEvent(input);
                    saveToFile();
                } else {
                    throw new InvalidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (Exception e) {
                printMessage(e.getMessage());
            }
        }
    }

    private static void printMessage(String message) {
            System.out.println("    ____________________________________________________________");
            System.out.println("     " + message);
            System.out.println("    ____________________________________________________________");
    }

    private static void printTasks() {
        System.out.println("    ____________________________________________________________");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + ". " + tasks.get(i));
        }
        System.out.println("    ____________________________________________________________");
    }

    private static void deleteTask(String command) throws InvalidTaskNumberException {
        try {
            int taskNo = Integer.parseInt(command.split(" ")[1]);
            
            if (taskNo <= 0 || taskNo > tasks.size()) {
                throw new InvalidTaskNumberException("Invalid task number.");
            }
            
            Task removedTask = tasks.remove(taskNo - 1);
            printMessage("Noted. I've removed this task:\n       " + removedTask + "\n     Now you have " + tasks.size() + " tasks in the list.");
        } catch (Exception e) {
            throw new InvalidTaskNumberException("Invalid task number.");
        }
    }

    private static void markTask(String command) throws InvalidTaskNumberException, TaskAlreadyDoneException {
        try {
            int taskNo = Integer.parseInt(command.split(" ")[1]);
            if (taskNo <= 0 || taskNo > tasks.size()) {
                throw new InvalidTaskNumberException("Invalid task number.");
            }
            
            Task task = tasks.get(taskNo - 1);
            if (task.isDone()) {
                throw new TaskAlreadyDoneException("Task \"" + task.getDescription() + "\" is already done.");
            }

            task.markAsDone();
            printMessage("Nice! I've marked this task as done:\n       " + task);
        } catch (Exception e) {
            printMessage(e.getMessage());
        }
    }

    private static void unmarkTask(String command) throws InvalidTaskNumberException, TaskNotDoneException{
        try {
            int taskNo = Integer.parseInt(command.split(" ")[1]);
            if (taskNo <= 0 || taskNo > tasks.size()) {
                throw new InvalidTaskNumberException("Invalid task number.");
            }

            Task task = tasks.get(taskNo - 1);
            if (!task.isDone()) {
                throw new TaskNotDoneException("Task \"" + task.getDescription() + "\" is not done yet.");
            }

            task.unmarkAsDone();
            printMessage("OK, I've marked this task as not done yet:\n       " + task);
        } catch (Exception e) {
            printMessage(e.getMessage());
        }
    }
    
    private static void addTodo(String command) throws EmptyTaskDescriptionException {
        if (command.length() <= 4) {
            throw new EmptyTaskDescriptionException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        
        String description = command.substring(5);
        if (description.isEmpty()) {
            throw new EmptyTaskDescriptionException("☹ OOPS!!! The description of a todo cannot be empty.");
        }

        tasks.add(new Todo(description));
        printMessage("Got it. I've added this task:\n       " + tasks.get(tasks.size() - 1) + "\n     Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void addDeadline(String command) throws EmptyTaskDescriptionException {
        String[] parts = command.substring(9).split(" /by ");
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new EmptyTaskDescriptionException("☹ OOPS!!! The description of a deadline or its date cannot be empty.");
        }

        tasks.add(new Deadline(parts[0], parts[1]));
        printMessage("Got it. I've added this task:\n       " + tasks.get(tasks.size() - 1) + "\n     Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void addEvent(String command) throws EmptyTaskDescriptionException {
        String[] parts = command.substring(6).split(" /from ");
        String[] timeParts = parts[1].split(" /to ");
        if (parts.length < 2 || parts[0].trim().isEmpty() || timeParts.length < 2 || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
            throw new EmptyTaskDescriptionException("☹ OOPS!!! The description of an event or its time cannot be empty.");
        }

        tasks.add(new Event(parts[0], timeParts[0], timeParts[1]));
        printMessage("Got it. I've added this task:\n       " + tasks.get(tasks.size() - 1) + "\n     Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void saveToFile() {
        List<String> lines = new ArrayList<>();
        for (Task task : tasks) {
            lines.add(task.toFileFormat()); 
        }
        
        Path path = Paths.get("./data/martin.txt");
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            }
            Files.write(path, lines, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            printMessage("Error saving tasks to file.");
        }
    }
    
    public static void loadFromFile() {
        Path path = Paths.get("./data/martin.txt");
        if (Files.exists(path)) {
            try {
                List<String> lines = Files.readAllLines(path);
                for (String line : lines) {
                    try {
                        tasks.add(Task.fromFileFormat(line));
                    } catch (IllegalArgumentException e) {
                        printMessage("Data file might be corrupted. (i.e., content not in expected format.)");
                    }
                }
            } catch (IOException e) {
                printMessage("Error reading tasks from file.");
            }
        }
    }    
}
