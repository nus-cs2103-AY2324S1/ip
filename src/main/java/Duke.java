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
            } else if (command.startsWith("todo")) {
                String description = command.split("todo")[1].strip();
                taskList.add(new ToDo(description));
            } else if (command.startsWith("deadline")) {
                String[] res = command.split("deadline");
                String description = res[1].split("/by")[0].strip();
                String by = res[1].split("/by")[1].strip();
                taskList.add(new Deadline(description, by));
            } else if (command.startsWith("event")) {
                String res = command.split("event")[1];
                String description = res.split("/from")[0].strip();
                String time = res.split("/from")[1];
                String from  = time.split("/to")[0].strip();
                String to = time.split("/to")[1].strip();

                taskList.add(new Event(description, from, to));
            }

            command = scanner.nextLine();
        }
        System.out.println(divider);
        System.out.println("Bye. Hope to see you again soon lol!");
        System.out.println(divider);
    }
}