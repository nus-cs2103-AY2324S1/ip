import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static String dash = "\t-------------------------------------------------------------";
    public static ArrayList<Task> taskList = new ArrayList<>();

    public static void welcomeMessage() {

        String logo = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("\tHello from\n" + logo);
        System.out.println(dash);
        System.out.println("\t Hello! I'm YOLO \n\t What can I do for you? \n");
        System.out.println(dash);
        System.out.println();
    }

    public static void farewell() {

        System.out.println(dash);
        System.out.println("\t Bye. Hope to see you again soon! \n");
        System.out.println(dash);
    }

    public static void listAllTask() {

        System.out.println(dash);
        System.out.println("\tHere " + (taskList.size() > 1 ? "are" : "is") + " the " + (taskList.size() > 1 ? "tasks" : "task") + " in your list: ");
        if (taskList.size() > 0) {
            for (int i = 1; i < taskList.size() + 1; i++) {
                System.out.println("\t" + i + "." + taskList.get(i - 1).toString());
            }
        }
        System.out.println();
        System.out.println(dash);
    }

    public static void mark(int i) {

        taskList.get(i - 1).mark();
        System.out.println(dash);
        System.out.println(" \tNice! I've marked this task as done:");
        System.out.println("\t  " + taskList.get(i - 1).toString());
        System.out.println();
        System.out.println(dash);
    }

    public static void unmark(int i) {

        taskList.get(i - 1).unmark();
        System.out.println(dash);
        System.out.println(" \tOk! I've marked this task as not done yet:");
        System.out.println("\t  " + taskList.get(i - 1).toString());
        System.out.println();
        System.out.println(dash);
    }

    public static void addTodo(String message) {

        System.out.println(dash);
        System.out.println("\tGot it. I've added this task: ");
        String taskDetail = message.substring(5, message.length());
        Task todo = new Todo(taskDetail);
        taskList.add(todo);
        System.out.println("\t  " + todo);
        System.out.println("\tNow you have " + taskList.size() + (taskList.size() > 1 ? " tasks" : " task") + " in the list.");
        System.out.println();
        System.out.println(dash);
    }

    public static void addDeadline(String message) {

        System.out.println(dash);
        System.out.println("\tGot it. I've added this task: ");
        String taskDetail = message.substring(9, message.length());
        String[] arr = taskDetail.split("/");
        Deadline dl = new Deadline(arr[0], arr[1].substring(3)); //here
        taskList.add(dl);
        System.out.println("\t  " + dl);
        System.out.println("\tNow you have " + taskList.size() + (taskList.size() > 1 ? " tasks" : " task") + " in the list.");
        System.out.println();
        System.out.println(dash);
    }

    public static void addEvent(String message) {

        System.out.println(dash);
        System.out.println("\tGot it. I've added this task: ");
        String taskDetail = message.substring(6, message.length());
        String[] arr = taskDetail.split("/");
        Event e = new Event(arr[0], arr[1].substring(5), arr[2].substring(3)); //here
        taskList.add(e);
        System.out.println("\t  " + e);
        System.out.println("\tNow you have " + taskList.size() + (taskList.size() > 1 ? " tasks" : " task") + " in the list.");
        System.out.println();
        System.out.println(dash);
    }

    public static void main(String[] args) {

        welcomeMessage();
        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine();

        while (!message.equals("bye")) {

//            System.out.println(message.substring(0, 8));
            if (message.equals("list")) {
                listAllTask();
            } else if (message.length() >= 4 && message.substring(0, 4).equals("mark")) {

                int index = Integer.parseInt(message.substring(message.length() - 1));
                if (index > 0 && index <= taskList.size()) {
                    mark(index);
                }
            } else if (message.length() >= 6 && message.substring(0, 6).equals("unmark")) {

                int index = Integer.parseInt(message.substring(message.length() - 1));
                if (index > 0 && index <= taskList.size()) {
                    unmark(index);
                }
                // can use enum here, as for now just use 3 different methods
            } else if (message.length() >= 4 && message.substring(0, 4).equals("todo")) {
                addTodo(message);
            } else if (message.length() >= 8 && message.substring(0, 8).equals("deadline")) {
                addDeadline(message);
            } else if (message.length() >= 5 && message.substring(0, 5).equals("event")) {
                addEvent(message);

            }
            System.out.println();
            message = sc.nextLine();
        }
        farewell();
    }
}
