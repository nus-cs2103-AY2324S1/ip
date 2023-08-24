import java.util.Scanner;
/**
 * Class For Duke
 */
public class Duke {
    /**
     * method to check whether a string is an integer
     * @param str the string we want to check
     * @return a boolean
     */
    private static boolean isInteger (String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static void main(String[] args) {
        /**
         * To store the item
         */
        Task[] storage = new Task[100];
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
            if (
                    input.split(" ").length == 2
                            && input.split(" ")[0].equals("mark")
                            && isInteger(input.split(" ")[1])
                            && Integer.parseInt(input.split(" ")[1]) <= curr + 1
                            && Integer.parseInt(input.split(" ")[1]) >= 1
            ) {
                storage[Integer.parseInt(input.split(" ")[1]) - 1].changeMarkStatus(true);
                System.out.println(
                        "____________________________________________________________\n" +
                                "You have marked this task as done \n" +
                                storage[Integer.parseInt(input.split(" ")[1]) - 1].toString()
                );
            } else if (
                    input.split(" ").length == 2
                            && input.split(" ")[0].equals("unmark")
                            && isInteger((input.split(" "))[1])
                            && Integer.parseInt(input.split(" ")[1]) <= curr + 1
                            && Integer.parseInt(input.split(" ")[1]) >= 1
            ) {
                storage[Integer.parseInt(input.split(" ")[1]) - 1].changeMarkStatus(false);
                System.out.println(
                        "____________________________________________________________\n" +
                                "You have marked this task as not done \n" +
                                storage[Integer.parseInt(input.split(" ")[1]) - 1].toString()
                );
            } else if (input.equals("list")) {
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
                storage[curr] = new Task(input);
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
