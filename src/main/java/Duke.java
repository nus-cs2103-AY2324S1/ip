import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        String welcomeMessage = "____________________________________________________________\n" +
                " Hello! I'm Bongo!\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(welcomeMessage);
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String[] input = sc.nextLine().split(" ");
            if (input[0].equals("bye")) {
                String goodbyeMessage = "____________________________________________________________\n" +
                        " Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________\n";
                System.out.println(goodbyeMessage);
                break;
            }
            if (input[0].equals("list")) {
                StringBuilder allTasks = new StringBuilder();
                for (int i = 0; i < tasks.size(); i++) {
                    allTasks.append(String.format("%d.%s\n", i + 1, tasks.get(i)));
                }
                String tasksList = "____________________________________________________________\n" +
                        allTasks +
                        "____________________________________________________________\n";
                System.out.println(tasksList);
                continue;
            }
            if (input.length >= 2 && (input[0].equals("mark") || input[0].equals("unmark"))) {
                int taskIndex = Integer.parseInt(input[1]);
                Task chosenTask = tasks.get(taskIndex - 1);
                String taskStatusMessage;
                if (input[0].equals("mark")) {
                    chosenTask.markAsDone();
                    taskStatusMessage = " Nice! I've marked this task as done:\n";
                } else {
                    chosenTask.markAsUndone();
                    taskStatusMessage = " OK, I've marked this task as not done yet:\n";
                }
                String finalMessage = "____________________________________________________________\n" +
                            taskStatusMessage +
                            String.format("  %s\n", chosenTask) +
                            "____________________________________________________________\n";
                System.out.println(finalMessage);
                continue;
            }
            String newTaskDesc = String.join(" ", input);
            tasks.add(new Task(newTaskDesc));
            String echoMessage = "____________________________________________________________\n" +
                    String.format(" added: %s\n", newTaskDesc) +
                    "____________________________________________________________\n";
            System.out.println(echoMessage);
        }
    }
}

