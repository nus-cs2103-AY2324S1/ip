import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static boolean exited = false;
    static ArrayList<Task> tasks = new ArrayList<>();

    public static void exit() {
        String goodbye = "Bye. Hope to see you again soon!";
        System.out.println(goodbye);
        System.exit(0);
    }
//    public static void echo(String input) {
//        System.out.println(input);
//    }

    public static void add(Task t) {
        String taskName = t.getName();
        tasks.add(t);
        System.out.println("Got it. I've added this task:\n "
                + t.toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.");


    }

    public static void list() {
        int listSize = tasks.size();
        for (int i = 0; i < listSize ; i++) {
            int num = i + 1;
            Task taskToList = tasks.get(i);
            System.out.println(num + ". " + taskToList.toString());
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
//            str.append(sc.nextLine());
//            int spaceIndex = str.indexOf(" ");
//            String command = str.substring(0, spaceIndex);

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
            } else if (command.equals("todo")) {

                String restOfString = sc.nextLine();
                String taskName = restOfString;
                Task taskToAdd = new Todo(taskName);
                Duke.add(taskToAdd);
            } else if (command.equals("deadline")) {
                String restOfString = sc.nextLine();
                int slashIndex = restOfString.indexOf("/by");
                String taskName = restOfString.substring(0, slashIndex - 1);
                String date = restOfString.substring(slashIndex + 4);
                Task taskToAdd = new Deadline(taskName, date);
                Duke.add(taskToAdd);
            } else if (command.equals("event")) {
                String restOfString = sc.nextLine();
                int fromIndex = restOfString.indexOf("/from");
                int toIndex = restOfString.indexOf("/to");
                String taskName = restOfString.substring(0, fromIndex - 1);
                String fromDate = restOfString.substring(fromIndex + 6, toIndex - 1);
                String toDate = restOfString.substring(toIndex + 4);
                Task taskToAdd = new Event(taskName, fromDate, toDate);
                Duke.add(taskToAdd);

            }
        }


    }
}
