import java.util.ArrayList;
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
         * ArrayList To store the task
         */
        ArrayList<Task> storage = new ArrayList<>();
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
            try {
                if (
                        input.split(" ").length == 2
                                && input.split(" ")[0].equals("mark")
                                && isInteger(input.split(" ")[1])
                                && Integer.parseInt(input.split(" ")[1]) <= curr + 1
                                && Integer.parseInt(input.split(" ")[1]) >= 1
                ) {
                    storage.get(Integer.parseInt(input.split(" ")[1]) - 1).changeMarkStatus(true);
                    System.out.println(
                            "____________________________________________________________\n" +
                                    "You have marked this task as done\n" +
                                    storage.get(Integer.parseInt(input.split(" ")[1]) - 1).toString() + "\n" +
                                    "____________________________________________________________\n"
                    );
                } else if (
                        input.split(" ").length == 2
                                && input.split(" ")[0].equals("unmark")
                                && isInteger((input.split(" "))[1])
                                && Integer.parseInt(input.split(" ")[1]) <= curr + 1
                                && Integer.parseInt(input.split(" ")[1]) >= 1
                ) {
                    storage.get(Integer.parseInt(input.split(" ")[1]) - 1).changeMarkStatus(false);
                    System.out.println(
                            "____________________________________________________________\n" +
                                    "You have marked this task as not done\n" +
                                    storage.get(Integer.parseInt(input.split(" ")[1]) - 1).toString() + "\n" +
                                    "____________________________________________________________\n"
                    );
                } else if (input.equals("list")) {
                    System.out.println("____________________________________________________________\n");
                    for (int i = 0; i < curr; i++) {
                        System.out.println(
                                (i + 1) + ". " + storage.get(i).toString() + "\n"
                        );
                    }
                    System.out.println("____________________________________________________________\n");
                } else if (input.split(" ").length == 2
                        && input.split(" ")[0].equals("delete")
                        && isInteger((input.split(" "))[1])
                        && Integer.parseInt(input.split(" ")[1]) <= curr + 1
                        && Integer.parseInt(input.split(" ")[1]) >= 1) {
                    System.out.println(
                            "____________________________________________________________\n" +
                            "Noted. I've removed this task:" +
                            storage.get(Integer.parseInt(input.split(" ")[1] ) - 1)
                    );
                    storage.remove(Integer.parseInt(input.split(" ")[1] ) - 1);
                    curr--;
                    System.out.println(
                            "Now you have " + curr + "  task in the list\n" +
                            "____________________________________________________________\n");
                } else {
                    System.out.println("____________________________________________________________\n");
                    if (ToDos.isTodo(input)) {
                        storage.add(new ToDos(input.substring(5, input.length())));
                        System.out.println("Got it. I've added this task:");
                    } else if (Deadlines.isDeadline(input)) {
                        storage.add(
                                new Deadlines(
                                    input.substring(9, input.indexOf("/by ")),
                                    input.substring(input.indexOf("/by ") + 4, input.length())
                                )
                        );
                        System.out.println("Got it. I've added this task:");
                    } else if (Events.isEvent(input)) {
                        storage.add(new Events(
                                input.substring(6, input.indexOf("/from ")),
                                input.substring(input.indexOf("/from ") + 6, input.indexOf("/to ")),
                                input.substring(input.indexOf("/to ") + 4, input.length())
                                )
                        );
                        System.out.println("Got it. I've added this task:");
                    } else {
                        Task.isTask(input);
                    }
                    System.out.println(
                            storage.get(curr).toString() + "\n" +
                                    "Now you have " + (curr + 1) + " tasks in the list.\n" +
                                    "____________________________________________________________\n");
                    curr++;
                }
            } catch (CommanNotFoundException e){
                System.out.println(e.getMessage());
            } catch (TodoEmptyNameException e){
                System.out.println(e.getMessage());
            } catch (DeadlineEmptyNameException e) {
                System.out.println(e.getMessage());
            } catch (DeadlineByNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (EventEmptyNameException e) {
                System.out.println(e.getMessage());
            } catch (EventFromNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (EventToNotFoundException e) {
                System.out.println(e.getMessage());
            }
            input = scanner.nextLine();
        }
        System.out.println(
                " Bye. Hope to see you again soon!\n" +
                        "Wish You a wonderful day\n" +
                        "____________________________________________________________\n"
        );
    }
}