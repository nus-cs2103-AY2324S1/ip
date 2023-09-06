import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Duke {
    private static final String FILE_PATH = "src/main/java/tasks.txt";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>(100);
        loadTasksFromFile(tasks);
        String logo = "██╗░░░██╗██████╗░██████╗░░█████╗░██╗\n"
                + "██║░░░██║██╔══██╗██╔══██╗██╔══██╗██║\n"
                + "██║░░░██║██████╔╝██████╦╝██║░░██║██║\n"
                + "██║░░░██║██╔══██╗██╔══██╗██║░░██║██║\n"
                + "╚██████╔╝██║░░██║██████╦╝╚█████╔╝██║\n"
                + "░╚═════╝░╚═╝░░╚═╝╚═════╝░░╚════╝░╚═╝\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

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
                } else if (command.startsWith("deadline")) {
                    String description = command.substring(9, command.indexOf("/by")).trim();
                    String by = command.substring(command.indexOf("/by") + 4).trim();
                    tasks.add(new Deadline(description, by));
                    System.out.println("Got it. I've added this task:\n  " + tasks.get(tasks.size() - 1));
                } else if (command.startsWith("event")) {
                    String description = command.substring(6, command.indexOf("/from")).trim();
                    String from = command.substring(command.indexOf("/from") + 6, command.indexOf("/to")).trim();
                    String to = command.substring(command.indexOf("/to") + 4).trim();
                    tasks.add(new Event(description, from, to));
                    System.out.println("Got it. I've added this task:\n  " + tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else if (command.startsWith("mark")) {
                    int index = Integer.parseInt(command.split(" ")[1]) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        tasks.get(index).markDone();
                        System.out.println("Nice! I've marked this task as done:\n  " + tasks.get(index));
                    } else {
                        System.out.println("Invalid task index.");
                    }
                } else if (command.startsWith("unmark")) {
                    int index = Integer.parseInt(command.split(" ")[1]) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        tasks.get(index).markNotDone();
                        System.out.println("OK, I've marked this task as not done yet:\n  " + tasks.get(index));
                    } else {
                        System.out.println("Invalid task index.");
                    }
                }  else if (command.startsWith("delete")) {
                    int index = Integer.parseInt(command.split(" ")[1]) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        Task removedTask = tasks.remove(index);
                        System.out.println("Noted. I've removed this task:\n  " + removedTask);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    } else {
                        System.out.println("Invalid task index.");
                    }
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
                saveTasksToFile(tasks);
            }
            catch (DukeException e) {
                System.out.println("☹ OOPS!!! " + e.getMessage());
            }

            System.out.println("____________________________________________________________");
        }


        scanner.close();
    }
    private static void loadTasksFromFile(ArrayList<Task> tasks) {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                Scanner fileScanner = new Scanner(file);
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    Task task = createTaskFromLine(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                }
                fileScanner.close();
            }
        } catch (FileNotFoundException e) {
            // Handle file not found exception
            System.out.println("File not found: " + FILE_PATH);
        }
    }

    private static Task createTaskFromLine(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            return null;
        }

        String type = parts[0];
        String status = parts[1];
        String description = parts[2];

        Task task = null;
        switch (type) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                if (parts.length >= 4) {
                    String by = parts[3];
                    task = new Deadline(description, by);
                }
                break;
            case "E":
                if (parts.length >= 5) {
                    String from = parts[3];
                    String to = parts[4];
                    task = new Event(description, from, to);
                }
                break;
        }

        if (task != null) {
            if (status.equals("1")) {
                task.markDone();
            } else {
                task.markNotDone();
            }
        }

        return task;
    }

    private static void saveTasksToFile(ArrayList<Task> tasks) {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                fileWriter.write(task.toFileString() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            // Handle IO exception
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}

class Task {
    protected String description;
    protected boolean isDone;

    public String toFileString() {
        return "";
    }
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

class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}


class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
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