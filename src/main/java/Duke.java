import java.util.Scanner;
/**
 * Class For Duke
 */
public class Duke {
    public static void main(String[] args) {
        System.out.println(
                "____________________________________________________________\n" +
                "Hello! I'm Chrainx \n" +
                "What can I do for you? \n" +
                "____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n"
        );
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!(input.equals("bye"))) {
            System.out.println(
                    "____________________________________________________________\n" +
                    input + "\n" +
                    "____________________________________________________________\n"
            );
            input = scanner.nextLine();
        }
        System.out.println(
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n"
        );
    }
}
