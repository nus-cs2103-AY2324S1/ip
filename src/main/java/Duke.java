import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    Scanner scanner = new Scanner(System.in);
    private ArrayList<Task> list = new ArrayList<>();

    private void greet() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Hello! I'm Duke.");
        System.out.println("\t What can I do for you?");
        System.out.println("\t____________________________________________________________");
        System.out.println();
    }

    private void getUserInput() {
        while (true) {
            try {
                String userCommand = scanner.nextLine();

                if (userCommand.isEmpty()) {
                    throw new EmptyCommandException();
                } else if (userCommand.equals("bye")) {
                    this.exit();
                    break;
                } else if (userCommand.equals("list")) {
                    this.showList();
                } else if (userCommand.startsWith("mark")) {
                    this.markTaskAsDone(userCommand);
                } else if (userCommand.startsWith("unmark")) {
                    this.markTaskAsNotDone(userCommand);
                } else if (userCommand.startsWith("todo")) {
                    this.addTodo(userCommand);
                } else if (userCommand.startsWith("deadline")) {
                    this.addDeadline(userCommand);
                } else if (userCommand.startsWith("event")) {
                    this.addEvent(userCommand);
                } else {
                    throw new UnknownCommandException();
                }
            } catch (DukeException e) {
                System.out.println("\t____________________________________________________________");
                System.out.println("\t" + e.getMessage());
                System.out.println("\t____________________________________________________________");
                System.out.println();
            }
        }
    }

    private void exit() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Bye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }

    private void showList() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println("\t " + (i + 1) + ". " + this.list.get(i));
        }
        System.out.println("\t____________________________________________________________");
        System.out.println();
    }

    private void markTaskAsDone(String userCommand) throws InvalidTaskIndexException {
        try {
            int taskIndex = Integer.parseInt(userCommand.split(" ")[1]) - 1;

            if (taskIndex < 0 || taskIndex >= list.size()) {
                throw new InvalidTaskIndexException(taskIndex + 1);
            }

            Task task = list.get(taskIndex);
            task.markAsDone();
            System.out.println("\t____________________________________________________________");
            System.out.println("\t Nice! I've marked this task as done:\n" +
                    "\t\t" + this.list.get(taskIndex));
            System.out.println("\t____________________________________________________________");
            System.out.println();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("\t____________________________________________________________");
            System.out.println("\t ☹ OOPS!!! Please provide a valid task number to mark as done.");
            System.out.println("\t____________________________________________________________");
        }
    }

    private void markTaskAsNotDone(String userCommand) throws InvalidTaskIndexException {
        try {
            int taskIndex = Integer.parseInt(userCommand.split(" ")[1]) - 1;

            if (taskIndex < 0 || taskIndex >= list.size()) {
                throw new InvalidTaskIndexException(taskIndex + 1);
            }

            Task task = list.get(taskIndex);
            task.markAsNotDone();
            System.out.println("\t____________________________________________________________");
            System.out.println("\t OK, I've marked this task as not done yet:\n" +
                    "\t\t" + this.list.get(taskIndex));
            System.out.println("\t____________________________________________________________");
            System.out.println();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("\t____________________________________________________________");
            System.out.println("\t ☹ OOPS!!! Please provide a valid task number to mark as undone.");
            System.out.println("\t____________________________________________________________");
        }

    }

    private void addTodo(String userCommand) throws EmptyDescriptionException {
        if (userCommand.length() <= 5) {
            throw new EmptyDescriptionException("todo");
        }

        String description = userCommand.substring(5).trim();
        Todo newTask = new Todo(description);
        list.add(newTask);

        System.out.println("\t____________________________________________________________");
        System.out.println("\t Got it. I've added this task:\n" +
                "\t\t" + newTask + "\n\t Now you have " + list.size() + " tasks in the list.");
        System.out.println("\t____________________________________________________________");
        System.out.println();
    }

    private void addDeadline(String userCommand) throws EmptyDescriptionException {
        if (userCommand.length() <= 9) {
            throw new EmptyDescriptionException("deadline");
        }

        String description = userCommand.substring(9).split("/by")[0].trim();
        String by = userCommand.substring(9).split("/by")[1].trim();

        Deadline newTask = new Deadline(description, by);
        list.add(newTask);
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Got it. I've added this task:\n" +
                "\t\t" + newTask + "\n\t Now you have " + list.size() + " tasks in the list.");
        System.out.println("\t____________________________________________________________");
        System.out.println();
    }

    private void addEvent(String userCommand) throws EmptyDescriptionException {
        if (userCommand.length() <= 6) {
            throw new EmptyDescriptionException("event");
        }

        String description = userCommand.substring(6).split("/from")[0].trim();
        String from = userCommand.substring(6).split("/from")[1].split("/to")[0].trim();
        String to = userCommand.substring(6).split("/to")[1].trim();

        Event newTask = new Event(description, from, to);
        list.add(newTask);
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Got it. I've added this task:\n" +
                "\t\t" + newTask + "\n\t Now you have " + list.size() + " tasks in the list.");
        System.out.println("\t____________________________________________________________");
        System.out.println();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        duke.getUserInput();
    }
}
