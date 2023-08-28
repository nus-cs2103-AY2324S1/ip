import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        ArrayList<Task> taskList = new ArrayList<>();

        String initMsg = "____________________________________________________________\n"
                + " Hello! I'm IRIS\n"
                + " What can I do for you?\n"
                + "____________________________________________________________";
        System.out.println(initMsg);

        input = scanner.nextLine();

        while (!input.equals("bye")) {
            System.out.println("____________________________________________________________");
            String[] inputParts = input.split(" ", 2);
            String command = inputParts[0];

            if (input.equals("list")) {
                int count = 1;
                for (Task task : taskList) {
                    if (task == null) {
                        break;
                    } else {
                        System.out.println(count++ + ". " + task);
                    }
                }
            } else if (command.equals("mark")) {
                int index = Integer.parseInt(inputParts[1]) - 1;
                taskList.get(index).markDone();
                System.out.println("Nice! I've marked this task as done:\n"
                + taskList.get(index));
            } else if (command.equals("unmark")) {
                int index = Integer.parseInt(inputParts[1]) - 1;
                taskList.get(index).markUndone();
                System.out.println("OK, I've marked this task as not done yet:\n"
                        + taskList.get(index));
            } else if (command.equals("delete")) {
                int index = Integer.parseInt(inputParts[1]) - 1;
                System.out.println("Noted. I've removed this task:\n"
                        + taskList.get(index));
                taskList.remove(index);
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
            } else {
                try {
                    Task newTask;
                    if (inputParts.length < 2) {
                        throw new DukeException("☹ OOPS!!! The description of a command cannot be empty.");
                    } else if (command.equals("todo")) {
                        newTask = new Todo(inputParts[1]);
                    } else if (command.equals("deadline")) {
                        String[] commandParts = inputParts[1].split("/by", 2);
                        newTask = new Deadline(commandParts[0], commandParts[1]);
                    } else if (command.equals("event")) {
                        String[] commandParts = inputParts[1].split("/from", 2);
                        String[] eventParts = commandParts[1].split("/to");
                        newTask = new Event(commandParts[0], eventParts[0], eventParts[1]);
                    } else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }

                    taskList.add(newTask);
                    System.out.println("Got it. I've added this task:\n" + newTask);
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                } catch (DukeException e) {
                    System.out.println(e);
                }
            }

            System.out.println("____________________________________________________________");
            input = scanner.nextLine();
        }

        scanner.close();
        String endMsg = "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________";
        System.out.println(endMsg);
    }
}