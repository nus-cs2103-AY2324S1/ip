import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Duke {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        String name = "Chaty";
        String taskFileName = "/tasks.txt";

        System.out.println("Hello! I'm " + name + "\n"
                + "What can I do for you?" + "\n\n");
        String next = scan.nextLine();
        while (!next.equals("bye")) {
            try {
                if (next.equals("list")) {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + "." + tasks.get(i));
                    }

                } else if (next.split(" ").length < 2) {
                    throw new DukeException("You forgot to enter the task!");

                } else if (next.startsWith("mark")) {
                    int tasknum = Integer.parseInt(next.split(" ")[1]) - 1;
                    tasks.get(tasknum).mark();
                    System.out.println("Nice! I've marked this task as done:\n" + tasks.get(tasknum));

                } else if (next.startsWith("unmark")) {
                    int tasknum = Integer.parseInt(next.split(" ")[1]) - 1;
                    tasks.get(tasknum).unmark();
                    System.out.println(" OK, I've marked this task as not done yet:\n" + tasks.get(tasknum));

                } else if (next.startsWith("deadline")) {
                    if (!next.contains("/by") || next.length() <= next.indexOf("/by") + 4) {
                        throw new DukeException("You forgot to specify when the deadline ends!");
                    }
                    Task nextTask = new Deadline(next);
                   // writeToFile(taskFile, nextTask);
                    tasks.add(nextTask);
                    System.out.println("Got it. I've added this task: \n" + nextTask + "\nnow you have "
                            + tasks.size() + " tasks in the list");

                } else if (next.startsWith("event")) {
                    if (!next.contains("/from")) {
                        throw new DukeException("You forgot to specify when the event starts!");
                    }
                    if (!next.contains("/to")) {
                        throw new DukeException("You forgot to specify when the event ends!");
                    }
                    Task nextTask = new Event(next);
                    tasks.add(nextTask);
                    System.out.println("Got it. I've added this task: \n" + nextTask + "\nnow you have "
                            + tasks.size() + " tasks in the list");

                } else if (next.startsWith("todo")) {
                    Task nextTask = new Todo(next);
                    tasks.add(nextTask);
                    System.out.println("Got it. I've added this task: \n" + nextTask + "\nnow you have "
                            + tasks.size() + " tasks in the list");
                    TaskFile.saveTask(nextTask, taskFileName);

                } else if (next.startsWith("delete")) {
                    if (tasks.size() <= 0) {
                        throw new DukeException("There are no tasks to delete");
                    }
                    int deleteIndex = Integer.parseInt(next.split(" ")[1]) - 1;
                    Task deleted = tasks.remove(deleteIndex);
                    System.out.println("Noted. I've removed this task:\n" + deleted + "\nNow you have "
                        + tasks.size() + " tasks in the list");

                } else {
                    throw new DukeException("Sorry I don't understand your input!");
                }

            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            next = scan.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}