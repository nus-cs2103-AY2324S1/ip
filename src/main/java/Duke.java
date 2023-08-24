import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static List<Task> tasks = new ArrayList<>();
    private static final String lineSep = "-------------------------------";
    public static void main(String[] args) {
        System.out.println(lineSep);
        System.out.println("Hello! I'm YJ's Chatbot");
        System.out.println("What can I do for you?\n" + lineSep);
        Scanner scanner = new Scanner(System.in);
        String cmd = readCmd(scanner);
        while (!cmd.equals("bye")) {
            if (cmd.equals("list")) {
                listTasks();
                System.out.println(lineSep);
            } else if (cmd.contains("mark ")) {  // works for both mark and unmark
                String[] split = cmd.split(" ");
                if (split.length > 1) {
                    markTask(split);
                }
            } else {
//                System.out.println("Got it. I've added this task:\n" + cmd + "\n" + lineSep);
                System.out.println("Got it. I've added this task:");
                String[] split = cmd.split(" ");
                String taskType = split[0];
                if (taskType.equals("todo")) {
                    Todo todo = new Todo(split[1]);
                    tasks.add(todo);
                    System.out.println(todo);
                } else if (taskType.equals("deadline")) {
                    Deadline deadline = new Deadline(split[1], split[3]);
                    tasks.add(deadline);
                    System.out.println(deadline);
                } else {
                    Event event = new Event(split[1], split[3], split[5]);
                    tasks.add(event);
                    System.out.println(event);
                }
                System.out.println(lineSep);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            }
            cmd = readCmd(scanner);
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
        tasks.get(idx).setCompleted(split[0].equals("mark"));
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(idx).toString());
        System.out.println(lineSep);
    }

}
