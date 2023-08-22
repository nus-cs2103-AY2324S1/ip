import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String name = "Derek";
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");

        ArrayList<Task> tasks = new ArrayList<>();

        Scanner in = new Scanner(System.in);
        boolean run = true;
        while (run) {
            String line = in.nextLine();
            String[] split = line.split(" ");
            switch (split[0]) {
                case "mark":
                    int index = Integer.parseInt(split[1]) - 1;
                    Task task = tasks.get(index);
                    task.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(task);
                    break;
                case "unmark":
                    index = Integer.parseInt(split[1]) - 1;
                    task = tasks.get(index);
                    task.markAsUndone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(task);
                    break;
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + "." + tasks.get(i));
                    }
                    break;
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    run = false;
                    break;
                default:
                    Task newTask = new Task(line);
                    tasks.add(newTask);
                    System.out.println("added: " + line);
            }
        }
    }
}
