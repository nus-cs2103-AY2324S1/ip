import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int counter = 0;
        String[] actions = new String[100];
        boolean[] isDone = new boolean[100];
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
                    System.out.println(" " + (i + 1) + "." + checkAction(actions[i], isDone[i]));
                }
            } else if (input.startsWith("add")) {
                String action = input.substring(4).trim();
                actions[counter] = action;
                isDone[counter] = false;
                counter++;
                System.out.println(" added: " + action);
            } else if (input.startsWith("mark")) {
                int num = Integer.parseInt(input.substring(5).trim()) - 1;
                // remove mark then the integer must - 1 because array index start with 0
                if (num < counter) {
                    isDone[num] = true;
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("   " + checkAction(actions[num], isDone[num]));
                }
            } else if (input.startsWith("unmark")) {
                int num2 = Integer.parseInt(input.substring(7).trim()) -1 ;
                if (num2 < counter) {
                    isDone[num2] = false;
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println("   " + checkAction(actions[num2], isDone[num2]));
                }
            }
            System.out.println("____________________________________________________________");
        }
    }
    private static String checkAction(String task, boolean isDone) {
        return "[" + (isDone ? "X" : " ") + "] " + task;
    }
}
