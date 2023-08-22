import java.util.ArrayList;
import java.util.Arrays;
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
        String nextTaskString = null;

        if (task.startsWith("todo")) {
            String[] preprocessedTask = task.split("todo ");

            Todo nextTodo = new Todo(preprocessedTask[1]);
            this.tasks.add(nextTodo);
            nextTaskString = nextTodo.toString();
        } else if (task.startsWith("deadline")) {
            String[] temp = task.split("deadline ");
            String[] preprocessedTask = preprocessTask(temp[1]);

            Deadline nextDeadline = new Deadline(preprocessedTask[0], preprocessedTask[1]);
            this.tasks.add(nextDeadline);
            nextTaskString = nextDeadline.toString();
        } else if (task.startsWith("event")) {
            String[] temp = task.split("event ");
            String[] preprocessedTask = preprocessTask(temp[1]);

            Event nextEvent = new Event(preprocessedTask[0], preprocessedTask[1], preprocessedTask[2]);
            this.tasks.add(nextEvent);
            nextTaskString = nextEvent.toString();
        }

        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println(nextTaskString);
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
        System.out.println(line);
    }

    private String[] preprocessTask(String temp) {
        String[] preprocessedTask = temp.split(" /by | /from | /to ");

        return preprocessedTask;
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
