import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static boolean exited = false;
    Task t = new Task("test task");
    static ArrayList<Task> tasks = new ArrayList<>();

    public static void exit() {
        String goodbye = "Bye. Hope to see you again soon!";
        System.out.println(goodbye);
        System.exit(0);
    }
//    public static void echo(String input) {
//        System.out.println(input);
//    }

    public static void add(String input) {
        Task newTask = new Task(input);
        tasks.add(newTask);
        System.out.println("Added: " + input);
    }

    public static void list() {
        int listSize = tasks.size();
        for (int i = 0; i < listSize ; i++) {
            int num = i + 1;
            Task taskToList = tasks.get(i);
            if (taskToList.getStatus()) {
                System.out.println(num + ".[x] " + taskToList.getName());
            } else {
                System.out.println(num + ".[ ] " + taskToList.getName());
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        tasks.clear();
        exited = false;

        String welcome = "Hello! I'm Eddie\n" +
                "What can I do for you?";

        System.out.println(welcome);

        while (!exited) {
            String command = sc.next();
            if (command.equals("bye")) {
                Duke.exit();
            } else if (command.equals("list")) {
                Duke.list();
            } else if (command.equals("mark")) {
                int taskNum = sc.nextInt();
                Task task = tasks.get(taskNum - 1);
                task.taskIsDone();
            } else if (command.equals("unmark")) {
                int taskNum = sc.nextInt();
                Task task = tasks.get(taskNum - 1);
                task.taskNotDone();
            } else {
                String restOfString = sc.nextLine();
                String taskName = command + restOfString;
                Duke.add(taskName);
            }
        }


    }
}
