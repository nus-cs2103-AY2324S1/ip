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
    private static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        try {
            Storage storedList = new Storage();
            ArrayList<Task> taskList = storedList.getStorage();
            System.out.println(
                "____________________________________________________________\n" +
                        " Hello! I'm Chrainx\n" +
                        " What can I do for you?\n" +
                        "____________________________________________________________\n"
            );
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                try {
                    String input = scanner.nextLine();
                    if (
                            input.split(" ").length == 2
                                    && input.split(" ")[0].equals("mark")
                                    && isInteger(input.split(" ")[1])
                                    && Integer.parseInt(input.split(" ")[1]) <= taskList.size() + 1
                                    && Integer.parseInt(input.split(" ")[1]) >= 1
                    ) {
                        taskList.get(Integer.parseInt(input.split(" ")[1]) - 1).changeMarkStatus(true);
                        System.out.println(
                                "____________________________________________________________\n" +
                                        "You have marked this task as done\n" +
                                        taskList.get(Integer.parseInt(input.split(" ")[1]) - 1).toString() + "\n" +
                                        "____________________________________________________________\n"
                        );
                    } else if (
                            input.split(" ").length == 2
                                    && input.split(" ")[0].equals("unmark")
                                    && isInteger((input.split(" "))[1])
                                    && Integer.parseInt(input.split(" ")[1]) <= taskList.size() + 1
                                    && Integer.parseInt(input.split(" ")[1]) >= 1
                    ) {
                        taskList.get(Integer.parseInt(input.split(" ")[1]) - 1).changeMarkStatus(false);
                        System.out.println(
                                "____________________________________________________________\n" +
                                        "You have marked this task as not done\n" +
                                        taskList.get(Integer.parseInt(input.split(" ")[1]) - 1).toString() + "\n" +
                                        "____________________________________________________________\n"
                        );
                    } else if (input.equals("list")) {
                        System.out.println("____________________________________________________________\n");
                        for (int i = 0; i < taskList.size(); i++) {
                            System.out.println(
                                    (i + 1) + ". " + taskList.get(i).toString() + "\n"
                            );
                        }
                        System.out.println("____________________________________________________________\n");
                    } else if (input.split(" ").length == 2
                            && input.split(" ")[0].equals("delete")
                            && isInteger((input.split(" "))[1])
                            && Integer.parseInt(input.split(" ")[1]) <= taskList.size() + 1
                            && Integer.parseInt(input.split(" ")[1]) >= 1) {
                        System.out.println(
                                "____________________________________________________________\n" +
                                        "Noted. I've removed this task:" +
                                        taskList.get(Integer.parseInt(input.split(" ")[1]) - 1)
                        );
                        taskList.remove(Integer.parseInt(input.split(" ")[1]) - 1);
                        System.out.println(
                                "Now you have " + taskList.size() + "  task in the list\n" +
                                        "____________________________________________________________\n");
                    } else {
                        System.out.println("____________________________________________________________\n");
                        if (ToDos.isTodo(input)) {
                            taskList.add(new ToDos(input.substring(5)));
                            System.out.println("Got it. I've added this task:");
                        } else if (Deadlines.isDeadline(input)) {
                            taskList.add(
                                    new Deadlines(
                                            input.substring(9, input.indexOf("/by ")),
                                            input.substring(input.indexOf("/by ") + 4)
                                    )
                            );
                            System.out.println("Got it. I've added this task:");
                        } else if (Events.isEvent(input)) {
                            taskList.add(new Events(
                                            input.substring(6, input.indexOf("/from ")),
                                            input.substring(input.indexOf("/from ") + 6, input.indexOf("/to ")),
                                            input.substring(input.indexOf("/to ") + 4)
                                    )
                            );
                            System.out.println("Got it. I've added this task:");
                        } else if (input.equals("bye")){
                            storedList.editStorage(taskList);
                            System.out.println(
                                    " Bye. Hope to see you again soon!\n" +
                                            "Wish You a wonderful day\n" +
                                            "____________________________________________________________\n"
                            );
                            scanner.close();
                            break;
                        } else {
                            Task.isTask(input);
                        }
                        System.out.println(
                                taskList.get(taskList.size() - 1).toString() + "\n" +
                                        "Now you have " + (taskList.size()) + " tasks in the list.\n" +
                                        "____________________________________________________________\n");
                    }
                } catch (DukeException e) {
                    System.out.println(e.toString());
                }

            }
        } catch (DukeException e) {
            System.out.println(e.toString());
        }
    }
}