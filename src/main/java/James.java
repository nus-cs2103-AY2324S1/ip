import java.util.Scanner;
import java.util.ArrayList;

public class James {
    public static void main(String[] args) {
        String line = "____________________________________________________________";
        String sadFace = "\u2639";

        System.out.println(line + "\n" + "Hello! I'm James\n" + "What can I do for you?\n" + line);

        // User Input
        Scanner in = new Scanner(System.in);

        ArrayList<Task> items = new ArrayList<Task>();

        String input = in.nextLine();
        while (!input.equals("bye")) {
            try {
                if (input.equals("list")) {
                    System.out.println(line);
                    for (int i = 0; i < items.size(); i++) {
                        System.out.println(i + 1 + "." + items.get(i));
                    }
                } else if (input.contains("unmark")) {
                    Integer taskIdx = Integer.parseInt(input.split(" ")[1]);
                    Task task = items.get(taskIdx - 1);
                    task.unmark();
                    System.out.println(line + "\n" + "OK! I've marked this task as not done yet:");
                    System.out.println(task + "\n" + line);
                } else if (input.contains("mark")) {
                    Integer taskIdx = Integer.parseInt(input.split(" ")[1]);
                    Task task = items.get(taskIdx - 1);
                    task.mark();
                    System.out.println(line + "\n" + "Nice! I've marked this task as done:");
                    System.out.println(task + "\n" + line);
                } else if (input.contains("delete")) {
                    Integer taskIdx = Integer.parseInt(input.split(" ")[1]);
                    Task task = items.get(taskIdx - 1);
                    items.remove(task);
                    System.out.println(line + "\n" + "Noted. I've removed this task:\n" + task + "\n" + line);
                    System.out.println("Now you have " + items.size() + " tasks in the list.");
                } else {
                    // Add Task
                    Task task;
                    if (input.contains("todo")) {
                        String[] description = input.split("todo ");
                        if (description.length == 1) {
                            throw new JamesException(sadFace + " OOPS!!! The description of a todo cannot be empty.");
                        }
                        task = new ToDo(description[1]);
                    } else if (input.contains("deadline")) {
                        String[] parsed = input.split("deadline ");
                        if (parsed.length == 1) {
                            throw new JamesException(sadFace + " OOPS!!! The description of a deadline cannot be empty.");
                        }
                        String[] param = parsed[1].split(" /by ");
                        if (param.length == 1) {
                            throw new JamesException(sadFace + " OOPS!!! The time of a deadline cannot be empty.");
                        }
                        String description = param[0];
                        String time = param[1];
                        task = new Deadline(description, time);
                    } else if (input.contains("event")) {
                        String[] parsed = input.split("event ")[1].split(" /from ");
                        if (parsed.length == 1) {
                            throw new JamesException(sadFace + " OOPS!!! The description of a event cannot be empty.");
                        }
                        String description = parsed[0];
                        String[] params = parsed[1].split(" /to ");
                        if (params.length == 1) {
                            throw new JamesException(sadFace + " OOPS!!! The time of a event cannot be empty.");
                        }
                        String startTime = params[0];
                        String endTime = params[1];
                        task = new Event(description, startTime, endTime);
                    } else {
                        throw new JamesException(sadFace + " OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    items.add(task);
                    System.out.println(line + "\n" + "Got it. I've added this task:\n" + task + "\n" + line);
                    System.out.println("Now you have " + items.size() + " tasks in the list.");
                }

            } catch (JamesException e) {
                System.out.println(line + "\n" + e.getMessage() + "\n" + line);
            }
            input = in.nextLine();
        }

        System.out.println(line + "\nBye. Hope to see you again soon!\n" + line);

    }
}
