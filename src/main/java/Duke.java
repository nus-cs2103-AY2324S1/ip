import java.util.Scanner;
import java.util.ArrayList;
public class Duke {

    private static void addTask(ArrayList<Task> taskList, String[] cmd) throws DukeException {
        if (cmd[0].equals("todo")) {
            if (cmd.length == 1 || cmd[1].equals("")) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }

            taskList.add(new ToDo(cmd[1]));
        } else if (cmd[0].equals("deadline")) {

            if (cmd.length == 1 || cmd[1].equals("")) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }

            String[] task = cmd[1].split("/by ", 2);
            if (task.length == 1 || task[1].equals("")) {
                throw new DukeException("☹ OOPS!!! Need to include /by date for deadline.");
            }

            taskList.add(new Deadline(task[0], task[1]));
        } else if (cmd[0].equals("event")) {

            if (cmd.length == 1 || cmd[1].equals("")) {
                throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
            }

            String[] event = cmd[1].split("/from ", 2);

            if (event.length == 1 || event[1].equals("")) {
                throw new DukeException("☹ OOPS!!! Need to include /from date for an event.");
            }

            String[] dates = event[1].split("/to ", 2);

            if (dates.length == 1 || dates[1].equals("")) {
                throw new DukeException("☹ OOPS!!! Need to include /to date for an event.");
            }

            taskList.add(new Event(event[0], dates[0], dates[1]));
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.get(taskList.size() - 1).toString());
        System.out.println("Now you have " + (taskList.size()) + " tasks in the list.");

    }

    public static void main(String[] args) {
        String name = "Obi-wan Kenobi";
        String line = "_____________________________________";
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        System.out.println("Hello There! I am " + name);
        System.out.println("What can I do for you?");
        System.out.println(line);

        while (true) {
            String input = scanner.nextLine();
            String[] command = input.split(" ", 2);

            if (command[0].equals("bye") && command.length == 1) {
                break;
            } else if (command[0].equals("list") && command.length == 1) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.print((i + 1) + "." + taskList.get(i).toString() + "\n");
                }
            } else if (command.length == 2 && (command[0].equals("mark") || command[0].equals("unmark"))) {
                int pos = Integer.parseInt(command[1]);

                if (command[0].equals("mark")) {
                    taskList.get(pos - 1).markTask();
                    System.out.println("Nice! I've marked this task as done:");
                } else {
                    taskList.get(pos - 1).unmarkTask();
                    System.out.println("OK, I've marked this task as not done yet:");
                }

                System.out.println(taskList.get(pos - 1).toString());
            } else if (command[0].equals("delete") && command.length == 2) {
                int pos = Integer.parseInt(command[1]);

                System.out.println("Noted. I've removed this task:");
                System.out.println(taskList.get(pos - 1));
                taskList.remove(pos - 1);
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
            } else {
                // add tasks
                try {
                    addTask(taskList, command);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }

            }

            System.out.println(line);
        }

        System.out.println("Bye. May the force be with you!");
    }

}