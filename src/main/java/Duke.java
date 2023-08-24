import java.util.EnumSet;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static List<Task> tasks = new ArrayList<>();
    private static final String lineSep = "-------------------------------";

    private enum Command {
        invalid, bye, list, mark, unmark, delete, todo, deadline, event;

        /*
        If more task types are added in the future can just add here so I don't have to change in main
        when checking if it's an adding task command
         */
        public static EnumSet<Command> taskTypes() {
            return EnumSet.of(todo, deadline, event);
        }
    }
    public static void main(String[] args) {
        System.out.println(lineSep);
        System.out.println("Hello! I'm YJ's Chatbot");
        System.out.println("What can I do for you?\n" + lineSep);
        Scanner scanner = new Scanner(System.in);
        String input = readCmd(scanner);
        Command cmd = getCommand(input);
        while (!cmd.equals(Command.bye)) {
            if (cmd.equals(Command.list)) {
                listTasks();
                System.out.println(lineSep);
            } else if (cmd.equals(Command.delete)) {
                String[] split = input.split(" ");
                if (split.length > 1) {
                    try {
                        Task toRemove = tasks.get(Integer.parseInt(split[1]));
                        tasks.remove(toRemove);
                        System.out.println("Noted. I've removed this task:\n" + toRemove);
                    } catch (NumberFormatException e) {
                        System.out.println("To delete a task you need to provide a valid integer index!\n" + lineSep);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("No task with that index!\n" + lineSep);
                    }
                } else {
                    System.out.println("To delete a task you need to provide an index!\n" + lineSep);
                }
            } else if (cmd.equals(Command.mark) || cmd.equals(Command.unmark)) {  // works for both mark and unmark
                String[] split = input.split(" ");
                if (split.length > 1) {
                    markTask(split);
                } else {
                    System.out.println("To mark/unmark a task you need to provide an index!\n" + lineSep);
                }
            } else if (Command.taskTypes().contains(cmd)){
                addTask(cmd, input);
            } else {
                // Unknown command
                System.out.println("Unknown command given :<");
            }
                System.out.println(lineSep);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                input = readCmd(scanner);
                cmd = getCommand(input);
            }


        // Close scanner, must be done here because if I try to close in readCmd it'll close the System.in stream.
        scanner.close();

        System.out.println("Bye. Hope to see you soon!\n" + lineSep);
    }

    public static String readCmd(Scanner scanner) {
        // Read user input
        String cmd = scanner.nextLine();
        return cmd;
    }

    private static void listTasks() {
        int tempCounter = 0;
        for (Task task : tasks) {
            // Don't print nulls
            if (task == null) { break; }
            if (task instanceof Todo) {
                Todo t = (Todo) task;
                System.out.println(tempCounter + "." + t);
            } else if (task instanceof Deadline) {
                Deadline t = (Deadline) task;
                System.out.println(tempCounter + "." + t);
            } else if (task instanceof Event) {
                Event t = (Event) task;
                System.out.println(tempCounter + "." + t);
            }
            tempCounter++;
        }
    }

    private static void markTask(String[] split) {
        int idx = Integer.parseInt(split[1]);
        try {
            tasks.get(idx).setCompleted(split[0].equals("mark"));
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(idx).toString());
            System.out.println(lineSep);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No task with that index!\n" + lineSep);
        }
    }

    private static Command getCommand(String input) {
        Command ret = Command.invalid;
        for (Command cmd : Command.values() ) {
            if (input.startsWith(cmd.toString())) {
                ret = cmd;
            }
        }
        return ret;
    }

    private static void addTask(Command cmd, String input) {
        String[] split = input.split(" ");
        String taskType = split[0];
        if (taskType.equals("todo")) {
            if (split.length < 2) {
                System.out.println("The description of a todo cannot be empty!\n" + lineSep);
            } else {
                System.out.println("Got it. I've added this task:");
                Todo todo = new Todo(split[1]);
                tasks.add(todo);
                System.out.println(todo);
            }
        } else if (taskType.equals("deadline")) {
            if (split.length < 3) {
                System.out.println("A deadline must contain a description and end specified with `/by`!\n"
                        + lineSep);
            } else {
                System.out.println("Got it. I've added this task:");
                Deadline deadline = new Deadline(split[1], split[3]);
                tasks.add(deadline);
                System.out.println(deadline);
            }
        } else if (taskType.equals("event")) {
            if (split.length < 5) {
                System.out.println("An event must contain a description," +
                        " start and end specified with `/by` and `/to`!\n" + lineSep);
            } else {
                System.out.println("Got it. I've added this task:");
                Event event = new Event(split[1], split[3], split[5]);
                tasks.add(event);
                System.out.println(event);
            }
        }
    }
}
