import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static String dash = "\t-------------------------------------------------------------";
    public static ArrayList<String> taskList = new ArrayList<>();

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
        taskList.add(message);
    }

    public static void listAllTask() {

        System.out.println(dash);
        if (taskList.size() > 0) {
            for (int i = 1; i < taskList.size() + 1; i++) {
                System.out.println("\t" + i + ". " + taskList.get(i - 1));
            }
        }
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
            } else {
                addTask(message);
            }
            System.out.println();
            message = sc.nextLine();
            System.out.println();
        }
        farewell();
    }
}
