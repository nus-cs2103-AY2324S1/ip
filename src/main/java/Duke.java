import java.util.Scanner;

public class Duke {
    private final static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    public void startChat() {
        System.out.println("---------------------------------------------\n Hello! I'm zy\n" +
                            " What can I do for you?\n---------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while (!command.equals("bye")) {
            System.out.println("---------------------------------------------");
            try {
                this.readNewTask(command);
            } catch (DukeException e) {
                System.out.println(e);
            }
            System.out.println("---------------------------------------------");
            command = scanner.nextLine();
        }
        scanner.close();
        System.out.println("---------------------------------------------\n Bye. Hope to see you again soon!" +
                            "\n---------------------------------------------");
    }

    public void readNewTask(String command) throws DukeException {
        String[] separateCommand = command.split(" ");
        if (command.equals("list")) {
            this.listAllTasks();
        } else if (command.startsWith("mark") || command.startsWith("unmark")) {
            try {
                if (separateCommand.length > 2 || Integer.parseInt(separateCommand[1]) > taskCount) {
                    throw new DukeException("☹ OOPS!!! Invalid number");
                }
                int taskNumber = Integer.parseInt(separateCommand [1]);
                if (command.startsWith("mark")) {
                    tasks[taskNumber - 1].markAsDone();
                    System.out.println(" Nice! I've marked this task as done:");
                } else if (command.startsWith("unmark")) {
                    tasks[taskNumber - 1].markAsUndone();
                    System.out.println(" OK, I've marked this task as not done yet:");
                }
                System.out.println("   " + tasks[taskNumber - 1].toString());
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! Invalid number");
            }
        } else if (command.startsWith("todo") || command.startsWith("deadline") || command.startsWith("event")) {
            this.addNewTask(command);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public void listAllTasks() {
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(" " + (i + 1) + "." + tasks[i].toString());
        }
    }

    public void addNewTask(String command) throws DukeException {
        if (command.startsWith("todo")) {
            try {
                String description = command.substring(5);
                if (description.length() == 0) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                tasks[taskCount] = new ToDo(description);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
        } else if (command.startsWith("deadline")) {
            try {
                String[] parts = command.split("/by");
                String description = parts[0].substring(9).trim();
                if (description.length() == 0) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
                String by = parts[1].trim();
                tasks[taskCount] = new Deadline(description, by);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
        } else if (command.startsWith("event")) {
            try {
                String[] parts = command.split("/from");
                String description = parts[0].substring(6).trim();
                if (description.length() == 0) {
                    throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                }
                String[] timeParts = parts[1].split("/to");
                String start = timeParts[0].trim();
                String end = timeParts[1].trim();
                tasks[taskCount] = new Event(description, start, end);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            }
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        taskCount++;
        System.out.println(" Got it. I've added this task:" + "\n" + "   " + tasks[taskCount - 1].toString() + "\n"
                            + " Now you have " + taskCount + " tasks in the list.");
    }

    public static void main(String[] args) {
        Duke bot = new Duke();
        bot.startChat();
    }
}
