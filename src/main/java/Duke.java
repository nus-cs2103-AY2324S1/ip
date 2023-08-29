import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        String name = "Chaty";

        System.out.println("Hello! I'm " + name + "\n"
                + "What can I do for you?" + "\n\n");
        String next = scan.nextLine();
        String[] nextParts = next.split(" ");

        while (!next.equals("bye")) {
            try {
                tasks = TaskFile.loadTasks();
                if (nextParts.length <= 1 && !nextParts[0].toLowerCase().equals("list")) {
                    throw new DukeException("You forgot to write the task");
                }
                switch (nextParts[0].toLowerCase()) {
                    case "list": {
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + "." + tasks.get(i));
                        }
                        break;
                    }

                    case "mark": {
                        int tasknum = Integer.parseInt(next.split(" ")[1]) - 1;
                        tasks.get(tasknum).mark();
                        System.out.println("Nice! I've marked this task as done:\n" + tasks.get(tasknum));
                        break;
                    }

                    case "unmark": {
                        int tasknum = Integer.parseInt(next.split(" ")[1]) - 1;
                        tasks.get(tasknum).unmark();
                        System.out.println(" OK, I've marked this task as not done yet:\n" + tasks.get(tasknum));
                        break;
                    }
                    case "deadline": {
                        if (!next.contains("/by") || next.length() <= next.indexOf("/by") + 4) {
                            throw new DukeException("You forgot to specify when the deadline ends!");
                        }
                        Task nextTask = new Deadline(false, next);
                        tasks.add(nextTask);
                        System.out.println("Got it. I've added this task: \n" + nextTask + "\nnow you have "
                                + tasks.size() + " tasks in the list");
                        break;
                    }
                    case "event": {
                        if (!next.contains("/from")) {
                            throw new DukeException("You forgot to specify when the event starts!");
                        }
                        if (!next.contains("/to")) {
                            throw new DukeException("You forgot to specify when the event ends!");
                        }
                        Task nextTask = new Event(false, next);
                        tasks.add(nextTask);
                        System.out.println("Got it. I've added this task: \n" + nextTask + "\nnow you have "
                                + tasks.size() + " tasks in the list");
                        break;
                    }

                    case "todo": {
                        Task nextTask = new Todo(false, next);
                        tasks.add(nextTask);
                        System.out.println("Got it. I've added this task: \n" + nextTask + "\nnow you have "
                                + tasks.size() + " tasks in the list");
                        break;
                    }

                    case "delete": {
                        if (tasks.size() <= 0) {
                            throw new DukeException("There are no tasks to delete");
                        }
                        int deleteIndex = Integer.parseInt(next.split(" ")[1]) - 1;
                        Task deleted = tasks.remove(deleteIndex);
                        System.out.println("Noted. I've removed this task:\n" + deleted + "\nNow you have "
                                + tasks.size() + " tasks in the list");
                        break;
                    }
                    default : {
                        throw new DukeException("I can't identify your command!");
                    }
                }
                    TaskFile.saveTask(tasks);
                }
            catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            next = scan.nextLine();
            nextParts = next.split(" ");
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}