import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        /* String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/

        // Hello! I'm [YOUR CHATBOT NAME]

        String print1 = "Hello! I'm Afro\n"
                        + "What can I do for you?\n";

        int currIndex = 1;
        String str;
        List<Task> taskForce = new ArrayList<Task>();

        System.out.println(print1);

        Scanner sc = new Scanner(System.in);

        while (true) {
            str = sc.nextLine();

            if (str.equals("bye")) {
                break;
                
            } else if (str.equals("list")) {
                for (Task task : taskForce) {
                    int index = taskForce.indexOf(task) + 1;
                    System.out.println(index + ":[" + task.getStatusIcon() + 
                                        "] " + task.description);
                }
                
            } else if (str.contains("unmark")) {
                int stringLength = str.length();
                int index = Integer.parseInt(str.substring(stringLength - 1)) - 1;
                
                Task task = taskForce.get(index);
                task.markTaskNotDone();

                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[" + task.getStatusIcon() + 
                                        "] " + task.description);

            } else if (str.contains("mark")) {
                int stringLength = str.length();
                int index = Integer.parseInt(str.substring(stringLength - 1)) - 1;

                Task task = taskForce.get(index);
                task.markTaskDone();

                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + task.getStatusIcon() + 
                                        "] " + task.description);

            } else {
                Task tasks = new Task(str);
                taskForce.add(tasks);

                currIndex++;
                System.out.println("added: " + str);
            }
        }

        sc.close();
        System.out.println("Bye. Hope to see you again soon!");

    }
}
