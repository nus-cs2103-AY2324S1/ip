import java.util.ArrayList;
import java.util.Scanner;

public class AdaBot {
    private static final String helloString = "Hello! I'm AdaBot.\nWhat do you want to do today?";
    private static final String byeString = "Bye. Hope to see you again soon!";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println(helloString);
        String response = "";
        ArrayList<Task> tasks = new ArrayList<Task>();

        while (true) {
            response = input.nextLine();
            String[] queries = response.trim().split("\\s+");
            if (queries[0].equals("bye")) {
                break;
            } else if (queries[0].equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(String.format("%d.%s", i + 1, tasks.get(i).toString()));
                }
            } else if (queries[0].equals("mark")) {
                int index = Integer.parseInt(queries[1]) - 1;
                tasks.get(index).markDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks.get(index).toString());
            } else if (queries[0].equals("unmark")) {
                int index = Integer.parseInt(queries[1]) - 1;
                tasks.get(index).unmarkDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + tasks.get(index).toString());
            } else {
                tasks.add(new Task(response));
                System.out.println("added: " + response);
            }
        }
        System.out.println(byeString);
        input.close();
    }
}
