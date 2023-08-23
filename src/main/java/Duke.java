import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static ArrayList<Task> list = new ArrayList<>();
    public static void main(String[] args) {
        printIntro();

        Scanner sc = new Scanner(System.in);
        // Get user command
        String cmd = sc.nextLine();

        // Exit if command is "bye"
        while (!cmd.equals("bye")) {
            // If cmd is "list", list items and wait for next command
            if (cmd.equals("list")) {
                printListItems();
            } else if (cmd.split(" ")[0].equals("mark")) {
                String arr[] = cmd.split(" ", 2);
                int taskNumber = Integer.parseInt(arr[1]) - 1;
                Task task = list.get(taskNumber);
                task.markTask();
                printMarkedTaskMessage(task);
            } else if (cmd.split(" ")[0].equals("unmark")) {
                String arr[] = cmd.split(" ", 2);
                int taskNumber = Integer.parseInt(arr[1]) - 1;
                Task task = list.get(taskNumber);
                task.unMarkTask();
                printUnmarkedTaskMessage(task);
            } else {    // else, echo command and add to the list
                Task newTask = new Task(cmd);
                printAddTaskMessage(newTask);
                list.add(newTask);
            }
            cmd = sc.nextLine();
        }

        printExit();
    }

    public static void printListItems() {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isDone) {
                System.out.println(i+1 + ".[X] " + list.get(i).name);
                continue;
            }
            System.out.println(i+1 + "." + list.get(i).getStatusIcon() + " " + list.get(i).name);
        }
    }

    public static void printIntro() {
        String intro = "____________________________________________________________\n" +
                " Hello! I'm Dookie\n" +
                " What can I do for you?\n" +
                "____________________________________________________________";
        System.out.println(intro);
    }

    public static void printAddTaskMessage(Task task) {
        String message = "____________________________________________________________\n" +
                " added: " + task.name + "\n" +
                "____________________________________________________________";
        System.out.println(message);
    }

    public static void printExit() {
        String exitMessage = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";
        System.out.println(exitMessage);
    }

    public static void printMarkedTaskMessage(Task task) {
        String message = "____________________________________________________________\n" +
                " Nice! I've marked this task as done:\n" +
                "   " + task.getStatusIcon() + " " + task.name + "\n" +
                "____________________________________________________________";
        System.out.println(message);
    }

    public static void printUnmarkedTaskMessage(Task task) {
        String message = "____________________________________________________________\n" +
                " OK. I've marked this task as not done yet:\n" +
                "   " + task.getStatusIcon() + " " + task.name + "\n" +
                "____________________________________________________________";
        System.out.println(message);
    }
}
