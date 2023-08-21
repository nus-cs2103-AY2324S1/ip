import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    //    public String logo = " ____        _        \n"
    //            + "|  _ \\ _   _| | _____ \n"
    //            + "| | | | | | | |/ / _ \\\n"
    //            + "| |_| | |_| |   <  __/\n"
    //            + "|____/ \\__,_|_|\\_\\___|\n";
    //        System.out.println("Hello from\n" + logo);
    public static String divider = "____________________________________________________________";
    public static void main(String[] args) {
        System.out.println(divider);
        System.out.println("Hello! I'm BanterBot");
        System.out.println("What can I do for you lmao?");
        System.out.println(divider);

        TaskList taskList = new TaskList();
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                taskList.print();
            } else if (command.startsWith("mark")) {
                int idx = Integer.parseInt(command.split("mark")[1].strip());
                taskList.mark(idx);
            } else if (command.startsWith("unmark")) {
                int idx = Integer.parseInt(command.split("mark")[1].strip());
                taskList.unmark(idx);
            } else {
                taskList.add(new Task(command));
            }
            command = scanner.nextLine();
        }
        System.out.println(divider);
        System.out.println("Bye. Hope to see you again soon lol!");
        System.out.println(divider);
    }
}