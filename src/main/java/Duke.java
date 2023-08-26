import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
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

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.getDescription();
    }

    public String toFormattedString() {
        return "";
    }

    public static Task createTaskFromFormattedString(String formattedString) {
        String[] parts = formattedString.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        if (taskType.equals("T")) {
            Task todo =  new ToDo(description);
            todo.isDone = isDone;
            return todo;
        } else if (taskType.equals("D")) {
            String by = parts[3];
            Task deadline =  new Deadline(description, by);
            deadline.isDone = isDone;
            return deadline;
        } else if (taskType.equals("E")) {
            String from = parts[3];
            String to = parts[4];
            Task event = new Event(description, from, to);
            event.isDone = isDone;
            return event;
        } else {
            // Handle unrecognized task type
            return null;
        }
    }
}

class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFormattedString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}

class Deadline extends Task {
    protected LocalDateTime by;
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mmlis")) + ")";
    }

    @Override
    public String toFormattedString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}

class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String from, String to){
        super(description);
        this.from = LocalDateTime.parse(from);
        this.to = LocalDateTime.parse(to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    @Override
    public String toFormattedString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }
}

public class Duke {
    private static final String FILE_PATH = "./src/main/java/duke.txt";
    public static void main(String[] args) {
        String horizontalLine = "________________________________________________________________";
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println(horizontalLine);
        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);

        loadTasksFromFile(tasks);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            System.out.println(horizontalLine);

            try {
                if (userInput.equalsIgnoreCase("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println(horizontalLine);
                    saveTasksToFile(tasks);
                    break;
                } else if (userInput.equalsIgnoreCase("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(" " + (i + 1) + ". " + tasks.get(i));
                    }
                } else if (userInput.startsWith("todo")) {
                    String description = userInput.substring(4).trim();
                    if (description.isEmpty()) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    tasks.add(new ToDo(description));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(" " + tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else if (userInput.startsWith("deadline")) {
                    String input = userInput.substring(8).trim();
                    String[] parts = input.split("/by");
                    if (parts.length < 2) {
                        throw new DukeException("☹ OOPS!!! The format of a deadline should be: deadline DESCRIPTION /by DATE");
                    }
                    String description = parts[0].trim();
                    String by = parts[1].trim();
                    tasks.add(new Deadline(description, by));
                    System.out.println("Got it. Ive added this task:");
                    System.out.println(" " + tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else if (userInput.startsWith("event")) {
                    String input = userInput.substring(5).trim();
                    String[] parts = input.split("/from");
                    if (parts.length < 2) {
                        throw new DukeException("☹ OOPS!!! The format of an event should be: event DESCRIPTION /from DATE /to DATE");
                    }
                    String description = parts[0].trim();
                    String[] dateParts = parts[1].split("/to");
                    if (dateParts.length < 2) {
                        throw new DukeException("☹ OOPS!!! The format of an event should be: event DESCRIPTION /from DATE /to DATE");
                    }
                    String from = dateParts[0].trim();
                    String to = dateParts[1].trim();
                    tasks.add(new Event(description, from, to));
                    System.out.println("Got it. Ive added this task:");
                    System.out.println(" " + tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else if (userInput.startsWith("mark") || userInput.startsWith("unmark")) {
                    int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        if (userInput.startsWith("mark")) {
                            tasks.get(index).markAsDone();
                            System.out.println("Nice! I've marked this task as done:");
                        } else {
                            tasks.get(index).markAsNotDone();
                            System.out.println("OK, I've marked this task as not done yet:");
                        }
                        System.out.println(" " + tasks.get(index));
                    } else {
                        throw new DukeException("☹ OOPS!!! Invalid task number.");
                    }
                } else if (userInput.startsWith("delete")) {
                    String[] parts = userInput.split(" ");
                    if (parts.length < 2) {
                        System.out.println("☹ OOPS!!! Please provide a task number.");
                    } else {
                        try {
                            int index = Integer.parseInt(parts[1]) - 1;

                            if (index >= 0 && index < tasks.size()) {
                                Task removedTask = tasks.remove(index);
                                System.out.println("Noted. I've removed this task:");
                                System.out.println(" " + removedTask);
                                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                            } else {
                                System.out.println("☹ OOPS!!! Invalid task number.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("☹ OOPS!!! Please provide a valid task number.");
                        }
                    }
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("☹ OOPS!!! Please provide a valid task number.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! Please provide a valid task number within the list of tasks.");
            }
            catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }

            System.out.println(horizontalLine);
        }
        scanner.close();
    }

    public static void loadTasksFromFile(ArrayList<Task> tasks) {
        try {
            File file = new File(FILE_PATH);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.isEmpty()) { // Skip empty lines
                    Task task = Task.createTaskFromFormattedString(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                } else {
                    break; // Stop reading after reaching the last line
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Please create the data file in the file path");
        }
    }

    public static void saveTasksToFile(ArrayList<Task> tasks) {
        try {
            File file = new File(FILE_PATH);
            FileWriter writer = new FileWriter(file);
            for (Task task : tasks) {
                writer.write(task.toFormattedString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks to file.");
        }
    }
}
