import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    private List<Task> tasks = new ArrayList<>();

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
        } else if (command.startsWith("mark") || command.startsWith("unmark") || command.startsWith("delete")) {
            try {
                if (separateCommand.length > 2 || Integer.parseInt(separateCommand[1]) > tasks.size()) {
                    throw new DukeException("☹ OOPS!!! Invalid number");
                }
                int taskNumber = Integer.parseInt(separateCommand[1]);
                if (command.startsWith("mark")) {
                    tasks.get(taskNumber - 1).markAsDone();
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("   " + tasks.get(taskNumber - 1).toString());
                } else if (command.startsWith("unmark")) {
                    tasks.get(taskNumber - 1).markAsUndone();
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println("   " + tasks.get(taskNumber - 1).toString());
                } else if (command.startsWith("delete")) {
                    System.out.println(" Noted. I've removed this task:");
                    System.out.println("   " + tasks.get(taskNumber - 1).toString());
                    tasks.remove(taskNumber - 1);
                }
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
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + "." + tasks.get(i).toString());
        }
    }

    public void addNewTask(String command) throws DukeException {
        if (command.startsWith("todo")) {
            try {
                String description = command.substring(5);
                if (description.length() == 0) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                tasks.add(new ToDo(description));
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
                tasks.add(new Deadline(description, by));
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
                tasks.add(new Event(description, start, end));
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            }
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        System.out.println(" Got it. I've added this task:" + "\n" + "   " + tasks.get(tasks.size() - 1).toString() + "\n"
                            + " Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void main(String[] args) {
        Duke bot = new Duke();
        bot.startChat();
    }
}
