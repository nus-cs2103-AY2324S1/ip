import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String botName = "Aaronbot";
        List<Task> tasks = new ArrayList<>();

        System.out.println("Hello! I'm " + botName);
        System.out.println("What can I do for you?");

        while (true) {
            String userInput = scanner.nextLine();
            String[] inputParts = userInput.split(" ");
            String command = inputParts[0];

            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
            } else if (command.equals("mark") || command.equals("unmark")) {
                int taskIndex = Integer.parseInt(inputParts[1]) - 1;
                if (taskIndex < tasks.size()) {
                    Task task = tasks.get(taskIndex);
                    if (command.equals("mark")) {
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
            } else {
                if (command.equals("todo")) {
                    tasks.add(new Todo(userInput.substring(5)));
                } else if (command.equals("deadline")) {
                    String[] deadlineParts = userInput.split(" /by ");
                    String description = deadlineParts[0].substring(9);
                    String by = deadlineParts[1];
                    tasks.add(new Deadline(description, by));
                } else if (command.equals("event")) {
                    String[] eventParts = userInput.split(" /from | /to ");
                    String description = eventParts[0].substring(6);
                    String from = eventParts[1];
                    String to = eventParts[2];
                    tasks.add(new Event(description, from, to));
                } else {
                    System.out.println("Invalid command.");
                }

                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks.get(tasks.size() - 1));
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            }
        }

        scanner.close();
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
    private String by;

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
    private String from;
    private String to;

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
