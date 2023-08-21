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
        TaskList taskList = new TaskList();
        boolean flag = true;

        System.out.println(divider);
        System.out.println("Hello! I'm BanterBot");
        System.out.println("What can I do for you lmao?");
        System.out.println(divider);

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();

        while (flag) {
            Command command = null;
            try {
                command = Command.parseCommand(input);

                switch (command) {
                    case LIST:
                        taskList.print();
                        break;
                    case MARK:
                        int idx = Integer.parseInt(input.split("mark", 2)[1].strip());

                        taskList.mark(idx);
                        break;
                    case UNMARK:
                        idx = Integer.parseInt(input.split("mark", 2)[1].strip());

                        taskList.unmark(idx);
                        break;
                    case TODO:
                        String description = input.split("todo", 2)[1].strip();

                        taskList.add(new ToDo(description));
                        break;
                    case DEADLINE:
                        String[] parts = input.split("deadline", 2);
                        description = parts[1].split("/by", 2)[0].strip();
                        String by = parts[1].split("/by", 2)[1].strip();

                        taskList.add(new Deadline(description, by));
                        break;
                    case EVENT:
                        parts = input.split("event", 2);
                        description = parts[1].split("/from", 2)[0].strip();
                        String time = parts[1].split("/from", 2)[1];
                        String from = time.split("/to", 2)[0].strip();
                        String to = time.split("/to", 2)[1].strip();

                        taskList.add(new Event(description, from, to));
                        break;
                    case INVALID:
                        System.out.println("Invalid Command Bruh");
                        break;
                    case BYE:
                        System.out.println(divider);
                        System.out.println("Bye. Hope to see you again soon lol!");
                        System.out.println(divider);
                        flag = false;
                        return;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                input = scanner.nextLine();
            }
        }
    }
}