import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = "██╗░░░██╗██████╗░██████╗░░█████╗░██╗\n"
                    + "██║░░░██║██╔══██╗██╔══██╗██╔══██╗██║\n"
                    + "██║░░░██║██████╔╝██████╦╝██║░░██║██║\n"
                    + "██║░░░██║██╔══██╗██╔══██╗██║░░██║██║\n"
                    + "╚██████╔╝██║░░██║██████╦╝╚█████╔╝██║\n"
                    + "░╚═════╝░╚═╝░░╚═╝╚═════╝░░╚════╝░╚═╝\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        ArrayList<Task> tasks = new ArrayList<>(100);

        while (true) {
            String command = scanner.nextLine();
            System.out.println("____________________________________________________________");

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
            } else {
                tasks.add(new Task(command));
                System.out.println("added: " + command);
            }

            System.out.println("____________________________________________________________");
        }

        scanner.close();
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
    protected String by;

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
    protected String from;
    protected String to;

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
