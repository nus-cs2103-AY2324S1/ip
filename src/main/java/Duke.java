import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final String line = "____________________________________________________________";
    private ArrayList<Task> tasks = new ArrayList<Task>();

    public void greet() {
        System.out.println(line);
        System.out.println("Hello! I'm Fong!");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    public void bye() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    public void printTasks() {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;

            System.out.println(index + "." + this.tasks.get(i).toString());
        }
        System.out.println(line);
    }

    public void handleTask(String task) {
        System.out.println(line);
        System.out.println("added: " + task);
        System.out.println(line);

        Task nextTask = new Task(task);
        this.tasks.add(nextTask);
    }

    public void markTask(int taskIndex) {
        this.tasks.get(taskIndex).doTask();

        System.out.println(line);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.tasks.get(taskIndex).toString());
        System.out.println(line);
    }

    public void unmarkTask(int taskIndex) {
        this.tasks.get(taskIndex).undoTask();

        System.out.println(line);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.tasks.get(taskIndex).toString());
        System.out.println(line);
    }

    public void acceptTasks() {
        Scanner scanner = new Scanner(System.in);
        String nextTask = scanner.nextLine();

        while (!nextTask.equals("bye")) {
            if (nextTask.equals("list")) {
                this.printTasks();
            } else if (nextTask.startsWith("mark")) {
                String taskIndexString = String.valueOf(nextTask.charAt(nextTask.length() - 1));
                int taskIndex = Integer.parseInt(taskIndexString) - 1;

                this.markTask(taskIndex);
            } else if (nextTask.startsWith("unmark")) {
                String taskIndexString = String.valueOf(nextTask.charAt(nextTask.length() - 1));
                int taskIndex = Integer.parseInt(taskIndexString) - 1;

                this.unmarkTask(taskIndex);
            } else {
                this.handleTask(nextTask);
            }

            nextTask = scanner.nextLine();
        }

        scanner.close();

        this.bye();
    }

    public static void main(String[] args) {
        Duke chatBot = new Duke();
        chatBot.greet();
        chatBot.acceptTasks();
    }
}
