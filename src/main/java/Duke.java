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
        final String horizontal = "-------------------------------------------------------------------";

        List<String> taskList = new ArrayList<String>();

        System.out.println(horizontal + logo + horizontal);
        System.out.println("ChadGPT: Welcome to ChadGPT, What would you like to do today?\n" + horizontal);

        Scanner sc = new Scanner(System.in);
        System.out.print("User: ");
        while (!sc.hasNext("bye")) {
            String task = sc.nextLine();
            if (task.equals("list")) {
                int counter = 1;
                System.out.println("ChadGPT: Here are your tasks: ");
                for (String t : taskList) {
                    System.out.println(counter + ". " + t);
                    counter++;
                }
            } else {
                System.out.println("ChadGPT: added task '" + task + "'");
                taskList.add(task);
            }
            System.out.print(horizontal + "\nUser: ");
        }

        sc.close();

        System.out.print("ChadGPT: Bye. Hope to see you again soon!\n" + horizontal);
    }
}
