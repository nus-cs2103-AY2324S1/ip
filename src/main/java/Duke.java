import java.text.NumberFormat;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        String welcome = " ──────────────────────────────────────── \n"
                + "  Hello! I'm Handsome \n"
                + "  What can I do for you? \n"
                + " ──────────────────────────────────────── ";
        String goodbye = " ──────────────────────────────────────── \n"
                + "  Bye. Hope to see you again soon! \n"
                + " ──────────────────────────────────────── \n";
        System.out.println(welcome);
        while (true) {
            String command = input.nextLine();
            if (command.equals("bye")) {
                break;
            }
            if (command.equals("list")) {
                int index = 1;
                System.out.println(" ──────────────────────────────────────── \n"
                        + "Here are the tasks in your list:");
                for (Task task : tasks) {
                    System.out.println(String.format("  %d. %s ", index, task.toString()));
                    index++;
                }
                System.out.println(" ──────────────────────────────────────── ");
            } else if (command.startsWith("mark")) {
                try {
                    String[] substrings = command.split(" ", 2);
                    int taskId = Integer.parseInt(substrings[1]) - 1;
                    if (taskId >= 0 && taskId < tasks.size()) {
                        tasks.get(taskId).markAsDone();
                    } else {
                        String message = tasks.size() > 0
                                ? "No such task! Please enter a task ID between 1 and " + tasks.size()
                                : "You have no tasks! Please add some tasks first";
                        System.out.println(" ──────────────────────────────────────── \n"
                                + message
                                + "\n ──────────────────────────────────────── ");
                    }
                } catch (NumberFormatException e) {
                    System.out.println(" ──────────────────────────────────────── \n"
                            + "Invalid command! Please enter only one valid task ID\n"
                            + " ──────────────────────────────────────── ");
                }
            } else if (command.startsWith("unmark")) {
                try {
                    String[] substrings = command.split(" ", 2);
                    int taskId = Integer.parseInt(substrings[1]) - 1;
                    if (taskId >= 0 && taskId < tasks.size()) {
                        tasks.get(taskId).markAsUndone();
                    } else {
                        String message = tasks.size() > 0
                                ? "No such task! Please enter a task ID between 1 and " + tasks.size()
                                : "You have no tasks! Please add some tasks first";
                        System.out.println(" ──────────────────────────────────────── \n"
                                + message
                                + "\n ──────────────────────────────────────── ");
                    }
                } catch (NumberFormatException e) {
                    System.out.println(" ──────────────────────────────────────── \n"
                            + "Invalid command! Please enter only one valid task ID\n"
                            + " ──────────────────────────────────────── ");
                }
            } else {
                tasks.add(new Task(command));
                String echo = " ──────────────────────────────────────── \n"
                        + "  added: " + command + " \n"
                        + " ──────────────────────────────────────── ";
                System.out.println(echo);
            }
        }
        System.out.println(goodbye);
    }
}

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println(" ──────────────────────────────────────── \n"
                + "  Nice! I've marked this task as done:\n"
                + "     " + this
                + "\n ────────────────────────────────────────");
    }

    public void markAsUndone() {
        this.isDone = false;
        System.out.println(" ──────────────────────────────────────── \n"
                + "  OK, I've marked this task as not done yet:\n"
                + "     " + this
                + "\n ────────────────────────────────────────");
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
