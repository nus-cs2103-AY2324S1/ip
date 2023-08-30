import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private final String name = "Paimon";

    public void readTask(String command, User user) throws DukeException {
        String[] commandSplit = command.split(" ");
        if (command.equals("list")) {
            user.listTasks();
        } else {
            if (command.startsWith("todo") ||
                    command.startsWith("deadline") ||
                    command.startsWith("event")) {
                this.addTask(command, user);
            } else if (command.startsWith("mark") ||
                    command.startsWith("unmark") ||
                    command.startsWith("delete")) {
                try {
                    if (commandSplit.length > 2 || Integer.parseInt(commandSplit[1]) > user.taskCount()) {
                        throw new DukeException("Please enter a valid number");
                    }
                    int index = Integer.parseInt(commandSplit[1]) - 1;
                    if (command.startsWith("mark")) {
                        user.markTask(index);
                    } else if (command.startsWith("unmark")) {
                        user.unMarkTask(index);
                    } else {
                        user.deleteTask(index);
                        return;
                    }
                    user.taskToPrint(index);
                } catch (NumberFormatException e) {
                    throw new DukeException("Please enter a valid number");
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("Please enter a valid number");
                }
            } else {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    public void addTask(String command, User user) throws DukeException {
        if (command.startsWith("todo")) {
            try {
                String taskDescription = command.substring(5);
                if (taskDescription.length() == 0) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
                Task t = new Todo(taskDescription);
                user.addTask(t);
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + t.toString());
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
        } else if (command.startsWith("deadline")) {
            try {
                String[] taskAndDeadline = command.substring(9).split(" /by ");
                if (taskAndDeadline[0].length() == 0) {
                    throw new DukeException("The description of a deadline cannot be empty.");
                }
                Task t = new Deadline(taskAndDeadline[0], taskAndDeadline[1]);
                user.addTask(t);
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + t.toString());
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("The description of a deadline cannot be empty.");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("The deadline is not specified.");
            }
        } else if (command.startsWith("event")) {
            try {
                String[] taskAndDate = command.substring(6).split(" /from | /to ");
                if (taskAndDate[0].length() == 0) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
                Task t = new Event(taskAndDate[0], taskAndDate[1], taskAndDate[2]);
                user.addTask(t);
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + t.toString());
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("The description of an event cannot be empty.");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("The dates of an event cannot be empty.");
            }
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        System.out.println(" Now you have " + user.taskCount() + " tasks in the list.");
    }

    public void greetAndFarewell() {
        System.out.println("---------------------------------------------------------------");
        System.out.println(" Hello! I'm " + name);
        System.out.println(" What can I do for you?");
        System.out.println("---------------------------------------------------------------");
        User user = new User("data/duke.txt");

        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while (!command.equals("bye")) {
            System.out.println("---------------------------------------------------------------");
            try {
                this.readTask(command, user);
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
            System.out.println("---------------------------------------------------------------");
            command = scanner.nextLine();
        }

        scanner.close();
        System.out.println("---------------------------------------------------------------");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("---------------------------------------------------------------");
    }

    public static void main(String[] args) {
        Duke chatBot = new Duke();
        chatBot.greetAndFarewell();
    }
}
