import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Jo {

    public static void markDone(Task task) {
        task.mark();
        System.out.println("> Nice! I've marked this task as done:");
        System.out.println("\t" + task.getTaskStatus());
    }

    public static void markNotDone(Task task) {
        task.unmark();
        System.out.println("> OK, I've marked this task as not done yet:");
        System.out.println("\t" + task.getTaskStatus());
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
                    System.out.println("\t" + (i+1) + ". " + t.getTaskStatus());
                }
            } else if (input.substring(0, 4).equals("mark")) {
                int taskIndex = Character.getNumericValue(input.charAt(input.length() - 1)) - 1;
                markDone(taskList.get(taskIndex));
            } else if (input.substring(0, 6).equals("unmark")) {
                int taskIndex = Character.getNumericValue(input.charAt(input.length() - 1)) - 1;
                markNotDone(taskList.get(taskIndex));
            } else {
                Task newTask = new Task(input);
                taskList.add(newTask);
                System.out.println("> Added: " + newTask.description);
            }
            input = scanner.nextLine();
        }
        System.out.println("> Bye. Hope to see you again soon!");

    }
}
