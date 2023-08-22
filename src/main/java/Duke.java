import java.util.Scanner;
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

        Scanner sc = new Scanner(System.in);
        String input;

        while (true) {
            input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println("_____________________________________\n");
                System.out.println("Bye. Hope to see you again soon!\n");
                System.out.println("_____________________________________\n");
                break;
            } else {
                System.out.println("_____________________________________\n");
                System.out.println(input + "\n");
                System.out.println("_____________________________________\n");
            }
        }
    }
}
