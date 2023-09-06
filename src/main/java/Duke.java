import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Duke {
    private static final String FILE_PATH = "src/main/java/task.txt";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = loadTasksFromFile();
        String logo = "██╗░░░██╗██████╗░██████╗░░█████╗░██╗\n"
                    + "██║░░░██║██╔══██╗██╔══██╗██╔══██╗██║\n"
                    + "██║░░░██║██████╔╝██████╦╝██║░░██║██║\n"
                    + "██║░░░██║██╔══██╗██╔══██╗██║░░██║██║\n"
                    + "╚██████╔╝██║░░██║██████╦╝╚█████╔╝██║\n"
                    + "░╚═════╝░╚═╝░░╚═╝╚═════╝░░╚════╝░╚═╝\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        //ArrayList<Task> tasks = new ArrayList<>(100);

        while (true) {
            String command = scanner.nextLine();
            System.out.println("____________________________________________________________");
            try{
                if (command.equalsIgnoreCase("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (command.equalsIgnoreCase("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                } else if (command.startsWith("todo")) {
                    String description = command.substring(5).trim();
                    if (description.isEmpty()) {
                        throw new DukeException("The description of a todo cannot be empty.");
                    }
                    tasks.add(new Todo(description));
                    System.out.println("Got it. I've added this task:\n  " + tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    saveTasksToFile(tasks);
                } else if (command.startsWith("deadline")) {
                    String description = command.substring(9, command.indexOf("/by")).trim();
                    String by = command.substring(command.indexOf("/by") + 4).trim();
                    tasks.add(new Deadline(description, by));
                    System.out.println("Got it. I've added this task:\n  " + tasks.get(tasks.size() - 1));
                    saveTasksToFile(tasks);
                } else if (command.startsWith("event")) {
                    String description = command.substring(6, command.indexOf("/from")).trim();
                    String from = command.substring(command.indexOf("/from") + 6, command.indexOf("/to")).trim();
                    String to = command.substring(command.indexOf("/to") + 4).trim();
                    tasks.add(new Event(description, from, to));
                    System.out.println("Got it. I've added this task:\n  " + tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    saveTasksToFile(tasks);
                } else if (command.startsWith("mark")) {
                    int index = Integer.parseInt(command.split(" ")[1]) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        tasks.get(index).markDone();
                        System.out.println("Nice! I've marked this task as done:\n  " + tasks.get(index));
                    } else {
                        System.out.println("Invalid task index.");
                    }
                    saveTasksToFile(tasks);
                } else if (command.startsWith("unmark")) {
                    int index = Integer.parseInt(command.split(" ")[1]) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        tasks.get(index).markNotDone();
                        System.out.println("OK, I've marked this task as not done yet:\n  " + tasks.get(index));
                    } else {
                        System.out.println("Invalid task index.");
                    }
                    saveTasksToFile(tasks);
                }  else if (command.startsWith("delete")) {
                    int index = Integer.parseInt(command.split(" ")[1]) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        Task removedTask = tasks.remove(index);
                        System.out.println("Noted. I've removed this task:\n  " + removedTask);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    } else {
                        System.out.println("Invalid task index.");
                    }
                    saveTasksToFile(tasks);
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }

            }
            catch (DukeException e) {
                System.out.println("☹ OOPS!!! " + e.getMessage());
            }

            System.out.println("____________________________________________________________");
        }


        scanner.close();
    }
    private static void saveTasksToFile(ArrayList<Task> tasks) {
        try (FileOutputStream fos = new FileOutputStream(FILE_PATH);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(tasks);
            System.out.println("Tasks saved to file: " + FILE_PATH);
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    private static ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();

        File file = new File(FILE_PATH);

        if (!file.exists()) {
            try {
                file.createNewFile(); // Create a blank file if it doesn't exist
                System.out.println("Created new data file: " + FILE_PATH);
            } catch (IOException e) {
                System.out.println("Error creating data file: " + e.getMessage());
            }
        }

        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(FILE_PATH);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {

                tasks = (ArrayList<Task>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading tasks from file: " + e.getMessage());
            }
        }

        return tasks;
    }
}

class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // Return a tick or cross symbol cuz im lazy like that, or its easier. idk
    }
    public boolean isDone() {
        return isDone;
    }
    public void markDone() {
        isDone = true;
    }
    public void markNotDone() {
        isDone = false;
    }
    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }
}

class Todo extends Task implements Serializable{
    public Todo() {
        super("");
    }
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Deadline extends Task implements Serializable{
    protected String by;
    public Deadline() {
        super("");
    }
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}


class Event extends Task implements Serializable{
    protected String from;
    protected String to;
    public Event() {
        super("");
    }
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

class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}