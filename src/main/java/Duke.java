import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Duke {

    public static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy - HHmm");

    public static void main(String[] args) {
        greet();
        try {
            getUserInput();
        } catch (DukeException e) {
            System.out.println("\n" + e.getMessage());
        } finally {
            exit();
        }
    }

    public static void greet() {
        display("Hello! I'm Max\n" + "What can I do for you?");
    }

    public static void exit() {
        display("Bye. Hope to see you again soon!");
    }

    public static void getUserInput() throws DukeException{
        Scanner sc = new Scanner(System.in);
        TaskList list = new TaskList();
        list.readFromFile();

        while (true) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                display(list.toString());
            } else if (userInput.startsWith("mark")) {
                // get index by splitting user input and get task at that index from list
                int index = Integer.parseInt(userInput.split(" ")[1]);

                if (index < 1 || index > list.getNumberOfTasks()) {
                    throw new DukeException("OOPS!!! Index of task to be marked is out of bounds");
                }

                Task toBeMarked = list.getTaskAt(index - 1);
                toBeMarked.mark();
                display("Nice! I've marked this task as done:\n" + toBeMarked.toString());
            } else if (userInput.startsWith("unmark")) {
                // get index by splitting user input and get task at that index from list
                int index = Integer.parseInt(userInput.split(" ")[1]);

                if (index < 1 || index > list.getNumberOfTasks()) {
                    throw new DukeException("OOPS!!! Index of task to be unmarked is out of bounds");
                }

                Task toBeUnmarked = list.getTaskAt(index - 1);
                toBeUnmarked.unmark();
                display("OK, I've marked this task as not done yet:\n" + toBeUnmarked.toString());
            } else if (userInput.startsWith("delete")) {
                int index = Integer.parseInt(userInput.split(" ")[1]);

                if (index < 1 || index > list.getNumberOfTasks()) {
                    throw new DukeException("OOPS!!! Index of task to be deleted is out of bounds");
                }

                Task toBeDeleted = list.getTaskAt(index - 1);
                list.deleteTaskAt(index - 1);
                display("Noted. I've removed this task:\n" + toBeDeleted.toString()
                        + "\nNow you have " + list.getNumberOfTasks() + " tasks in the list.");
            } else {
                Task add = getTask(userInput);
                try {
                    list.addToList(add);
                    display("Got it. I've added this task:\n" + add.toString()
                            + "\nNow you have " + list.getNumberOfTasks() + " tasks in the list.");
                } catch (NullPointerException e) {
                    throw new DukeException("OOPS!!! Could not add task to the list");
                }
            }

            list.writeToFile();
        }
    }

    @SuppressWarnings("DuplicateExpressions")
    private static Task getTask(String userInput) throws DukeException {
        Task add;

        if (userInput.startsWith("todo")) {
            String description = userInput.substring(userInput.indexOf(' ') + 1);

            if (description.isEmpty() || description.equals("todo")) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty");
            }

            add = new Todo(description);
        } else if (userInput.startsWith("deadline")) {
            try {
                String description = userInput.substring(userInput.indexOf(' ') + 1, userInput.indexOf('/') - 1);
                String by = userInput.substring(userInput.indexOf("/by") + 4);
                add = new Deadline(description, LocalDateTime.parse(by, TIME_FORMAT));
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! Missing parameters in deadline");
            }

        } else if (userInput.startsWith("event")) {
            try {
                String description = userInput.substring(userInput.indexOf(' ') + 1, userInput.indexOf('/') - 1);
                String from = userInput.substring(userInput.indexOf("/from") + 6, userInput.indexOf("/to") - 1);
                String to = userInput.substring(userInput.indexOf("/to") + 4);
                add = new Event(description, LocalDateTime.parse(from, TIME_FORMAT),
                        LocalDateTime.parse(to, TIME_FORMAT));
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! Missing parameters in event");
            }
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return add;
    }

    public static void display(String message) {
        lines();
        System.out.println(message);
        lines();
    }

    public static void lines() {
        System.out.println("");
        for (int i = 0; i < 50; i++) {
            System.out.print("-");
        }
        System.out.println("");
    }
}
