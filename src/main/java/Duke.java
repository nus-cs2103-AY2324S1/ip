import java.util.Scanner;
public class Duke {
    public static void echo(String str){
        System.out.println("____________________________________________________________\n"
                    + str + "\n"
                    + "____________________________________________________________\n"
        );
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(
                "____________________________________________________________\n"
                + "Hello! I'm ET\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n"
        );

        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            echo(input);
            input = scanner.nextLine();
        }

        System.out.println("____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n"
        );
    }
}
