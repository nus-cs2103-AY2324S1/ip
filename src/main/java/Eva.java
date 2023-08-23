import java.util.ArrayList;
import java.util.Scanner;
public class Eva {
    public static void main(String[] args) {
        String logo = "  ______          \n"
                + " |  ____|         \n"
                + " | |____   ____ _ \n"
                + " |  __\\ \\ / / _` |\n"
                + " | |___\\ V / (_| |\n"
                + " |______\\_/ \\__,_|\n";
        System.out.println("Hello from\n" + logo);
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Hello! I'm Eva.");
        System.out.println("\t What can I do for you?");
        System.out.println("\t____________________________________________________________");

        ArrayList<Task> tasks = new ArrayList<>(); // Use ArrayList to store tasks
        int taskCount = 0; // Counter for tasks

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("\t____________________________________________________________");
                System.out.println("\t Bye. Hope to see you again soon!");
                System.out.println("\t____________________________________________________________");
                break;
            } else if (input.equals("list")) {
                System.out.println("\t____________________________________________________________");
                if (taskCount == 0) {
                    System.out.println("\t No tasks added yet.");
                } else {
                    System.out.println("\t Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println("\t " + (i + 1) + "." + tasks.get(i));
                    }
                }
                System.out.println("\t____________________________________________________________");
            } else if (input.startsWith("mark")) {
                int taskIndex = Integer.parseInt(input.substring(5)) - 1;
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    tasks.get(taskIndex).markDone();
                    System.out.println("\t____________________________________________________________");
                    System.out.println("\t Nice! I've marked this task as done:");
                    System.out.println("\t   " + tasks.get(taskIndex));
                    System.out.println("\t____________________________________________________________");
                } else {
                    System.out.println("\t Task not found.");
                }
            } else if (input.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(input.substring(7)) - 1;
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    tasks.get(taskIndex).markUndone();
                    System.out.println("\t____________________________________________________________");
                    System.out.println("\t OK, I've marked this task as not done yet:");
                    System.out.println("\t   " + tasks.get(taskIndex));
                    System.out.println("\t____________________________________________________________");
                } else {
                    System.out.println("\t Task not found.");
                }
            } else if (input.startsWith("todo")) {
                try {
                    if (input.length() <= 5) {
                        throw new DukeException("\t ☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    tasks.add(new Todo(input.substring(5)));
                    taskCount++;
                    System.out.println("\t____________________________________________________________");
                    System.out.println("\t Got it. I've added this task: ");
                    System.out.println("\t\t" + tasks.get(taskCount - 1));
                    System.out.println("\t Now you have " + taskCount + " task(s) in the list.");
                    System.out.println("\t____________________________________________________________");
                } catch (DukeException e) {
                    System.out.println("\t____________________________________________________________");
                    System.out.println("\t" + e.getMessage());
                    System.out.println("\t____________________________________________________________");
                }
            } else if (input.startsWith("deadline")) {
                // Parse the description and by date
                try{
                    int byIndex = input.indexOf("/by");
                    if (byIndex == -1) {
                        throw new DukeException("\t ☹ OOPS!!! The deadline description must include a /by date.");
                    }
                    String description = input.substring(9, byIndex).trim();
                    if (description.isEmpty()) {
                        throw new DukeException("☹ OOPS!!! The deadline description cannot be empty.");
                    }
                    String by = input.substring(byIndex + 3).trim();
                    tasks.add(new Deadline(description, by));
                    taskCount++;
                    System.out.println("\t____________________________________________________________");
                    System.out.println("\t Got it. I've added this task: ");
                    System.out.println("\t\t" + tasks.get(taskCount-1));
                    System.out.println("\t Now you have " + taskCount + " task(s) in the list.");
                    System.out.println("\t____________________________________________________________");
                } catch (DukeException e) {
                    System.out.println("\t____________________________________________________________");
                    System.out.println("\t" + e.getMessage());
                    System.out.println("\t____________________________________________________________");
                }
            } else if (input.startsWith("event")) {
                // Parse the description and from-to dates
                try {
                    int fromIndex = input.indexOf("/from");
                    int toIndex = input.indexOf("/to");
                    if (fromIndex == -1 && toIndex == -1) {
                        throw new DukeException("\t ☹ OOPS!!! The event description must include both /from and /to dates.");
                    }
                    String description = input.substring(6, fromIndex).trim();
                    if (description.isEmpty()) {
                        throw new DukeException("☹ OOPS!!! The event description cannot be empty.");
                    }
                    String from = input.substring(fromIndex + 5, toIndex).trim();
                    String to = input.substring(toIndex + 3).trim();
                    tasks.add(new Event(description, from, to));
                    taskCount++;
                    System.out.println("\t____________________________________________________________");
                    System.out.println("\t Got it. I've added this task: ");
                    System.out.println("\t\t" + tasks.get(taskCount-1));
                    System.out.println("\t Now you have " + taskCount + " task(s) in the list.");
                    System.out.println("\t____________________________________________________________");
                } catch (DukeException e) {
                    System.out.println("\t____________________________________________________________");
                    System.out.println("\t" + e.getMessage());
                    System.out.println("\t____________________________________________________________");
                }
            } else if (input.startsWith("delete")) {
                try {
                    int taskIndex = Integer.parseInt(input.substring(7)) - 1;
                    if (taskIndex >= 0 && taskIndex < tasks.size()) {
                        Task removedTask = tasks.remove(taskIndex);
                        System.out.println("\t____________________________________________________________");
                        System.out.println("\t Noted. I've removed this task:");
                        System.out.println("\t   " + removedTask);
                        System.out.println("\t Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println("\t____________________________________________________________");
                        taskCount--;
                    } else {
                        throw new DukeException("☹ OOPS!!! Task not found.");
                    }
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    System.out.println("\t____________________________________________________________");
                    System.out.println("\t ☹ OOPS!!! Please enter a valid task index to delete.");
                    System.out.println("\t____________________________________________________________");
                } catch (DukeException e) {
                    System.out.println("\t____________________________________________________________");
                    System.out.println("\t" + e.getMessage());
                    System.out.println("\t____________________________________________________________");
                }
            } else {
                System.out.println("\t____________________________________________________________");
                System.out.println("\t ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println("\t____________________________________________________________");
            }
        }
        scanner.close();
    }

    static class DukeException extends Exception {
        public DukeException(String message) {
            super(message);
        }
    }

    static class Task {
        private String description;
        private boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public void markDone() {
            isDone = true;
        }

        public void markUndone() {
            isDone = false;
        }

        @Override
        public String toString() {
            return "[" + (isDone ? "X" : " ") + "] " + description;
        }
    }

    static class Todo extends Task {
        public Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    static class Deadline extends Task {
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

    static class Event extends Task {
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
}