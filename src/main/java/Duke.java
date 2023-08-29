import java.util.Scanner;
import java.util.ArrayList;

/**
 * Program to run a task manager that can add, delete and mark tasks.
 *
 * @author Teo Kai Sheng
 */
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<Task>();
        String line = "    ______________________________________________";
        System.out.println(line + "\n    Hello, I'm your task manager :)\n    What can I do for you?\n" + line);
        String[] input = scanner.nextLine().split(" ", 2);
        while (!input[0].equals("bye")) {
            String command = input[0];
            System.out.println(line);
            if (command.equals("list") ) {
                try {
                    if (!(input.length == 1 || input[1].trim().equals(""))) {
                        throw new DukeException();
                    }
                    System.out.println("    Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(String.format("    %d.%s",
                                i+1, list.get(i).toString()));
                    }
                } catch (DukeException e) {
                    System.out.println("    Did you mean list?");
                }
            } else if (command.equals("mark")) {
                try {
                    int toMark = Integer.parseInt(input[1]);
                    Task task = list.get(toMark - 1);
                    task.markAsDone();
                    System.out.println("    Nice! I've marked this task as done:");
                    System.out.println(String.format("      %s", task.toString()));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("    Task does not exist.");
                } catch (NumberFormatException e) {
                    System.out.println("    Please enter a number e.g., mark 1");
                }
            } else if (command.equals("unmark")) {
                try {
                    int toMark = Integer.parseInt(input[1]);
                    Task task = list.get(toMark - 1);
                    task.markAsUndone();
                    System.out.println("    OK, I've marked this task as not done yet:");
                    System.out.println(String.format("      %s", task.toString()));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("    Task does not exist.");
                } catch (NumberFormatException e) {
                    System.out.println("    Please enter a number e.g., unmark 1");
                }
            } else if (command.equals("delete")) {
                try {
                    int toDelete = Integer.parseInt(input[1]);
                    Task task = list.get(toDelete - 1);
                    System.out.println("    Noted. I've removed this task:");
                    System.out.println(String.format("      %s", task.toString()));
                    list.remove(toDelete - 1);
                    System.out.println("    Number of tasks: " + list.size());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("    Task does not exist.");
                } catch (NumberFormatException e) {
                    System.out.println("    Please enter a number e.g., delete 1");
                }
            } else if (command.equals("deadline")) {
                try {
                    String[] s = input[1].split("/by", 2);
                    String desc = s[0].trim();
                    String deadline = s[1].trim();
                    if (desc.equals("") || deadline.equals("")) {
                        throw new DukeException();
                    }
                    Deadline d = new Deadline(desc, deadline);
                    list.add(d);
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("      " + d.toString());
                    System.out.println("    Number of tasks: " + list.size());
                } catch (DukeException e) {
                    System.out.println("    Format: deadline description /by time");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("    Format: deadline description /by time");
                }

            } else if (command.equals("event")) {
                try {
                    String[] s1 = input[1].split("/from", 2);
                    String[] s2 = s1[1].split("/to", 2);
                    String desc = s1[0].trim();
                    String from = s2[0].trim();
                    String to = s2[1].trim();
                    if (desc.equals("") || from.equals("") || to.equals("")) {
                        throw new DukeException();
                    }
                    Event e = new Event(desc, from, to);
                    list.add(e);
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("      " + e.toString());
                    System.out.println("    Number of tasks: " + list.size());
                } catch (DukeException e) {
                    System.out.println("    Format: event description /from time /to time");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("    Format: event description /from time /to time");
                }

            } else if (command.equals("todo")) {
                try {
                    String desc = input[1];
                    if (desc.trim().equals("")) {
                        throw new DukeException();
                    }
                    ToDo t = new ToDo(desc);
                    list.add(t);
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("      " + t.toString());
                    System.out.println("    Number of tasks: " + list.size());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("    ☹ OOPS!!! The description of a todo cannot be empty.");
                } catch (DukeException e) {
                    System.out.println("    ☹ OOPS!!! The description of a todo cannot be empty.");
                }
            } else {
                System.out.println("    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            System.out.println(line);
            input = scanner.nextLine().split(" ", 2);
        }
        System.out.println(line + "\n    Bye. Hope to see you again soon!\n" + line);
    }
}
