import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static final String FILE_PATH = "./data/duke.txt";

    private static ArrayList<Task> tasks = new ArrayList<>();

    private static DateTimeFormatter inputDateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    private static DateTimeFormatter outputDateTimeFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");

    private static LocalDateTime parseInputDateTime(String dateTimeString){
        return LocalDateTime.parse(dateTimeString, inputDateTimeFormatter);
    }

    private static LocalDateTime parseOutputDateTime(String dateTimeString){
        return LocalDateTime.parse(dateTimeString, outputDateTimeFormatter);
    }


    private static void saveTasks() {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);

            for (Task task : tasks) {
                writer.write(task.toSaveString() + "\n");
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks.");
            e.printStackTrace();
        }
    }

    private static void loadTasks() {
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            System.out.println("No save file detected. Attempting to create one...");
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();  // This creates the directory structure if it doesn't exist
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("An error occurred while creating a new save file.");
                e.printStackTrace();
            }
            System.out.println("Save file created successfully at " + FILE_PATH);
        }

        List<String> lines;

        try {
            lines = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks.");
            return;
        }

        for (String line : lines) {
            String[] parts = line.split(" \\| ");
            boolean isMarked = parts[1].equals("1");

            switch (parts[0]) {
            case "T":
                tasks.add(new Todo(parts[2], isMarked));
                break;
            case "D":
                tasks.add(new Deadline(parts[2], isMarked, parseOutputDateTime(parts[3])));
                break;
            case "E":
                tasks.add(new Event(parts[2], isMarked, parseOutputDateTime(parts[3]), parseOutputDateTime(parts[4])));
                break;
            }
        }
    }

    private static void display(String... text) {
        System.out.println("____________________________________________________________");
        for (String i : text)
            System.out.println(i);
        System.out.println("____________________________________________________________");
    }

    private static void raiseMissingValueError(String value, String command) {
        Duke.display(String.format("OOPS!!! The %s of %s cannot be empty.", value, command));
    }

    private static void greet() {
        Duke.display("Hello! I'm Tackie", "What can I do for you?");
    }

    private static void farewell() {
        Duke.display("Bye. Hope to see you again soon!");
    }

    private static void list(){
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Duke.tasks.size(); i++) {
            System.out.println((i + 1) + "." + Duke.tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    private static void mark(int taskIndex){
        Task targetTask = Duke.tasks.get(taskIndex);
        targetTask.markStatus();
        Duke.display("Nice! I've marked this task as done:", targetTask.toString());
    }

    private static void unmark(int taskIndex){
        Task targetTask = Duke.tasks.get(taskIndex);
        targetTask.unmarkStatus();
        Duke.display("OK, I've marked this task as not done yet:", targetTask.toString());
    }

    private static void printAddTaskInfo(Task task){
        Duke.display("Got it. I've added this task:", task.toString(),
                "Now you have " + Duke.tasks.size() + " tasks in the list.");
    }


    private static void addTodo(String description){
        Todo newTask = new Todo(description);
        Duke.tasks.add(newTask);
        Duke.printAddTaskInfo(newTask);
    }

    private static void addDeadline(String description, LocalDateTime by){
        Deadline newTask = new Deadline(description, by);
        Duke.tasks.add(newTask);
        Duke.printAddTaskInfo(newTask);
    }

    private static void addEvent(String description, LocalDateTime from, LocalDateTime to){
        Event newTask = new Event(description, from, to);
        Duke.tasks.add(newTask);
        Duke.printAddTaskInfo(newTask);
    }

    public static void main(String[] args) {
        Duke.loadTasks();
        Duke.greet();

        String[] inputParts;
        int taskIndex;
        String description;
        String byString;
        LocalDateTime by;
        String fromString;
        LocalDateTime from;
        String toString;
        LocalDateTime to;
        Task deletedTask;

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            inputParts = userInput.split(" ", 2);
            switch (inputParts[0]) {
            case "list":
                Duke.list();
                break;

            case "mark":
                if (inputParts.length == 1) {
                    raiseMissingValueError("index", "mark");
                    break;
                }
                taskIndex = Integer.parseInt(inputParts[1]) - 1;
                Duke.mark(taskIndex);
                break;

            case "unmark":
                if (inputParts.length == 1) {
                    raiseMissingValueError("index", "unmark");
                    break;
                }
                taskIndex = Integer.parseInt(inputParts[1]) - 1;
                Duke.unmark(taskIndex);
                break;

            case "delete":
                if (inputParts.length == 1) {
                    raiseMissingValueError("index", "delete");
                    break;
                }
                taskIndex = Integer.parseInt(inputParts[1]) - 1;
                if (taskIndex < 0 || taskIndex >= Duke.tasks.size()) {
                    Duke.display("OOPS!!! The task index is invalid.");
                } else {
                    deletedTask = Duke.tasks.remove(taskIndex);
                    Duke.display("Noted. I've removed this task:", deletedTask.toString(),
                            "Now you have " + Duke.tasks.size() + " tasks in the list.");
                }
                break;

            case "todo":
                if (inputParts.length == 1) {
                    raiseMissingValueError("description", "todo");
                    break;
                }
                description = inputParts[1];
                Duke.addTodo(description);
                break;

            case "deadline":
                if (inputParts.length == 1) {
                    raiseMissingValueError("description", "deadline");
                    break;
                }
                inputParts = inputParts[1].split(" /by ", 2);
                description = inputParts[0];
                if (inputParts.length == 1) {
                    raiseMissingValueError("by", "deadline");
                    break;
                }
                by = parseInputDateTime(inputParts[1]);
                Duke.addDeadline(description, by);
                break;

            case "event":
                if (inputParts.length == 1) {
                    raiseMissingValueError("description", "event");
                    break;
                }
                inputParts = inputParts[1].split(" /from ", 2);
                description = inputParts[0];
                if (inputParts.length == 1) {
                    raiseMissingValueError("from", "event");
                    break;
                }
                inputParts = inputParts[1].split(" /to ", 2);
                from = parseInputDateTime(inputParts[0]);
                if (inputParts.length == 1) {
                    raiseMissingValueError("to", "event");
                    break;
                }
                to = parseInputDateTime(inputParts[1]);
                Duke.addEvent(description, from, to);
                break;

            default:
                Duke.display("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            saveTasks();
            userInput = scanner.nextLine();
        }
        Duke.farewell();
    }
}
