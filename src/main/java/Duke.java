import java.util.Scanner;
/**
 * Class For Duke
 */
public class Duke {
    public static void main(String[] args) {
        /**
         * To store the item
         */
        String[] storage = new String[100];
        /**
         * current number of item
         */
        int curr = 0;
        System.out.println(
                "____________________________________________________________\n" +
                "Hello! I'm Chrainx \n" +
                "What can I do for you? \n" +
                "____________________________________________________________\n"
        );
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!(input.equals("bye"))) {
            if (input.equals("list")) {
                System.out.println("____________________________________________________________\n");
                for (int i = 0; i < curr; i++) {
                    System.out.println(
                            (i+1) + ". " + storage[i] + "\n"
                    );
                }
                System.out.println("____________________________________________________________\n");
            } else {
                System.out.println(
                        "____________________________________________________________\n" +
                                "added: " + input + "\n" +
                                "____________________________________________________________\n"
                );
                storage[curr] = input;
                curr++;
            }
            input = scanner.nextLine();
        }
        System.out.println(
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n"
        );
    }
}
