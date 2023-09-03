import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static class EmptyDescriptionException extends Exception {
        EmptyDescriptionException(String message) {
            super(message);
        }
    }

    private static class UnknownCommandException extends Exception {
        UnknownCommandException(String message) {
            super(message);
        }
    }

    private static abstract class Task {
        String description;
        boolean isDone;

        Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        void markAsDone() {
            this.isDone = true;
        }

        void unmark() {
            this.isDone = false;
        }

        public abstract String toString();
    }

    private static class Todo extends Task {
        Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T][" + (isDone ? "X" : " ") + "] " + description;
        }
    }

    private static class Deadline extends Task {
        String by;

        Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D][" + (isDone ? "X" : " ") + "] " + description + " (by: " + by + ")";
        }
    }

    private static class Event extends Task {
        String from;
        String to;

        Event(String description, String from, String to) {
            super(description);
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "[E][" + (isDone ? "X" : " ") + "] " + description + " (from: " + from + " to: " + to + ")";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        String line = "____________________________________________________________";

        System.out.println(line);
        System.out.println("Hello! I'm DaDaYuan");
        System.out.println("What can I do for you?");
        System.out.println(line);

        while (true) {
            String input = scanner.nextLine();

            System.out.println(line);

            try {
                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println(line);
                    break;
                } else if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + "." + tasks.get(i));
                    }
                } else if (input.startsWith("todo")) {
                    String description = input.length() > 5 ? input.substring(5) : "";
                    if (description.isEmpty()) {
                        throw new EmptyDescriptionException("OOPS!!! The description of a todo cannot be empty.");
                    } else {
                        tasks.add(new Todo(description));
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    }
                } else if (input.startsWith("deadline")) {
                    String[] parts = input.split(" /by ", 2);
                    if (parts.length < 2) {
                        throw new EmptyDescriptionException("OOPS!!! The deadline of a task cannot be empty.");
                    } else {
                        String description = parts[0].substring(9);
                        tasks.add(new Deadline(description, parts[1]));
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    }
                } else if (input.startsWith("event")) {
                    String[] parts = input.split(" /from | /to ", 3);
                    if (parts.length < 3) {
                        throw new EmptyDescriptionException("OOPS!!! The event timing details are incomplete.");
                    } else {
                        tasks.add(new Event(parts[0].substring(6), parts[1], parts[2]));
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    }
                } else if (input.startsWith("delete")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        System.out.println("Noted. I've removed this task:");
                        System.out.println("   " + tasks.get(index));
                        tasks.remove(index);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    } else {
                        System.out.println("OOPS!!! No task found at the given index.");
                    }
                } else {
                    throw new UnknownCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (EmptyDescriptionException | UnknownCommandException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("OOPS!!! The index provided is not valid.");
            }

            System.out.println(line);
        }

        scanner.close();
    }
}
