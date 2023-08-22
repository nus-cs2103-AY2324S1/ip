import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        final String logo = "\n   _____ _    _          _____   _____ _____ _______ \n" +
                "  / ____| |  | |   /\\   |  __ \\ / ____|  __ \\__   __|\n" +
                " | |    | |__| |  /  \\  | |  | | |  __| |__) | | |   \n" +
                " | |    |  __  | / /\\ \\ | |  | | | |_ |  ___/  | |   \n" +
                " | |____| |  | |/ ____ \\| |__| | |__| | |      | |   \n" +
                "  \\_____|_|  |_/_/    \\_\\_____/ \\_____|_|      |_|   \n";
        final String horizontal = "--------------------------------------------------------" +
                "-----------";

        List<Task> taskList = new ArrayList<Task>();

        System.out.println(horizontal + logo + horizontal);
        System.out.println("ChadGPT: Welcome to ChadGPT, What would you like to do today?\n" + horizontal);

        Scanner sc = new Scanner(System.in);
        System.out.print("User: ");
        while (!sc.hasNext("bye")) {
            String command = sc.next();
            if (command.equals("list")) {
                int counter = 1;
                System.out.println("ChadGPT: Here are your tasks: ");
                for (Task t : taskList) {
                    System.out.print(counter++ + ". ");
                    t.printStatus();
                }
            } else if (command.equals("mark")) {
                int index = Integer.parseInt(sc.next()) - 1;
                taskList.get(index).completeTask();
                System.out.println("ChadGPT: Nice! I'll mark the task as done: ");
                taskList.get(index).printStatus();
            } else if (command.equals("unmark")) {
                int index = Integer.parseInt(sc.next()) - 1;
                taskList.get(index).undo();
                System.out.println("ChadGPT: No problem, I'll mark this task as not done yet: ");
                taskList.get(index).printStatus();
            } else {
                System.out.println("ChadGPT: added task '" + command + "'");
                taskList.add(new Task(command));
            }
            System.out.print(horizontal + "\nUser: ");
        }

        sc.close();

        System.out.print("ChadGPT: Bye. Hope to see you again soon!\n" + horizontal);
    }
}
