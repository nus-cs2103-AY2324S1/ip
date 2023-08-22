import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        String welcomeMessage = "____________________________________________________________\n" +
                " Hello! I'm Bongo!\n" +
                " What can I do for you?\n" +
                "____________________________________________________________";
        System.out.println(welcomeMessage);
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String[] input = sc.nextLine().split(" ");
            if (input[0].equals("bye")) {
                String goodbyeMessage = "____________________________________________________________\n" +
                        " Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________";
                System.out.println(goodbyeMessage);
                break;
            }
            if (input[0].equals("list")) {
                StringBuilder allTasks = new StringBuilder();
                for (int i = 0; i < tasks.size(); i++) {
                    allTasks.append(String.format(" %d.%s\n", i + 1, tasks.get(i)));
                }
                String tasksList = "____________________________________________________________\n" +
                        " Here are the tasks in your list:\n" +
                        allTasks +
                        "____________________________________________________________";
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
                            "____________________________________________________________";
                System.out.println(finalMessage);
                continue;
            }
            Task newTask = null;
            if (input[0].equals("todo")) {
                String newTaskDesc = String.join(" ", input).substring(input[0].length() + 1);
                newTask = new Todo(newTaskDesc);
            }
            if (input[0].equals("deadline")) {
                String taskInput = String.join(" ", input).substring(input[0].length() + 1);
                int index = taskInput.indexOf("/by");
                String taskDesc = taskInput.substring(0, index - 1);
                String taskDeadline = taskInput.substring(index + 4);
                newTask = new Deadline(taskDesc, taskDeadline);
            }
            if (input[0].equals("event")) {
                String taskInput = String.join(" ", input).substring(input[0].length() + 1);
                int fromIndex = taskInput.indexOf("/from");
                int toIndex = taskInput.indexOf("/to");
                String taskDesc = taskInput.substring(0, fromIndex - 1);
                String taskFrom = taskInput.substring(fromIndex + 6, toIndex - 1);
                String taskTo = taskInput.substring(toIndex + 4);
                newTask = new Event(taskDesc, taskFrom, taskTo);
            }
            tasks.add(newTask);
            String echoMessage = "____________________________________________________________\n" +
                    " Got it. I've added this task:\n" +
                    String.format("  %s\n", newTask) +
                    String.format(" Now you have %d tasks in the list.\n", tasks.size()) +
                    "____________________________________________________________";
            System.out.println(echoMessage);
        }
    }
}

