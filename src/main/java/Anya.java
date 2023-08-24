import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class Anya {
    final static String LINE = "\n____________________________________________________________\n";
    static ArrayList<Task> tasks = new ArrayList<>();
    static int taskCount = 0;
    enum Command {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, UNKNOWN
    }
    public static void main(String[] args) {
        greet();
        start();
        exit();
    }

    public static void greet() {
        System.out.println(LINE);
        System.out.println("    Hello! I'm Anya Forger\n"
                         + "    What can I do for you?\n"
                         + "    Enter a command: list/mark/unmark/todo/deadline/event/bye\n");
        System.out.println(LINE);
    }

    public static void exit() {
        System.out.println(LINE);
        System.out.println("    Bye. Hope to see you again soon!\n");
        System.out.println(LINE);
    }

    public static void start() {
        Scanner sc = new Scanner(System.in);

        scan:
        while (true) {
            try {
                String[] arguments = sc.nextLine().split(" ", 2);
                Command command = getCommand(arguments[0]);
                String details;
                if (arguments.length == 1) {
                    details = "";
                } else {
                    details = arguments[1];
                }

                switch (command) {
                    case BYE:
                        break scan;
                    case LIST:
                        list();
                        break;
                    case MARK: {
                        // Error: No argument or Multiple arguments provided
                        if (details.isEmpty() || details.split(" ").length != 1) {
                            throw new InvalidArgumentException(LINE
                                    + "☹ Waku waku! Please only input ONE integer after the word mark!"
                                    + LINE);
                        }
                        // Error: Argument provided is not a number
                        try {
                            Integer.parseInt(details);
                        } catch (NumberFormatException e) {
                            throw new InvalidArgumentException(LINE
                                    + "☹ Waku waku! Please only input INTEGERs after the word mark!"
                                    + LINE);
                        }
                        // Error: Argument provided is not within task numbers
                        int taskNumber = Integer.parseInt(details);
                        if (taskNumber < 1 || taskNumber > taskCount) {
                            throw new InvalidArgumentException(LINE
                                    + "☹ Waku waku! I don't see a task with the number:" + taskNumber
                                    + LINE);
                        }
                        // Error: Argument provided is already Done (Future implementation)

                        Task t = tasks.get(taskNumber - 1);
                        t.markAsDone();

                        System.out.println(LINE);
                        System.out.println("    Waku waku! I've marked this task as Done:");
                        System.out.println("    " + t);
                        System.out.println(LINE);
                        break;
                    }
                    case UNMARK: {
                        // Error: No argument or Multiple arguments provided
                        if (details.isEmpty() || details.split(" ").length != 1) {
                            throw new InvalidArgumentException(LINE
                                    + "☹ Waku waku! Please only input ONE integer after the word unmark!"
                                    + LINE);
                        }
                        // Error: Argument provided is not a number
                        try {
                            Integer.parseInt(details);
                        } catch (NumberFormatException e) {
                            throw new InvalidArgumentException(LINE
                                    + "☹ Waku waku! Please only input INTEGERs after the word unmark!"
                                    + LINE);
                        }
                        // Error: Argument provided is not within task numbers
                        int taskNumber = Integer.parseInt(details);
                        if (taskNumber < 1 || taskNumber > taskCount) {
                            throw new InvalidArgumentException(LINE
                                    + "☹ Waku waku! I don't see a task with the number:" + taskNumber
                                    + LINE);
                        }

                        Task t = tasks.get(taskNumber - 1);
                        t.markAsNotDone();

                        System.out.println(LINE);
                        System.out.println("    Waku waku! I've marked this task as Not Done:");
                        System.out.println("    " + t);
                        System.out.println(LINE);
                        break;
                    }
                    case TODO: {
                        // Error: No argument provided
                        if (details.isEmpty()) {
                            throw new InvalidArgumentException(LINE
                                    + "☹ Waku waku! Please input a description after the word todo!"
                                    + LINE);
                        }

                        Task t = new Todo(details);
                        taskCount++;
                        tasks.add(t);

                        System.out.println(LINE);
                        System.out.println("    Waku waku! I've added this task:");
                        System.out.println("    " + t);
                        System.out.println("    Now you have " + taskCount + " tasks in the list!");
                        System.out.println(LINE);
                        break;
                    }
                    case DEADLINE: {
                        // Error: No argument or wrong no of arguments provided
                        String[] info = details.split("/by");
                        if (details.isEmpty() || info.length != 2) {
                            throw new InvalidArgumentException(LINE
                                    + "☹ Waku waku! Please input in the following format: "
                                    + "    deadline <taskName> /by <deadline>"
                                    + LINE);
                        }

                        String taskName = info[0].trim();
                        String deadline = info[1].trim();
                        Task t = new Deadline(taskName, deadline);
                        taskCount++;
                        tasks.add(t);

                        System.out.println(LINE);
                        System.out.println("    Waku waku! I've added this task:");
                        System.out.println("    " + t);
                        System.out.println("    Now you have " + taskCount + " tasks in the list!");
                        System.out.println(LINE);
                        break;
                    }
                    case EVENT: {
                        // Error: No argument provided
                        if (details.isEmpty()) {
                            throw new InvalidArgumentException(LINE
                                    + "☹ Waku waku! Please input in the following format:\n"
                                    + "    event <taskName> /from <startTime> /to <endTime>"
                                    + LINE);
                        }
                        // Error: Does not contain /from and /to
                        if (!details.contains("/from") && !details.contains("/to")) {
                            throw new InvalidArgumentException(LINE
                                    + "☹ Waku waku! Please input in the following format:\n"
                                    + "    event <taskName> /from <startTime> /to <endTime>"
                                    + LINE);
                        }

                        String taskName = details.split("/from")[0].trim();
                        String startTime = details.split("/from")[1].trim();
                        String endTime = details.split("/to")[1].trim();
                        Task t = new Event(taskName, startTime, endTime);
                        taskCount++;
                        tasks.add(t);

                        System.out.println(LINE);
                        System.out.println("    Waku waku! I've added this task:");
                        System.out.println("    " + t);
                        System.out.println("    Now you have " + taskCount + " tasks in the list!");
                        System.out.println(LINE);
                        break;
                    }
                    case DELETE: {
                        // Error: No argument or Multiple arguments provided
                        if (details.isEmpty() || details.split(" ").length != 1) {
                            throw new InvalidArgumentException(LINE
                                    + "☹ Waku waku! Please input in the following format:\n"
                                    + "    delete <taskNumber>"
                                    + LINE);
                        }
                        // Error: Argument provided is not a number
                        try {
                            Integer.parseInt(details);
                        } catch (NumberFormatException e) {
                            throw new InvalidArgumentException(LINE
                                    + "☹ Waku waku! Please only input INTEGERs after the word unmark!"
                                    + LINE);
                        }
                        // Error: Argument provided is not within task numbers
                        int taskNumber = Integer.parseInt(details);
                        if (taskNumber < 1 || taskNumber > taskCount) {
                            throw new InvalidArgumentException(LINE
                                    + "☹ Waku waku! I don't see a task with the number:" + taskNumber
                                    + LINE);
                        }

                        Task t = tasks.get(taskNumber - 1);
                        tasks.remove(t);
                        taskCount--;

                        System.out.println(LINE);
                        System.out.println("    Waku waku! I've removed this task as Not Done:");
                        System.out.println("    " + t);
                        System.out.println("    Now you have " + taskCount + " tasks in the list!");
                        System.out.println(LINE);
                        break;
                    }
                    default:
                        System.out.println(LINE);
                        System.out.println("☹ Waku waku!!! " +
                                "I'm sorry, but I don't know what that means (yet) :( ");
                        System.out.println(LINE);
                        break;
                }
            } catch (AnyaException e) {
                System.out.println(e);
            }

        }
    }

    public static void list() {
        System.out.println(LINE);
        System.out.println("    Waku waku!\n"
                         + "    Here are the tasks in your list:\n");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(" " + (i + 1) + ". " + tasks.get(i));
        }
        System.out.println(LINE);
    }

    private static Command getCommand(String input) {
        if (input.equals("bye")) return Command.BYE;
        if (input.equals("list")) return Command.LIST;
        if (input.equals("mark")) return Command.MARK;
        if (input.equals("unmark")) return Command.UNMARK;
        if (input.equals("todo")) return Command.TODO;
        if (input.equals("deadline")) return Command.DEADLINE;
        if (input.equals("event")) return Command.EVENT;
        if (input.equals("delete")) return Command.DELETE;
        return Command.UNKNOWN;
    }
}
