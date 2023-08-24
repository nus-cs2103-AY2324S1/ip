import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int counter = 0;
        String[] actions = new String[100];
        boolean[] isDone = new boolean[100];
        String[] type = new String[100];
        String logo = "____________________________________________________________\n Hello! I'm bob \n What can I do for you?\n ____________________________________________________________";
        System.out.println(logo);
        while (true) {
            String input = scanner.nextLine();
            System.out.println("____________________________________________________________");
            if (input.startsWith("bye")) {
                System.out.println(" Bye. Hope to see you again soon! \n ____________________________________________________________");
                break;
            } else if (input.startsWith("list")) {
                System.out.println(" Here are the tasks in your list:");
                for (int i = 0; i < counter; i++) {
                    System.out.println(" " + (i + 1) + "." + helper(actions[i], type[i], isDone[i]));
                }
            } else if (input.startsWith("todo")) {
                if (input.length() <= 4) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                } else {
                    String action = input.substring(5).trim();
                    isDone[counter] = false;
                    actions[counter] = action;
                    type[counter] = "T";
                    counter++;
                    System.out.println(" Got it. I've added this task:\n   " + helper(action, "T", false));
                    System.out.println(" Now you have " + counter + " tasks in the list.");
                }
            } else if (input.startsWith("deadline")) {
                String action = input.substring(9, input.indexOf("/by")).trim();
                String by = input.substring(input.indexOf("/by") + 3).trim();
                actions[counter] = action;
                type[counter] = "D";
                isDone[counter] = false;
                counter++;
                System.out.println(" Got it. I've added this task:\n   " + helper(action, "D", false) + " (by: " + by + ")");
                System.out.println(" Now you have " + counter + " tasks in the list.");
            } else if (input.startsWith("event")) {
                String action = input.substring(6, input.indexOf("/from")).trim();
                String from = input.substring(input.indexOf("/from") + 6, input.indexOf("/to")).trim();
                String to = input.substring(input.indexOf("/to") + 4).trim();
                actions[counter] = action;
                isDone[counter] = false;
                type[counter] = "E";
                counter++;
                System.out.println(" Got it. I've added this task:\n   " + helper(action, "E", false) + " (from: " + from + " to: " + to + ")");
                System.out.println(" Now you have " + counter + " tasks in the list.");
            } else if (input.startsWith("mark")) {
                int num = Integer.parseInt(input.substring(5).trim()) - 1;
                if (num < counter) {
                    isDone[num] = true;
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("   " + helper(actions[num], type[num], isDone[num]));
                }
            } else if (input.startsWith("unmark")) {
                int num2 = Integer.parseInt(input.substring(7).trim()) - 1;
                if (num2 < counter) {
                    isDone[num2] = false;
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println("   " + helper(actions[num2], type[num2], isDone[num2]));
                } else {
                      System.out.println("   ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

                }
            }
            System.out.println("____________________________________________________________");
        }
    }

    private static String helper(String task, String taskType, boolean isDone) {
        String taskIcon;
        if (taskType.equals("T")) {
            taskIcon = "[T]";
        } else if (taskType.equals("D")) {
            taskIcon = "[D]";
        } else if (taskType.equals("E")) {
            taskIcon = "[E]";
        } else {
            taskIcon = "[ ]";
        }
        return taskIcon + "[" + (isDone ? "X" : " ") + "] " + task;
    }
}
