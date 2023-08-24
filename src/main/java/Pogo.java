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
            String description = input.substring("todo".length() + 1);
            task = new ToDo (description);
        } else if (input.startsWith("deadline")) {
            String[] split = input.substring("deadline".length() + 1).split(" /by ");
            String description = split[0];
            String by = split[1];
            task = new Deadline(description, by);
        } else if (input.startsWith("event")) {
            String[] split = input.substring("event".length() + 1).split(" /from ");
            String description = split[0];
            String[] temp = split[1].split(" /to ");
            String from = temp[0];
            String to = temp[1];
            task = new Event(description, from, to);
        } else {
            task = new Task(input);
        }
        tasks.add(task);
        return task;
    }

    private static boolean handleInput(String input) {
        if (input.equals("bye")) {
            System.out.println(QUIT_MESSAGE);
            return true;
        } else if (input.equals("list")) {
            Pogo.printTasks();
        } else if (input.startsWith("mark")) {
            int index = Pogo.parseTaskIndex(input);
            tasks.get(index).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(index).getStatusMessage());
        } else if (input.startsWith("unmark")) {
            int index = Pogo.parseTaskIndex(input);
            tasks.get(index).markAsUndone();
            System.out.println("Ok, I've marked this task as not done yet:");
            System.out.println(tasks.get(index).getStatusMessage());
        }
        else {
            try {
                Task task = Pogo.addTask(input);
                System.out.println("added: " + task.getStatusMessage());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } catch (PogoInvalidTaskException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (PogoEmptyTaskException e) {
                System.out.println("☹ OOPS!!! The description of a task cannot be empty.\n");
            } catch (PogoException e) {
                System.out.println("☹ OOPS!!! An error has occurred.");
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
