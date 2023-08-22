import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("_____________________________________\n");
        System.out.println("Hello! I 'm Jarvis.\n");
        System.out.println("What can I do for you?\n");
        System.out.println("_____________________________________\n");

        List<String> task = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String input;

        while (true) {
            input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println("_____________________________________\n");
                System.out.println("Bye. Hope to see you again soon!\n");
                System.out.println("_____________________________________\n");
                break;
            } else if (input.equals("list")) {
                System.out.println("_____________________________________\n");
                for (int i = 0; i < task.size(); i++) {
                    int tem_order = i + 1;
                    System.out.println(tem_order + "." + task.get(i) );
                }
                System.out.println("_____________________________________\n");
            } else {
                task.add(input);
                System.out.println("_____________________________________\n");
                System.out.println("added: " + input + "\n");
                System.out.println("_____________________________________\n");
            }
        }
    }
}
