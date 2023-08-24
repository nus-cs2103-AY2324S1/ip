import java.util.ArrayList;
import java.util.Scanner;

public class Pogo {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String QUIT_MESSAGE = "Bye. Hope to see you again soon!";

    private static int parseTaskIndex(String input) {
        return Integer.parseInt(input.split(" ")[1]) - 1;
    }

    private static void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i).getStatusMessage());
        }
    }

    private static Task addTask(String input) throws PogoException {
        Task task;
        if (input.startsWith("todo")) {
            int length = "todo".length();
            if (input.length() == length) {
                throw new PogoEmptyTaskException();
            }

            String description = input.substring("todo".length() + 1);

            task = new ToDo (description);
        } else if (input.startsWith("deadline")) {
            int length = "deadline".length();
            if (input.length() == length) {
                throw new PogoEmptyTaskException();
            }

            String[] split = input.substring(length+1).split(" /by ");
            String description = split[0];
            String by = split[1];

            task = new Deadline(description, by);
        } else if (input.startsWith("event")) {
            int length = "event".length();
            if (input.length() == length) {
                throw new PogoEmptyTaskException();
            }

            String[] split = input.substring(length+1).split(" /from ");
            String description = split[0];
            String[] temp = split[1].split(" /to ");
            String from = temp[0];
            String to = temp[1];

            task = new Event(description, from, to);
        } else {
            throw new PogoInvalidTaskException();
        }
        tasks.add(task);
        return task;
    }

    private static void printNumberOfTasks() {
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static boolean handleInput(String input) {
        if (input.equals("bye")) {
            System.out.println(QUIT_MESSAGE);
            return true;
        } else if (input.equals("list")) {
            Pogo.printTasks();
        } else if (input.startsWith("mark")) {
            int index = Pogo.parseTaskIndex(input);
            Task task = tasks.get(index);

            if (task.isDone()) {
                System.out.println("This task is already done!");
            } else {
                tasks.get(index).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
            }

            System.out.println(tasks.get(index).getStatusMessage());
        } else if (input.startsWith("unmark")) {
            int index = Pogo.parseTaskIndex(input);
            Task task = tasks.get(index);

            if (task.isDone()) {
                task.markAsUndone();
                System.out.println("Ok, I've marked this task as not done yet:");
            } else {
                System.out.println("This task is already not done!");
            }

            System.out.println(tasks.get(index).getStatusMessage());
        } else if (input.startsWith("delete")) {
            int index = Pogo.parseTaskIndex(input);
            Task task = tasks.remove(index);

            System.out.println("Noted. I've removed this task:");
            System.out.println(task.getStatusMessage());
            Pogo.printNumberOfTasks();
        } else {
            try {
                Task task = Pogo.addTask(input);
                System.out.println("added: " + task.getStatusMessage());
                Pogo.printNumberOfTasks();
            } catch (PogoInvalidTaskException e) {
                System.out.println("Oops! I don't recognise that task.\n"
                        + "Only the following tasks are supported:\n"
                        + " - todo <description>\n"
                        + " - deadline <description> /by <date>\n"
                        + " - event <description> /from <date> /to <date>"
                );
            } catch (PogoEmptyTaskException e) {
                System.out.println("Oops! The description of a task cannot be empty.");
            } catch (PogoException e) {
                System.out.println("Oops! An unknown error has occurred.");
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Pogo\nWhat can I do for you?");
        System.out.println(HORIZONTAL_LINE);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            System.out.println(HORIZONTAL_LINE);

            boolean quit = Pogo.handleInput(input);
            if (quit) {
                break;
            } else {
                System.out.println(HORIZONTAL_LINE);
            }
        }
    }
}
