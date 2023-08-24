import java.util.Scanner;

/**
 * Class for duke
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
         * To store the task
         */
        Task[] storage = new Task[100];
        /**
         * To indicate the current number of task
         */
        int curr = 0;
        System.out.println(
                "____________________________________________________________\n" +
                        " Hello! I'm Chrainx\n" +
                        " What can I do for you?\n" +
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
            } else if (input.equals("list")){
                System.out.println("____________________________________________________________\n");
                for (int i = 0; i < curr; i++) {
                    System.out.println(
                            (i+1) + ". " + storage[i].toString() + "\n"
                    );
                }
                System.out.println("____________________________________________________________\n");
            } else {
                System.out.println(
                        "____________________________________________________________\n" +
                                "Got it. I've added this task:"
                );
                if (input.split( " ")[0].equals("todo")) {
                    storage[curr] = new ToDos(input.substring(5, input.length()));
                } else if (input.split( " ")[0].equals("deadline")) {
                    storage[curr] = new Deadlines(
                            input.substring(9, input.indexOf("/by ")),
                            input.substring(input.indexOf("/by ") + 4, input.length())
                    );
                }
                else if (input.split( " ")[0].equals("event")) {
                    storage[curr] = new Events(
                            input.substring(6, input.indexOf("/from ")),
                            input.substring(input.indexOf("/from ") + 6, input.indexOf("/to ")),
                            input.substring(input.indexOf("/to ") + 4, input.length())
                    );
                } else {
                    storage[curr] = new Task(input);
                }
                System.out.println(
                        storage[curr].toString() + "\n" +
                                "Now you have " + (curr + 1) + " tasks in the list.\n" +
                                "____________________________________________________________\n");
                curr++;
            }
            input = scanner.nextLine();
        }
        System.out.println(
                " Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________\n"
        );
    }
}