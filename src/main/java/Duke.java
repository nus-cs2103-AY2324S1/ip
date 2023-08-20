import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private final String name = "Paimon";
    private List<Task> tasks = new ArrayList<>();

    public void listTasks() {
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + this.tasks.get(i).toString());
        }
    }

    public void readTask(String command) throws DukeException {
        String[] commandSplit = command.split(" ");
        if (command.equals("list")) {
            this.listTasks();
        } else {
            if (command.startsWith("todo") ||
                    command.startsWith("deadline") ||
                    command.startsWith("event")) {
                this.addTask(command);
            } else if (command.startsWith("mark") ||
                    command.startsWith("unmark") ||
                    command.startsWith("delete")) {
                try {
                    if (commandSplit.length > 2 || Integer.parseInt(commandSplit[1]) > tasks.size()) {
                        throw new DukeException("Please enter a valid number");
                    }
                    int markTask = Integer.parseInt(commandSplit[1]);
                    if (command.startsWith("mark")) {
                        this.tasks.get(markTask - 1).markAsDone();
                        System.out.println(" Nice! I've marked this task as done:");
                    } else if (command.startsWith("unmark")) {
                        this.tasks.get(markTask - 1).markAsNotDone();
                        System.out.println(" OK, I've marked this task as not done yet:");
                    } else {
                        System.out.println(" Noted. I've removed this task:");
                        System.out.println("   " + this.tasks.get(markTask - 1).toString());
                        this.tasks.remove(markTask - 1);
                        System.out.println(" Now you have " + this.tasks.size() + " tasks in the list.");
                        return;
                    }
                    System.out.println("   " + this.tasks.get(markTask - 1).toString());
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

    public void addTask(String command) throws DukeException {
        if (command.startsWith("todo")) {
            try {
                String taskDescription = command.substring(5);
                if (taskDescription.length() == 0) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
                Task t = new Todo(taskDescription);
                this.tasks.add(t);
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
                this.tasks.add(t);
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
                this.tasks.add(t);
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
        System.out.println(" Now you have " + this.tasks.size() + " tasks in the list.");
    }

    public void greetAndFarewell() {
        System.out.println("---------------------------------------------------------------");
        System.out.println(" Hello! I'm " + name);
        System.out.println(" What can I do for you?");
        System.out.println("---------------------------------------------------------------");

        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while (!command.equals("bye")) {
            System.out.println("---------------------------------------------------------------");
            try {
                this.readTask(command);
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
