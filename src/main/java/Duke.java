import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static String dash = "\t-------------------------------------------------------------";
    public static ArrayList<Task> taskList = new ArrayList<>();

    public static void welomeMessage() {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
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

    public static void addTask(String message) {

        // check out for empty message
        System.out.println(dash);
        System.out.println("\t added: " + message + "\n");
        System.out.println(dash);
        taskList.add(new Task(message));
    }

    public static void listAllTask() {

        System.out.println(dash);
        System.out.println("\tHere are the tasks in your list: ");
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

    public static void main(String[] args) {

        welomeMessage();
        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine();

        while (!message.equals("bye")) {

            if (message.equals("list")) {
                listAllTask();
            } else if (message.length() >= 4 && message.substring(0, 4).equals("mark")) {

                int index = Integer.parseInt(message.substring(message.length() - 1));
                if (index > 0 && index <= taskList.size()) {
                    mark(Integer.parseInt(message.substring(message.length() - 1)));
                }
            } else if (message.length() >= 6 && message.substring(0, 6).equals("unmark")) {

                int index = Integer.parseInt(message.substring(message.length() - 1));
                if (index > 0 && index <= taskList.size()) {
                    unmark(Integer.parseInt(message.substring(message.length() - 1)));
                }
            } else {
                addTask(message);
            }
            System.out.println();
            message = sc.nextLine();
        }
        farewell();
    }
}
