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
            } else if (cmd.split(" ")[0].equals("todo")) {
                String taskName = cmd.split(" ", 2)[1];
                Task todo = new ToDo(taskName);
                list.add(todo);
                printAddTaskMessage(todo);
            } else if (cmd.split(" ")[0].equals("deadline")) {
                String task = cmd.split(" ", 2)[1];
                String taskName = task.split("/",2)[0];
                String end = cmd.split("/", 2)[1];
                String by = end.split(" ", 2)[1];
                Task deadline = new Deadline(taskName, by);
                list.add(deadline);
                printAddTaskMessage(deadline);
            } else if (cmd.split(" ")[0].equals("event")) {
                String task = cmd.split(" ", 2)[1];
                String taskName = task.split("/",2)[0];
                String[] time = cmd.split("/", 3);
                String starting = time[1];
                String ending = time[2];
                String start = starting.split(" ", 2)[1];
                String end = ending.split(" ", 2)[1];

                Task event = new Event(taskName, start, end);
                list.add(event);
                printAddTaskMessage(event);
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
            Task task = list.get(i);
            System.out.println(i+1 + "." + task.getTaskType() + task.getStatusIcon() + " " + task.name);
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
                " Got it. I've added this task: \n   " +
                task.getTaskType() + task.getStatusIcon() + " " + task.name + task.getTimeInfo() + "\n" +
                " Now you have " + list.size() + " tasks in the list.\n" +
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
