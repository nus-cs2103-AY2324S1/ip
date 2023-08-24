import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Jo {

    public static void markDone(Task task) {
        task.mark();
        System.out.println("> Nice! I've marked this task as done:");
        System.out.println("\t" + task.toString());
    }

    public static void markNotDone(Task task) {
        task.unmark();
        System.out.println("> OK, I've marked this task as not done yet:");
        System.out.println("\t" + task.toString());
    }

    public static void addTask(Task task, List<Task> taskList) {
        taskList.add(task);
        System.out.println("> Got it. I've added this task:");
        System.out.println("\t" + task.toString());
        System.out.println(String.format("> Now you have %d tasks in the list.", taskList.size()));
    }

    public static void main(String[] args) {

        System.out.println("> Hello! I'm Jo.\n> What can I do for you?");
        List<Task> taskList = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (!input.equals("bye")) {

            if (input.equals("list")) {
                System.out.println("> Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    Task t = taskList.get(i);
                    System.out.println("\t" + (i+1) + ". " + t.toString());
                }

            } else if (input.substring(0, 4).equals("mark")) {
                int taskIndex = Character.getNumericValue(input.charAt(input.length() - 1)) - 1;
                markDone(taskList.get(taskIndex));

            } else if (input.substring(0, 6).equals("unmark")) {
                int taskIndex = Character.getNumericValue(input.charAt(input.length() - 1)) - 1;
                markNotDone(taskList.get(taskIndex));

            } else if (input.substring(0, 4).equals("todo")) {
                String description = input.substring(5, input.length());
                addTask(new Task(description), taskList);

            } else if (input.substring(0, 8).equals("deadline")) {
                String[] description = input.substring(9, input.length()).split("/by", 2);
                String deadline = description[1].trim();
                String taskName = description[0].trim();
                addTask(new Deadline(taskName, deadline), taskList);

            } else if (input.substring(0, 5).equals("event")) {
                String[] description = input.substring(5, input.length()).split("/from", 2);
                String[] dates = description[1].split("/to", 2);
                String start = dates[0].trim();
                String end = dates[1].trim();
                String taskName = description[0].trim();
                addTask(new Event(taskName, start, end), taskList);

            } else {
                Task newTask = new Task(input);
                addTask(newTask, taskList);
            }

            input = scanner.nextLine();

        }

        System.out.println("> Bye. Hope to see you again soon!");

    }
}
