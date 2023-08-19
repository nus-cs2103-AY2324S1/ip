import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String helloString = "Hello! I'm AdaBot.\nWhat do you want to do today?";
    private static final String byeString = "Bye. Hope to see you again soon!";
    private static ArrayList<Task> tasks = new ArrayList<Task>();

    private static String addTask(Task task) {
        tasks.add(task);
        String res = "Got it. I've added this task:\n";
        res += String.format("  %s\n", task.toString());
        res += String.format("Now you have %d tasks in the list.", tasks.size());
        return res;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println(helloString);
        String response = "";

        while (true) {
            response = input.nextLine();
            String[] queries = response.trim().split("\\s+");
            if (queries[0].equals("bye")) {
                break;
            } 
            switch (queries[0]) {
            case "list":
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(String.format("%d.%s", i + 1, tasks.get(i).toString()));
                }
                break;
            case "mark":
                int index = Integer.parseInt(queries[1]) - 1;
                tasks.get(index).markDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks.get(index).toString());
                break;
            case "unmark":
                index = Integer.parseInt(queries[1]) - 1;
                tasks.get(index).unmarkDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + tasks.get(index).toString());
                break;
            case "deadline":
                System.out.println(addTask(Deadline.create(queries)));
                break;
            case "event":
                System.out.println(addTask(Event.create(queries)));
                break;
            case "todo":
                System.out.println(addTask(ToDo.create(queries)));
                break;
            }
        }
        System.out.println(byeString);
        input.close();
    }
}
