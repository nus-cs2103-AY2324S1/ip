import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    static boolean exited = false;
    static ArrayList<String> tasks = new ArrayList<>();

    public static void exit() {
        String goodbye = "Bye. Hope to see you again soon!";
        System.out.println(goodbye);
        System.exit(0);
    }
//    public static void echo(String input) {
//        System.out.println(input);
//    }

    public static void add(String input) {
        tasks.add(input);
        System.out.println("Added: " + input);
    }

    public static void list() {
        int listSize = tasks.size();
        for (int i = 0; i < listSize ; i++) {
            int num = i + 1;
            System.out.println(num + ". " + tasks.get(i));
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
            String command = sc.nextLine();
            if (command.equals("bye")) {
                Duke.exit();
            } else if (command.equals("list")) {
                Duke.list();
            } else {
                Duke.add(command);
            }
        }


    }
}
